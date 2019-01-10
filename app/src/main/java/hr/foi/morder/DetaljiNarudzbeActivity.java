package hr.foi.morder;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import hr.foi.morder.adapters.DjelatnikPregledRacunaListAdapter;
import hr.foi.morder.model.Artikl;
import hr.foi.morder.model.Narudzba;
import hr.foi.morder.model.Racun;
import hr.foi.morder.model.StavkaNarudzbe;

public class DetaljiNarudzbeActivity extends AppCompatActivity {
    ArrayList<Artikl> listaArtikala = new ArrayList<>();
    ArrayList<StavkaNarudzbe> listaStavkiNarudžbi = new ArrayList<>();
    private FirebaseFirestore database;
    Button btnPlaceOrder;
    private ListView listView;
    private DjelatnikPregledRacunaListAdapter djelatnikPregledRacunaStolaListAdapter;
    int stolID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalji_narudzbe2);
        Intent intent = getIntent();
        stolID = Integer.parseInt(intent.getStringExtra("stolID"));
        database = FirebaseFirestore.getInstance();
        getBill();

        btnPlaceOrder = findViewById(R.id.btnPlaceOrder);
        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void getBill() {
        database.collection("Racun")
                .whereEqualTo("stol_id", stolID)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(DocumentSnapshot documentSnapshot: task.getResult()){
                                Racun racun = documentSnapshot.toObject(Racun.class);
                                getOrders(racun.getId());
                            }
                        }
                        else{
                            Log.d("Error", "Error getting data");
                        }
                    }
                });
    }
    public void getOrders(int racunID) {
        database.collection("Narudzba")
                .whereEqualTo("racun_id", racunID)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(DocumentSnapshot documentSnapshot: task.getResult()){
                                Narudzba narudzba = documentSnapshot.toObject(Narudzba.class);
                                getOrderItem(narudzba.getId());
                            }
                        }
                        else{
                            Log.d("Error", "Error getting data");
                        }
                    }
                });
    }

    public void getOrderItem(int orderID) {
        database.collection("Stavka narudzbe")
                .whereEqualTo("narudzba_id", orderID)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(DocumentSnapshot documentSnapshot: task.getResult()){
                                StavkaNarudzbe stavkaNarudzbe = documentSnapshot.toObject(StavkaNarudzbe.class);
                                listaStavkiNarudžbi.add(stavkaNarudzbe);
                                getOrderArticles(stavkaNarudzbe.getArtikl_id());
                            }
                        }
                        else{
                            Log.d("Error", "Error getting data");
                        }
                    }
                });
    }

    public void getOrderArticles(int articleID) {
        database.collection("Artikl")
                .whereEqualTo("id", articleID)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(DocumentSnapshot documentSnapshot: task.getResult()){
                                Artikl artikl = documentSnapshot.toObject(Artikl.class);
                                listaArtikala.add(artikl);
                            }
                            listView = findViewById(R.id.customListView);
                            djelatnikPregledRacunaStolaListAdapter = new DjelatnikPregledRacunaListAdapter
                                    (getApplicationContext(), listaArtikala, listaStavkiNarudžbi);
                            listView.setAdapter(djelatnikPregledRacunaStolaListAdapter);
                        }
                        else{
                            Log.d("Error", "Error getting data");
                        }
                    }
                });
    }
}