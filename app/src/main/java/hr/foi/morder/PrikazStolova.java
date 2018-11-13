package hr.foi.morder;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import hr.foi.morder.Entities.Stol;

public class PrikazStolova extends AppCompatActivity {

    //ako nema narudžbe
    int crvena=Color.rgb(179,5,5);

    //ako je narudžba u izradi
    int zuta=Color.rgb(225,206,132);

    //ako je narudžba poslužena
    int zelena=Color.rgb(78,255,167);

    /*
    if(stol_n.statusNarudzbe==...){
        stol_n.backcolor=...
    }
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prikaz_stolova);

        Stol stol1 = new Stol();
        Stol stol2 = new Stol();
        Stol stol3 = new Stol();
        Stol stol4 = new Stol();
        Stol stol5 = new Stol();
        Stol stol6 = new Stol();
        Stol stol7 = new Stol();

        List<Stol> listaStolova=new List<Stol>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(@androidx.annotation.Nullable Object o) {
                return false;
            }

            @androidx.annotation.NonNull
            @Override
            public Iterator<Stol> iterator() {
                return null;
            }

            @androidx.annotation.Nullable
            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(@androidx.annotation.Nullable T[] a) {
                return null;
            }

            @Override
            public boolean add(Stol stol) {
                return false;
            }

            @Override
            public boolean remove(@Nullable Object o) {
                return false;
            }

            @Override
            public boolean containsAll(@androidx.annotation.NonNull Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(@androidx.annotation.NonNull Collection<? extends Stol> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, @androidx.annotation.NonNull Collection<? extends Stol> c) {
                return false;
            }

            @Override
            public boolean removeAll(@androidx.annotation.NonNull Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(@androidx.annotation.NonNull Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public Stol get(int index) {
                return null;
            }

            @Override
            public Stol set(int index, Stol element) {
                return null;
            }

            @Override
            public void add(int index, Stol element) {

            }

            @Override
            public Stol remove(int index) {
                return null;
            }

            @Override
            public int indexOf(@androidx.annotation.Nullable Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(@androidx.annotation.Nullable Object o) {
                return 0;
            }

            @androidx.annotation.NonNull
            @Override
            public ListIterator<Stol> listIterator() {
                return null;
            }

            @androidx.annotation.NonNull
            @Override
            public ListIterator<Stol> listIterator(int index) {
                return null;
            }

            @androidx.annotation.NonNull
            @Override
            public List<Stol> subList(int fromIndex, int toIndex) {
                return null;
            }
        };
        listaStolova.add(stol1);
        listaStolova.add(stol2);
        listaStolova.add(stol3);
        listaStolova.add(stol4);
        listaStolova.add(stol5);
        listaStolova.add(stol6);
        listaStolova.add(stol7);

        //u defaultu se svaki stol postavlja na slobodan, stanje se mijenja naknadno
        for (Stol item:listaStolova) {
             item.stanjeNarudzbe=slobodan;
        }

        //boja stavljena samo za primjer
        Button btnStol1=(Button)findViewById(R.id.btnStol1);
        btnStol1.setBackgroundColor(crvena);
        btnStol1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(v.getContext(),StanjeNarudzbePoStolu.class);
                startActivity(i);
            }
        });

        Button btnStol2=(Button)findViewById(R.id.btnStol2);
        btnStol2.setBackgroundColor(zuta);

    }
}
