package com.morder.morder;

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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QuerySnapshot;
import com.morder.morder.adapters.ArticleRecyclerAdapter;
import com.morder.morder.entities.Article;

import java.util.ArrayList;
import java.util.List;


public class ArticleActivity extends AppCompatActivity {

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

        loadArticleList();

        firestoreListener = database.collection("Artikl")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                        if (e != null) {
                            Log.e("Error", "Listen failed!", e);
                            return;
                        }

                        List<Article> articlesList = new ArrayList<>();

                        for(DocumentSnapshot doc: documentSnapshots){
                            Article article = doc.toObject(Article.class);
                            article.getNaziv();


                            articlesList.add(article);
                        }

                        adapter = new ArticleRecyclerAdapter(articlesList, getApplicationContext(), database);
                        recyclerView.setAdapter(adapter);

                    }
                });
    }

    private void loadArticleList() {
        database.collection("Artikl")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            List<Article> articlesList = new ArrayList<>();
                            for(DocumentSnapshot documentSnapshot: task.getResult()){
                                Article article = documentSnapshot.toObject(Article.class);
                                article.getNaziv();


                                articlesList.add(article);
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
            case R.id.kontakt:
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
