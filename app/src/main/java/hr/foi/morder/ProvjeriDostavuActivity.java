package hr.foi.morder;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import hr.foi.morder.adapters.DostavaRecyclerAdapter;
import hr.foi.morder.model.Racun;

public class ProvjeriDostavuActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FirebaseFirestore databaseDostava;
    public DostavaRecyclerAdapter dostavaRecyclerAdapter;
    private List<Racun> racunList;
    String nacinRada = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provjera_narudzbe);
        racunList = new ArrayList<>();
        buildRecyclerView();
        databaseDostava = FirebaseFirestore.getInstance();
        loadRacuni();
        Intent intent = getIntent();
        nacinRada = intent.getStringExtra("nacinRada");
    }

    private void loadRacuni() {
        databaseDostava.collection("Racun").whereEqualTo("status","dostava").get()
            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                List<Racun> racunList = new ArrayList<>();
                for(DocumentSnapshot documentSnapshot:task.getResult()){
                Racun racun = documentSnapshot.toObject(Racun.class);
                racun.getId();
                racun.getStatus();
                racunList.add(racun);
                }
                dostavaRecyclerAdapter = new DostavaRecyclerAdapter(getApplicationContext(), racunList, nacinRada);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(dostavaRecyclerAdapter);
                }
                else {
                    Log.d("Error", "Error getting data");
                }
                }
            });
    }

    public void buildRecyclerView(){
        recyclerView = findViewById(R.id.recycler_view_dostava);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(dostavaRecyclerAdapter);
    }
}