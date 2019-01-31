package hr.foi.morder;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import com.google.firebase.firestore.QuerySnapshot;

import hr.foi.morder.model.Racun;


public class GeneriraniKodActivity extends AppCompatActivity {

    private FirebaseFirestore database;
    private Integer racunId;
    private Integer maxRacunId = 0;
    private Integer generiraniKod;
    private Button kod;
    private String generirani;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.racun_kod);
        kod = findViewById(R.id.buttonKod);
        database = FirebaseFirestore.getInstance();
        dohvatiIdRacun();


    }

    private void racun(Integer id) {
        database.collection("Racun").whereEqualTo("id", id)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot documentSnapshot : task.getResult()) {
                                Racun racun = documentSnapshot.toObject(Racun.class);
                                generirani = racun.getKod().toString();

                            }
                            kod.setText(generirani);
                        } else {
                            Log.d("Error", "Error getting data");
                        }
                    }
                });
    }

    private void dohvatiIdRacun() {
        database.collection("Racun").whereEqualTo("status", "dostava").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot documentSnapshot : task.getResult()) {
                                Racun racun = documentSnapshot.toObject(Racun.class);
                                racunId = racun.getId();
                                if(racunId >maxRacunId){
                                    maxRacunId = racunId;
                                }
                            }
                            racun(maxRacunId);
                        } else {
                            Log.d("Error", "Error getting data");
                        }
                    }
                });
    }

}