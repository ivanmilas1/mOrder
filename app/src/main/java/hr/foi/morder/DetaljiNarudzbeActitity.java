package hr.foi.morder;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import hr.foi.morder.djelatnik.ListAdapter;
import hr.foi.morder.model.Artikl2;

public class DetaljiNarudzbeActitity extends AppCompatActivity
{
    private ListView listView;
    private ListAdapter listAdapter;
    ArrayList<Artikl2> artikl2s = new ArrayList<>();
    Button btnPlaceOrder;
    ArrayList<Artikl2> artiklsNarudzba = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalji_narudzbe2);
        getArtikli();
        listView = (ListView) findViewById(R.id.customListView);
        listAdapter = new ListAdapter(this, artikl2s);
        listView.setAdapter(listAdapter);
        btnPlaceOrder = (Button) findViewById(R.id.btnPlaceOrder);
        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    public void getArtikli(){
        artikl2s.add(new Artikl2("Coca-Cola",15.00,R.mipmap.ic_launcher));
        artikl2s.add(new Artikl2("Pivo",12.00,R.mipmap.untitled));
    }
}