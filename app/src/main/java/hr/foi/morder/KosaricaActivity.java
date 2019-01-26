package hr.foi.morder;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.List;
import hr.foi.morder.adapters.KosaricaAdapter;
import hr.foi.morder.model.StavkaNarudzbe;

public class KosaricaActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    public KosaricaAdapter kosaricaAdapter;
    public List<StavkaNarudzbe> stavkaNarudzbeList;
    private Button naruci;
    private Button ponisti;

    private FirebaseFirestore databaseStavkaNarudzbe;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kosarica);
        stavkaNarudzbeList = new ArrayList<>();
        buildRecyclerView();
        databaseStavkaNarudzbe = FirebaseFirestore.getInstance();
        loadStavkeKosarice();


    }
    Integer i=1 ;

    private void loadStavkeKosarice() {
        databaseStavkaNarudzbe.collection("Stavka narudzbe").whereEqualTo("narudzba_id", i).get()
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
                                stavkaNarudzbe.getJedinicna_cijena();
                                stavkaNarudzbeList.add(stavkaNarudzbe);

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

    public void buildRecyclerView(){

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewKosarica);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(kosaricaAdapter);
    }
}
