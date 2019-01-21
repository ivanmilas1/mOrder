package hr.foi.morder.adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

import hr.foi.morder.R;
import hr.foi.morder.model.Korisnik;

public class KorisnikRecyclerAdapter extends RecyclerView.Adapter<KorisnikRecyclerAdapter.KorisnikViewHoleder>  {

    private Context context;
    private List<Korisnik> KorisnikList;
    private FirebaseFirestore database;

    public KorisnikRecyclerAdapter(Context context, List<Korisnik> korisnikList, FirebaseFirestore database) {
        this.context = context;
        this.KorisnikList = korisnikList;
        this.database = database;
    }



    @NonNull
    @Override
    public KorisnikViewHoleder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_view_dodavanje_radnika, null);
        return new KorisnikViewHoleder(view);
    }

    @Override
        public void onBindViewHolder(@NonNull KorisnikViewHoleder korisnikViewHoleder, int i) {

       Korisnik korisnik = KorisnikList.get(i);

       korisnikViewHoleder.textViewIme.setText(korisnik.getImePrezime());
       korisnikViewHoleder.textViewKorime.setText(korisnik.getAdresaPrebivalista());






        }

    @Override
    public int getItemCount() {
        return KorisnikList.size();
    }

    class KorisnikViewHoleder extends RecyclerView.ViewHolder{

        TextView textViewTipKorisnika;
        TextView textViewIme;
        TextView textViewKorime;

        public KorisnikViewHoleder(@NonNull View itemView) {
            super(itemView);

            textViewTipKorisnika = itemView.findViewById(R.id.textViewTipKor);
            textViewIme = itemView.findViewById(R.id.textViewName);
            textViewKorime = itemView.findViewById(R.id.textViewVersion);
        }
    }

}


