package hr.foi.morder;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import hr.foi.morder.adapters.ArticleRecyclerAdapter;
import hr.foi.morder.model.Artikl;


public class NarucivanjeActivity extends AppCompatActivity {

    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigation;
    private RecyclerView recyclerView;
    private FirebaseFirestore database;
    private CollectionReference collection;
    private ListenerRegistration firestoreListener;
    private ArticleRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article);

        drawer = findViewById(R.id.drawer);
        toggle = new ActionBarDrawerToggle(this, drawer, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigation = findViewById(R.id.nv);
        setupDrawerContent(navigation);

        recyclerView = findViewById(R.id.article_recycler);
        database = FirebaseFirestore.getInstance();

    }

    private void loadArticleListDrinks() {
        database.collection("Artikl")
                .whereEqualTo("kategorija_id", 1)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            List<Artikl> articlesList = new ArrayList<>();
                            for(DocumentSnapshot documentSnapshot: task.getResult()){
                                Artikl artikl = documentSnapshot.toObject(Artikl.class);
                                artikl.getNaziv();
                                artikl.getJedinicna_cijena();
                                artikl.getSlika();
                                articlesList.add(artikl);
                            }
                            adapter = new ArticleRecyclerAdapter(articlesList, getApplicationContext(), database);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setAdapter(adapter);
                        }
                        else{
                            Log.d("Error", "Error getting data");
                        }
                    }
                });
    }

    private void loadArticleListFood() {
        database.collection("Artikl")
                .whereEqualTo("kategorija_id", 2)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            List<Artikl> articlesList = new ArrayList<>();
                            for(DocumentSnapshot documentSnapshot: task.getResult()){
                                Artikl artikl = documentSnapshot.toObject(Artikl.class);
//                                artikl.getNaziv();
//                                artikl.getJedinicna_cijena();
//                                artikl.getSlika();
                                articlesList.add(artikl);
                            }
                            adapter = new ArticleRecyclerAdapter(articlesList, getApplicationContext(), database);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setAdapter(adapter);
                        }
                        else{
                            Log.d("Error", "Error getting data");
                        }
                    }
                });
    }

    private void setupDrawerContent(NavigationView nv){
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectDrawerView(item);
                return true;
            }
        });
    }

    private void selectDrawerView(MenuItem item){
        switch (item.getItemId()){
            case R.id.pica:
                loadArticleListDrinks();
                drawer.closeDrawer(GravityCompat.START);
                break;

            case R.id.jela:
                loadArticleListFood();
                drawer.closeDrawer(GravityCompat.START);
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}