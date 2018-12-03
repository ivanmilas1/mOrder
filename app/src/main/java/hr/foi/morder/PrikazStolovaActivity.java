package hr.foi.morder;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import hr.foi.morder.entities.Stol;

import static hr.foi.morder.entities.Stol.stanjeNarudzbe.slobodan;

public class PrikazStolovaActivity extends AppCompatActivity {

    //ako nema narudžbe
    int crvena = Color.rgb(179, 5, 5);

    //ako je narudžba u izradi
    int zuta = Color.rgb(225, 206, 132);

    //ako je narudžba poslužena
    int zelena = Color.rgb(78, 255, 167);

    /*
    if(stol_n.statusNarudzbe==...){
        stol_n.backcolor=...
    }
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prikaz_stolova);

        Stol stol1 = new Stol(1);
        Stol stol2 = new Stol(2);
        Stol stol3 = new Stol(3);
        Stol stol4 = new Stol(4);
        Stol stol5 = new Stol(5);
        Stol stol6 = new Stol(6);
        Stol stol7 = new Stol(7);

        List<Stol> listaStolova = new ArrayList<>();

        listaStolova.add(stol1);
        listaStolova.add(stol2);
        listaStolova.add(stol3);
        listaStolova.add(stol4);
        listaStolova.add(stol5);
        listaStolova.add(stol6);
        listaStolova.add(stol7);

        //u defaultu se svaki stol postavlja na slobodan, stanje se mijenja naknadno
        for (Stol item : listaStolova) {
            item.stanjeNarudzbe = slobodan;
        }

        for (Stol item : listaStolova) {
            int stolId = item.StolID;
            Button btnStol = (Button) findViewById(stolId);
            if (item.stanjeNarudzbe == "slobodan") {
                btnStol.setBackgroundColor(crvena);
            } else if (item.stanjeNarudzbe == "narudzbaUIzradi") {
                btnStol.setBackgroundColor(zuta);
            } else {
                btnStol.setBackgroundColor(zelena);
            }
        }


        //boja stavljena samo za primjer
        Button btnStol1 = (Button) findViewById(R.id.btnStol1);
        btnStol1.setBackgroundColor(crvena);
        btnStol1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), DetaljiNarudzbeActitity.class);
                startActivity(i);
            }
        });
        Button btnStol2 = (Button) findViewById(R.id.btnStol2);
        btnStol2.setBackgroundColor(zuta);
    }
}