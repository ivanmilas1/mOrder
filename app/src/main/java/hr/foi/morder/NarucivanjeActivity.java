package hr.foi.morder;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hr.foi.morder.adapters.ArticleRecyclerAdapter;
import hr.foi.morder.adapters.ExpendableListAdapter;
import hr.foi.morder.model.Artikl;
import hr.foi.morder.model.Kategorija;
import hr.foi.morder.model.Narudzba;

/**
 * The type Narucivanje activity.
 * Set content view activity_prijava layout
 * @author Nikola Gluhak
 */
public class NarucivanjeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigation;
    private TextView textViewNovoUPonudi;
    private RecyclerView recyclerView;
    private FirebaseFirestore database;
    private ArticleRecyclerAdapter adapter;
    private ExpandableListView expandableListView;
    private ExpandableListAdapter expandableListAdapter;
    private List<String> listHeader;
    private HashMap<String, List<String>> listChild;
    private HashMap<String, List<String>> listChildEx;
    private Long childId;
    private Integer idNarudzba;
    private String stolId;
    private Integer racunId = 0;
    private String racunDokument;
    private Integer stol;
    private String narudzbaDokument;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article);
        drawer = findViewById(R.id.drawer);

        //potrebno da košarica funkcionira
        Intent intent = getIntent();
        racunId = (intent.getIntExtra("racunID", 0));

        toggle = new ActionBarDrawerToggle(this, drawer, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        expandableListView = findViewById(R.id.navigationmenu);
        navigation = findViewById(R.id.nv);
        setupDrawerContent(navigation);
        navigation.setNavigationItemSelectedListener(this);

        textViewNovoUPonudi = findViewById(R.id.naslovNovoUPonudi);

        recyclerView = findViewById(R.id.article_recycler);
        database = FirebaseFirestore.getInstance();

        loadLastArticles();
        dohvatiKategorije();
        dohvatiIdNarudzbe();
    }

    private void setHomePageHeaderText() {
        textViewNovoUPonudi.setText(R.string.novo_u_ponudi);
    }

    private void removeHomePageHeaderText() {
        textViewNovoUPonudi.setText("");
    }

    /**
     * Finding order Id
     * Firebase connection to "Narudzba" collection
     * Ordering by id descending to find last order
     * Adds one on last order number, that represent order in processing
     *@author Nikola Gluhak
     */
    private void dohvatiIdNarudzbe() {
        database.collection("Narudzba").orderBy("id", Query.Direction.DESCENDING).limit(1)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            final List<Narudzba> narudzbaList = new ArrayList<>();
                            for (DocumentSnapshot documentSnapshot : task.getResult()) {
                                Narudzba narudzba = documentSnapshot.toObject(Narudzba.class);
                                narudzbaDokument = documentSnapshot.getId();
                                narudzba.getId();
                                narudzbaList.add(narudzba);
                            }

                            for (Narudzba n : narudzbaList) {
                                idNarudzba = n.getId();
                            }
                            if (racunId == 0){
                                addIdNarudzba(idNarudzba + 1, 0.00, "restoran");
                            }
                            else {
                                addNarudzba(idNarudzba + 1, 0.00, "restoran", racunId);
                            }
                        } else {
                            Log.d("Error", "Error getting data");
                        }
                    }
                });
    }

    /**
     * Add id narudzba. Adding id value to order.
     *Firebase connection to "Narudzba" collection
     * @param id     the id represents order id number
     * @param status the status defines table status, free, in processing or taken.
     * @author Nikola Gluhak
     *
     */
    public void addIdNarudzba(Integer id, Double cijena, String status) {
        Map<String, Object> idNarudzbe = new Narudzba(id, cijena, status).toMap();
        database.collection("Narudzba")
                .add(idNarudzbe)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {

                    }
                });
    }

    public void addNarudzba(Integer id, Double cijena, String status, Integer racunID) {
        Map<String, Object> idNarudzbe = new Narudzba(id, cijena, status, racunID).toMap();
        database.collection("Narudzba")
                .add(idNarudzbe)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {

                    }
                });
    }

    /**
     * Dohvati kategorije. Creating categories from data gathered from Firestore based on their "Kategorija_id".
     * Creating new adapter for recycler viwe
     * Setting recycler view layout manager from application context
     * Setting the adapter into recycler view
     * @author Nikola Gluhak
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.kosarica:
                Intent intent = new Intent(this, KosaricaActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }

    private void dohvatiKategorije() {
        listChildEx = new HashMap<>();
        database.collection("Kategorija")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<String> kategorijaList = new ArrayList<>();
                            for (DocumentSnapshot documentSnapshot : task.getResult()) {
                                Kategorija kategorija = documentSnapshot.toObject(Kategorija.class);
                                kategorijaList.add(kategorija.getNaziv());
                            }

                            listChildEx.put("Jelovnik", kategorijaList);
                            listChild = listChildEx;
                            listHeader = new ArrayList<>(listChild.keySet());
                            expandableListAdapter = new ExpendableListAdapter(getApplicationContext(), listHeader, listChild);
                            expandableListView.setAdapter(expandableListAdapter);
                            expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                                @Override
                                public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                                    childId = parent.getExpandableListAdapter().getChildId(groupPosition, childPosition) + 1;
                                    v.setSelected(true);
                                    loadArticleList(childId);
                                    drawer.closeDrawers();
                                    return false;
                                }
                            });
                            expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                                @Override
                                public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                                    return false;
                                    }
                            });
                        } else {
                            Log.d("Error", "Error getting data");
                        }
                    }
                });
    }
    /**
     * Load articels List loading articels from Firestore database depending on their "kategorija_id" field.
     * Creating new adapter for recycler viwe
     * Setting recycler view layout manager from application context
     * Setting the adapter into recycler view
     * @param idKategorije    the idKategorije respresents category id number
     * @author Nikola Gluhak
     */
    private void loadArticleList(long idKategorije) {
        database.collection("Artikl")
                .whereEqualTo("kategorija_id", idKategorije)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<Artikl> articlesList = new ArrayList<>();
                            for (DocumentSnapshot documentSnapshot : task.getResult()) {
                                Artikl artikl = documentSnapshot.toObject(Artikl.class);
                                articlesList.add(artikl);
                            }
                            adapter = new ArticleRecyclerAdapter(articlesList, getApplicationContext(), database);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setAdapter(adapter);
                        } else {
                            Log.d("Error", "Error getting data");
                        }
                        removeHomePageHeaderText();
                    }
                });
    }
    /**
     * Load last articles fetching last 7 articles from Firestore collection "Artikl" and orders them by "id" field descending
     * Adding data to articlesList list
     * Creating new adapter for recycler viwe
     * Setting recycler view layout manager from application context
     * Setting the adapter into recycler view
     *@author Nikola Gluhak
     *
     */
    private void loadLastArticles() {
        database.collection("Artikl").orderBy("id", Query.Direction.DESCENDING).limit(3)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<Artikl> articlesList = new ArrayList<>();
                            for (DocumentSnapshot documentSnapshot : task.getResult()) {
                                Artikl artikl = documentSnapshot.toObject(Artikl.class);
                                articlesList.add(artikl);
                            }
                            setHomePageHeaderText();
                            adapter = new ArticleRecyclerAdapter(articlesList, getApplicationContext(), database);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setAdapter(adapter);
                        } else {
                            Log.d("Error", "Error getting data");
                        }
                    }
                });
    }

    /**
     * Click on navigation item marks it
     * @param nv navigation view for drawer
     * @author Nikola Gluhak
     */
    private void setupDrawerContent(NavigationView nv) {
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectDrawerView(item);
                return true;
            }
        });
    }

    private void selectDrawerView(MenuItem item) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}