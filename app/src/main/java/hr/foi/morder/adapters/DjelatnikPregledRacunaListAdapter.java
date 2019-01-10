package hr.foi.morder.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import hr.foi.morder.R;
import hr.foi.morder.model.Artikl;
import hr.foi.morder.model.StavkaNarudzbe;

public class DjelatnikPregledRacunaListAdapter extends BaseAdapter {
    public ArrayList<Artikl> listaArtikala;
    public ArrayList<StavkaNarudzbe> listaStavkiNarudžbi;
    private Context context;

    public DjelatnikPregledRacunaListAdapter(Context context, ArrayList<Artikl> listaArtikala, ArrayList<StavkaNarudzbe> listaStavkiNarudžbi) {
        this.context = context;
        this.listaArtikala = listaArtikala;
        this.listaStavkiNarudžbi = listaStavkiNarudžbi;
    }

    @Override
    public int getCount() {
        return listaArtikala.size();
    }

    @Override
    public Artikl getItem(int position) {
        return listaArtikala.get(position);
    }

    public StavkaNarudzbe getItemKolicina(int position) {
        return listaStavkiNarudžbi.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row;
        final DjelatnikPregledRacunaListViewHolder djelatnikPregledRacunaListViewHolder;
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.custom_listview, parent, false);
            djelatnikPregledRacunaListViewHolder = new DjelatnikPregledRacunaListViewHolder();
            djelatnikPregledRacunaListViewHolder.btnDodaj = row.findViewById(R.id.ib_addnew);
            djelatnikPregledRacunaListViewHolder.btnSmanji = row.findViewById(R.id.ib_remove);
            djelatnikPregledRacunaListViewHolder.etKolicina = row.findViewById(R.id.editTextQuantity);
            djelatnikPregledRacunaListViewHolder.ivSlika = row.findViewById(R.id.ivProduct);
            djelatnikPregledRacunaListViewHolder.tvNaziv = row.findViewById(R.id.tvProductName);
            djelatnikPregledRacunaListViewHolder.tvCijena = row.findViewById(R.id.tvPrice);
            row.setTag(djelatnikPregledRacunaListViewHolder);
        } else {
            row = convertView;
            djelatnikPregledRacunaListViewHolder = (DjelatnikPregledRacunaListViewHolder) row.getTag();
        }
        final Artikl artikl = getItem(position);
        final StavkaNarudzbe stavkaNarudzbe = getItemKolicina(position);

        djelatnikPregledRacunaListViewHolder.tvNaziv.setText(artikl.naziv);
        djelatnikPregledRacunaListViewHolder.tvCijena.setText(artikl.jedinicna_cijena + "Kn");
        Picasso.with(context).load(artikl.slika).into(djelatnikPregledRacunaListViewHolder.ivSlika);
        djelatnikPregledRacunaListViewHolder.etKolicina.setText(stavkaNarudzbe.kolicina + "");
        djelatnikPregledRacunaListViewHolder.btnDodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setQuantity(stavkaNarudzbe, djelatnikPregledRacunaListViewHolder.etKolicina, 1);

            }
        });
        djelatnikPregledRacunaListViewHolder.btnSmanji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setQuantity(stavkaNarudzbe, djelatnikPregledRacunaListViewHolder.etKolicina, -1);
            }
        });
        return row;
    }

    private void setQuantity(StavkaNarudzbe stavkaNarudzbe, EditText etKolicina, int value) {
        if (value > 0) {
            stavkaNarudzbe.kolicina += 1;
        } else {
            if (stavkaNarudzbe.kolicina > 0) {
                stavkaNarudzbe.kolicina -= 1;
            }

        }
        etKolicina.setText(stavkaNarudzbe.kolicina + "");
    }
}