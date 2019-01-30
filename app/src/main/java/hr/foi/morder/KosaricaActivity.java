package hr.foi.morder;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import hr.foi.morder.adapters.KosaricaAdapter;
import hr.foi.morder.model.Narudzba;
import hr.foi.morder.model.StavkaNarudzbe;

public class KosaricaActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    public KosaricaAdapter kosaricaAdapter;
    public List<StavkaNarudzbe> stavkaNarudzbeList;
    private Button naruci;
    public Integer brojNarudzbe = 0;
    public Double ukupnaCijena = 0.0;
    private FirebaseFirestore databaseStavkaNarudzbe;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kosarica);
        stavkaNarudzbeList = new ArrayList<>();
        buildRecyclerView();
        databaseStavkaNarudzbe = FirebaseFirestore.getInstance();
        naruci = findViewById(R.id.buttonNaruci);
        zadnjiElement();
        naruci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dovrsiNarudzbu(); ne dodaje brojNarudzbe ni ukupnaCijena na firestore
            }
        });
    }

    //Narudžba koja će sadržavati elemente košarice
    private void dovrsiNarudzbu() {
        Map<String, Object> narudzba = new Narudzba(brojNarudzbe, ukupnaCijena, 0).toMap();
        databaseStavkaNarudzbe.collection("Narudzba")
                .add(narudzba)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                    }
                });
    }

    //Dohvaćanje svih stavki koje je korisnik odabrao, spremaju se prvo na firestore te se onda dobavljaju i prikazuju, nisu vezani za narudzbu
    private void loadStavkeKosarice() {
        databaseStavkaNarudzbe.collection("Stavka narudzbe").whereEqualTo("narudzba_id", brojNarudzbe).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<StavkaNarudzbe> stavkaNarudzbeList = new ArrayList<>();
                            for (DocumentSnapshot documentSnapshot : task.getResult()) {
                                StavkaNarudzbe stavkaNarudzbe = documentSnapshot.toObject(StavkaNarudzbe.class);
                                stavkaNarudzbe.getArtikl_id();
                                stavkaNarudzbe.getCijena();
                                stavkaNarudzbe.getKolicina();
                                stavkaNarudzbeList.add(stavkaNarudzbe);
                                ukupnaCijena = ukupnaCijena + stavkaNarudzbe.getCijena();
                            }
                            kosaricaAdapter = new KosaricaAdapter(getApplicationContext(), stavkaNarudzbeList, databaseStavkaNarudzbe);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager((getApplicationContext()));
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setAdapter(kosaricaAdapter);
                        } else {
                            Log.d("Error", "Error getting data");
                        }
                    }
                });
    }

    //Dohvat zadnjeg id narudzbe, pod tim id se spremaju artikli koji su u kosarici
    private void zadnjiElement() {
        databaseStavkaNarudzbe.collection("Narudzba").orderBy("id", Query.Direction.DESCENDING).limit(1).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot documentSnapshot : task.getResult()) {
                                Narudzba narudzba = documentSnapshot.toObject(Narudzba.class);
                                brojNarudzbe = narudzba.getId();
                                //brojNarudzbe = brojNarudzbe + 1;
                                loadStavkeKosarice();
                            }
                        } else {
                            Log.d("Error", "Error getting data");
                        }
                    }
                });
    }

    //Recycler view za prikaz elemenata košarice
    public void buildRecyclerView() {
        recyclerView = findViewById(R.id.recyclerViewKosarica);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(kosaricaAdapter);
    }
}