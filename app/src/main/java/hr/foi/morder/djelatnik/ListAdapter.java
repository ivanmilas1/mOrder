package hr.foi.morder.djelatnik;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;

import java.util.ArrayList;

import hr.foi.morder.R;
import hr.foi.morder.model.Artikl2;

public class ListAdapter extends BaseAdapter
{
    public ArrayList<Artikl2> listaArtikala;
    private Context context;

    public ListAdapter(Context context,ArrayList<Artikl2> listaArtikala)
    {
        this.context = context;
        this.listaArtikala = listaArtikala;
    }

    @Override
    public int getCount() {
        return listaArtikala.size();
    }

    @Override
    public Artikl2 getItem(int position) {
        return  listaArtikala.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row;
        final ListViewHolder listViewHolder;
        if(convertView == null)
        {
            LayoutInflater layoutInflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.custom_listview,parent,false);
            listViewHolder = new ListViewHolder();
            listViewHolder.btnDodaj = row.findViewById(R.id.ib_addnew);
            listViewHolder.btnSmanji = row.findViewById(R.id.ib_remove);
            listViewHolder.etKolicina = row.findViewById(R.id.editTextQuantity);
            listViewHolder.ivSlika = row.findViewById(R.id.ivProduct);
            listViewHolder.tvNaziv = row.findViewById(R.id.tvProductName);
            listViewHolder.tvCijena = row.findViewById(R.id.tvPrice);
            row.setTag(listViewHolder);
        }
        else
            {
            row = convertView;
            listViewHolder = (ListViewHolder) row.getTag();
            }
        final Artikl2 artikl2 = getItem(position);

        listViewHolder.tvNaziv.setText(artikl2.Naziv);
        listViewHolder.tvCijena.setText(artikl2.Cijena+"Kn");
        listViewHolder.ivSlika.setImageResource(artikl2.Slika);
        listViewHolder.etKolicina.setText(artikl2.KolicinaKosarica + "" );
        listViewHolder.btnDodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Kolicina(position,listViewHolder.etKolicina,1);

            }
        });
        listViewHolder.btnSmanji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Kolicina(position,listViewHolder.etKolicina,-1);
            }
        });
        return row;
    }
    private void Kolicina(int position, EditText etKolicina, int value) {

        Artikl2 artikl2 = getItem(position);
        if(value > 0)
        {
            artikl2.KolicinaKosarica = artikl2.KolicinaKosarica + 1 ;
        }
        else
        {
            if(artikl2.KolicinaKosarica > 0)
            {
                artikl2.KolicinaKosarica = artikl2.KolicinaKosarica - 1;
            }

        }
        etKolicina.setText(artikl2.KolicinaKosarica+"");
    }
}