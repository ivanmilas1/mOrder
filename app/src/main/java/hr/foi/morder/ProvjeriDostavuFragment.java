package hr.foi.morder;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import hr.foi.morder.adapters.DostavaRecyclerAdapter;
import hr.foi.morder.model.Racun;

public class ProvjeriDostavuFragment extends Fragment {

    private RecyclerView recyclerView;
    private FirebaseFirestore databaseDostava;
    public DostavaRecyclerAdapter dostavaRecyclerAdapter;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_provjera_narudzbe, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        databaseDostava = FirebaseFirestore.getInstance();

    }

    public void loadRacuni() {
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
                dostavaRecyclerAdapter = new DostavaRecyclerAdapter(getContext(), racunList);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
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
        recyclerView = view.findViewById(R.id.recycler_view_dostava);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(dostavaRecyclerAdapter);
    }
}