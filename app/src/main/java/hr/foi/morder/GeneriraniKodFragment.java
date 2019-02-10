package hr.foi.morder;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import hr.foi.morder.model.Racun;


public class GeneriraniKodFragment extends Fragment {

    private FirebaseFirestore database;
    private Integer racunId, maxRacunId = 0;
    private Button kod;
    private String generirani;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.racun_kod, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        kod = view.findViewById(R.id.buttonGeneriraniKod);
        database = FirebaseFirestore.getInstance();

    }

    private void racun(Integer id) {
        database.collection("Racun").whereEqualTo("id", id).get()
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

    public void dohvatiIdRacun() {
        database.collection("Racun").whereEqualTo("status", "dostava").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot documentSnapshot : task.getResult()) {
                                Racun racun = documentSnapshot.toObject(Racun.class);
                                racunId = racun.getId();
                                if (racunId > maxRacunId) {
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