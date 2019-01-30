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
import android.widget.Toast;

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

import static android.widget.Toast.LENGTH_LONG;

/**
 * The type Detalji narudzbe activity is used for preview of customers order depending on table.
 * * Set content view activity_detalji_narudzbe layout
 *
 * @author Ivan Milas
 */
public class DetaljiNarudzbeActivity extends AppCompatActivity {
    /**
     * The Lista artikala is used to store articles from Firestore database.
     */
    ArrayList<Artikl> listaArtikala = new ArrayList<>();
    /**
     * The Lista stavki narudžbi for storing order articles.
     */
    ArrayList<StavkaNarudzbe> listaStavkiNarudžbi = new ArrayList<>();
    private FirebaseFirestore database;
    Button buttonIssueBill;
    /**
     * The Btn place order for submiting order an send update to Firestore server.
     */
    Button btnPlaceOrder;
    private ListView listView;
    /**
     * The Stol id represent table that consist order.
     */
    int stolID;
    private DjelatnikPregledRacunaListAdapter djelatnikPregledRacunaStolaListAdapter;

    /**
     * On create defining buttons, intents and variables.
     * Calling gettBill method.
     *
     * @param savedInstanceState
     */
    String stolDocumentID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalji_narudzbe);
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

        buttonIssueBill = findViewById(R.id.btnIzdatiRacun);
        buttonIssueBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTableStatusFree();
            }
        });
    }

    private void setTableStatusFree() {
        database.collection("Stol").whereEqualTo("id", stolID).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot documentSnapshot : task.getResult()) {
                                stolDocumentID = documentSnapshot.getId();
                            }
                            database.collection("Stol").document(stolDocumentID).update("stanje", "slobodan");
                            Intent i = new Intent(getApplicationContext(), PrikazStolovaActivity.class);
                            startActivity(i);
                            Toast.makeText(getApplicationContext(), "Račun je izdan", LENGTH_LONG).show();
                        } else {
                            Log.d("Error", "Error getting data");
                        }
                    }
                });
    }

    /**
     * Gets bill get data from Firestore. Data from "Racun" collection and document which has "stil_id" value that equals stolID.
     * *
     * If error occurs during fetching data, displays error message
     *
     * @author Ivan Milas
     */
    public void getBill() {
        database.collection("Racun")
                .whereEqualTo("stol_id", stolID)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot documentSnapshot : task.getResult()) {
                                Racun racun = documentSnapshot.toObject(Racun.class);
                                getOrders(racun.getId());
                            }
                        } else {
                            Log.d("Error", "Error getting data");
                        }
                    }
                });
    }

    /**
     * Gets orders get data from Firestore. Data from "Narudzba" collection and and document which has "racun_id" value that equals racunID.
     * <p>
     * If error occurs during fetching data, displays error message
     *
     * @param racunID the racun id current bill
     * @author Ivan Milas
     */
    public void getOrders(int racunID) {
        database.collection("Narudzba")
                .whereEqualTo("racun_id", racunID)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot documentSnapshot : task.getResult()) {
                                Narudzba narudzba = documentSnapshot.toObject(Narudzba.class);
                                getOrderItem(narudzba.getId());
                            }
                        } else {
                            Log.d("Error", "Error getting data");
                        }
                    }
                });
    }

    /**
     * Gets order item get data from Firestore. Data from "Stavka narudzbe" collection and document which has "narudzba_id" value that equals orderID.
     * <p>
     * If error occurs during fetching data, displays error message
     *
     * @param orderID the order id current order
     * @author Ivan Milas
     */
    public void getOrderItem(int orderID) {
        database.collection("Stavka narudzbe")
                .whereEqualTo("narudzba_id", orderID)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot documentSnapshot : task.getResult()) {
                                StavkaNarudzbe stavkaNarudzbe = documentSnapshot.toObject(StavkaNarudzbe.class);
                                listaStavkiNarudžbi.add(stavkaNarudzbe);
                                getOrderArticles(stavkaNarudzbe.getArtikl_id());
                            }
                        } else {
                            Log.d("Error", "Error getting data");
                        }
                    }
                });
    }

    /**
     * Gets order articles  from Firestore. Data from "Artikl" collection and document which has "id" value that equals articleID.
     * Adding article to listaArtikala list which consist of articles
     * If error occurs during fetching data, displays error message
     *
     * @param articleID the article id selected article from article list
     * @author Ivan Milas
     */
    public void getOrderArticles(int articleID) {
        database.collection("Artikl")
                .whereEqualTo("id", articleID)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot documentSnapshot : task.getResult()) {
                                Artikl artikl = documentSnapshot.toObject(Artikl.class);
                                listaArtikala.add(artikl);
                            }
                            listView = findViewById(R.id.customListView);
                            djelatnikPregledRacunaStolaListAdapter = new DjelatnikPregledRacunaListAdapter
                                    (getApplicationContext(), listaArtikala, listaStavkiNarudžbi);
                            listView.setAdapter(djelatnikPregledRacunaStolaListAdapter);
                        } else {
                            Log.d("Error", "Error getting data");
                        }
                    }
                });
    }
}