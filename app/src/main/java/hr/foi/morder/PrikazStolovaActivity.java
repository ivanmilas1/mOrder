package hr.foi.morder;

import android.content.Intent;
import android.graphics.Color;
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
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import hr.foi.morder.adapters.TableRecyclerAdapter;
import hr.foi.morder.entities.Stol;

import static android.support.v4.view.GravityCompat.START;

public class PrikazStolovaActivity extends AppCompatActivity {

    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigation;
    private RecyclerView recyclerView;
    private FirebaseFirestore database;
    private TableRecyclerAdapter adapter;

    DatabaseReference dbStol;

    //ako nema narudžbe
    int crvena = Color.rgb(179, 5, 5);

    //ako je narudžba u izradi
    int zuta = Color.rgb(225, 206, 132);

    //ako je narudžba poslužena
    int zelena = Color.rgb(78, 255, 167);

    /*
    if(stol_n.statusNarudzbe==...){
        stol_n.backcolor=...
    }

    ValueEventListener valueEventListener=new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prikaz_stolova);

        /*
        dbStol=FirebaseDatabase.getInstance().getReference("Stol");
        dbStol.addListenerForSingleValueEvent(valueEventListener);

        //učitaj sve
        Query upit=FirebaseDatabase.getInstance().getReference("Stol").orderByChild("id").equalTo("1");

        //učitaj s određenim id-jem
        upit.addListenerForSingleValueEvent(valueEventListener);
        */


        Stol stol1 = new Stol(1);
        Stol stol2 = new Stol(2);
        Stol stol3 = new Stol(3);
        Stol stol4 = new Stol(4);
        Stol stol5 = new Stol(5);
        Stol stol6 = new Stol(6);
        Stol stol7 = new Stol(7);

        List<Stol> listaStolova = new ArrayList<>();

        listaStolova.add(stol1);
        listaStolova.add(stol2);
        listaStolova.add(stol3);
        listaStolova.add(stol4);
        listaStolova.add(stol5);
        listaStolova.add(stol6);
        listaStolova.add(stol7);

        //u defaultu se svaki stol postavlja na slobodan, stanje se mijenja naknadno
        for (Stol item : listaStolova) {
            item.stanjeNarudzbe = "slobodan";
        }

        for (Stol item : listaStolova) {
            int ids = item.StolID;
            Button btnStol = (Button) findViewById(ids);
            if (item.stanjeNarudzbe == "slobodan") {
                btnStol.setBackgroundColor(crvena);
            } else if (item.stanjeNarudzbe == "narudzbaUIzradi") {
                btnStol.setBackgroundColor(zuta);
            } else {
                btnStol.setBackgroundColor(zelena);
            }
            btnStol.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(), DetaljiNarudzbeActitity.class);
                    startActivity(i);
                }
            });
        }
    }

    private void loadDesks(){
        database.collection("Stol").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    List<Stol> stolList=new ArrayList<>();
                    for (DocumentSnapshot snapshot: task.getResult()){
                        Stol stol=snapshot.toObject(Stol.class);
                        stol.getStolID();
                        stol.getStanjeNarudzbe();
                        stol.getKategorijaId();
                        stolList.add(stol);
                    }
                    adapter=new TableRecyclerAdapter(stolList, getApplicationContext(), database);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(adapter);
                }
                else{
                    Log.d("Error", "Error getting data");
                }
            }
        });
    };

    private void setupDrawerContent(NavigationView nv){
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                return false;
            }

            public boolean onNavigationItemSelected() {
                selectDrawerView();
                return true;
            }
        });
    }

    private void selectDrawerView(){
        loadDesks();
        drawer.closeDrawer(START);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}