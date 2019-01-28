package hr.foi.morder.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

import hr.foi.morder.DodavanjeRadnikaActivity;
import hr.foi.morder.R;
import hr.foi.morder.model.Djelatnik;

public class DjelatnikRecyclerAdapter extends RecyclerView.Adapter<DjelatnikRecyclerAdapter.DjelatnikViewHoleder>  {
    private Context context;
    private List<Djelatnik> DjelatnikList;
    private FirebaseFirestore database;

    public DjelatnikRecyclerAdapter(Context context, List<Djelatnik> djelatnikList, FirebaseFirestore database) {
        this.context = context;
        this.DjelatnikList = djelatnikList;
        this.database = database;
    }

    @NonNull
    @Override
    public DjelatnikViewHoleder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_view_dodavanje_radnika, null);
        return new DjelatnikViewHoleder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DjelatnikViewHoleder djelatnikViewHoleder, int i) {
        Integer tipDjelatnik;
        String tipKorisnika = null;
        final Djelatnik djelatnik = DjelatnikList.get(i);
        tipDjelatnik = djelatnik.getTipDjelatnikaId();
        switch (tipDjelatnik) {
            case 1:
                tipKorisnika = "Konobar";
                break;
            case 2:
                tipKorisnika = "Voditelj";
                break;
            case 3:
                tipKorisnika = "Administrator";
                break;
        }
        djelatnikViewHoleder.textViewIme.setText(djelatnik.getImePrezime());
        djelatnikViewHoleder.textViewDjelatnikId.setText(tipKorisnika);
        djelatnikViewHoleder.textViewEmail.setText(djelatnik.getEmail());
    }

    @Override
    public int getItemCount() {
        return DjelatnikList.size();
    }

    public class DjelatnikViewHoleder extends RecyclerView.ViewHolder{
        View view;
        public TextView textViewEmail;
        public TextView textViewIme;
        public TextView textViewDjelatnikId;

        public TextView textViewDjelatnikIme;
        public TextView textViewDjelatnikEmail;
        public TextView textViewDjelatnikTip;

        public DjelatnikViewHoleder(@NonNull View itemView) {

            super(itemView);
            view = itemView;
            textViewEmail = itemView.findViewById(R.id.textViewEmail);
            textViewIme = itemView.findViewById(R.id.textViewName);
            textViewDjelatnikId = itemView.findViewById(R.id.textViewTipKor);

            textViewDjelatnikIme = itemView.findViewById(R.id.input_name);
            textViewDjelatnikEmail = itemView.findViewById(R.id.input_email);

            view.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View view) {
                    Intent myIntent = new Intent(context, DodavanjeRadnikaActivity.class);
                    myIntent.putExtra("Ime", textViewIme.getText().toString());
                    myIntent.putExtra("Azuriranje", "a");
                    myIntent.putExtra("Email", textViewEmail.getText().toString());
                    myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(myIntent);
                }
            });
        }
    }
}