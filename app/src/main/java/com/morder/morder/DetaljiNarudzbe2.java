package com.morder.morder;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class DetaljiNarudzbe2 extends AppCompatActivity
{
    private ListView listView;
    private ListAdapter listAdapter;
    ArrayList<Artikl> artikls = new ArrayList<>();
    Button btnPlaceOrder;
    ArrayList<Artikl> artiklsNarudzba = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalji_narudzbe2);
        getArtikli();
        listView = (ListView) findViewById(R.id.customListView);
        listAdapter = new ListAdapter(this,artikls);
        listView.setAdapter(listAdapter);
        btnPlaceOrder = (Button) findViewById(R.id.btnPlaceOrder);
        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    public void getArtikli(){
        artikls.add(new Artikl("Coca-Cola",15.00,R.mipmap.ic_launcher));
        artikls.add(new Artikl("Pivo",12.00,R.mipmap.untitled));
    }
}
