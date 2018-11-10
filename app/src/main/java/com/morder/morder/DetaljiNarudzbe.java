package com.morder.morder;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DetaljiNarudzbe extends AppCompatActivity {


    private ArrayList<String> data = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalji_narudzbe);
        ListView lv = (ListView) findViewById(R.id.listview);
        generateListContent();
        lv.setAdapter(new MyListAdaper(this, R.layout.list_item, data));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });



    }

    private void generateListContent() {


            for(int i = 0; i < 7; i++) {

                data.add("This is row number " + i);


            }

        }
    private class MyListAdaper extends ArrayAdapter<String> {

        private int layout;

        private List<String> mObjects;

        private MyListAdaper(Context context, int resource, List<String> objects) {

            super(context, resource, objects);

            mObjects = objects;

            layout = resource;

        }



        @Override

        public View getView(final int position, View convertView, ViewGroup parent) {

            ViewHolder mainViewholder = null;

            if(convertView == null) {

                LayoutInflater inflater = LayoutInflater.from(getContext());

                convertView = inflater.inflate(layout, parent, false);

                ViewHolder viewHolder = new ViewHolder();

                viewHolder.naziv = (TextView) convertView.findViewById(R.id.list_item_naziv);
                viewHolder.kolicina = (TextView) convertView.findViewById(R.id.list_item_kolicina);
                viewHolder.cijena = (TextView) convertView.findViewById(R.id.list_item_cijena);
                viewHolder.dodaj = (Button) convertView.findViewById(R.id.list_item_btn_povecaj);
                viewHolder.ukloni = (Button) convertView.findViewById(R.id.list_item_btn_umanji);

                convertView.setTag(viewHolder);

            }

            mainViewholder = (ViewHolder) convertView.getTag();

            mainViewholder.dodaj.setOnClickListener(new View.OnClickListener() {

                @Override

                public void onClick(View v) {

                    Toast.makeText(getContext(), "Dodali ste ješ jedan proizvod", Toast.LENGTH_SHORT).show();

                }

            });
            mainViewholder.ukloni.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(getContext(),"Uklonili ste jedan proizvod",Toast.LENGTH_SHORT).show();
                }
            });

            mainViewholder.naziv.setText(getItem(position));



            return convertView;

        }

    }
    public class ViewHolder {

        TextView naziv;
        Button dodaj;
        TextView kolicina;
        Button ukloni;
        TextView cijena;

    }

}
