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

/**
 * The type Djelatnik recycler adapter.
 */
public class DjelatnikRecyclerAdapter extends RecyclerView.Adapter<DjelatnikRecyclerAdapter.DjelatnikViewHoleder>  {
    private Context context;
    private List<Djelatnik> DjelatnikList;
    private FirebaseFirestore database;

    /**
     * Instantiates a new Djelatnik recycler adapter.
     *
     * @param context       the context , accepts activity context
     * @param djelatnikList the djelatnik list, List of waiters
     * @param database      the database, database parameter
     */
    public DjelatnikRecyclerAdapter(Context context, List<Djelatnik> djelatnikList, FirebaseFirestore database) {
        this.context = context;
        this.DjelatnikList = djelatnikList;
        this.database = database;
    }

    /**
     *
     * @param viewGroup
     * @param i current position inside the view holder
     * @return returns new view
     */
    @NonNull
    @Override
    public DjelatnikViewHoleder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_view_dodavanje_radnika, null);
        return new DjelatnikViewHoleder(view);
    }

    /**
     *
     * @param djelatnikViewHoleder
     * @param i
     */
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

    /**
     * The type Djelatnik view holeder.
     */
    public class DjelatnikViewHoleder extends RecyclerView.ViewHolder{
        /**
         * The View.
         */
        View view;
        /**
         * The Text view email. Text view for email input
         */
        public TextView textViewEmail;
        /**
         * The Text view ime. Text view for name input
         */
        public TextView textViewIme;
        /**
         * The Text view djelatnik id. Text view for user id input.
         */
        public TextView textViewDjelatnikId;

        /**
         * The Text view djelatnik ime. Text view inside view holder to print user name.
         */
        public TextView textViewDjelatnikIme;
        /**
         * The Text view djelatnik email. Text view inside view holder to print user email.
         */
        public TextView textViewDjelatnikEmail;
        /**
         * The Text view djelatnik tip. Text view inside view holder to print user type..
         */
        public TextView textViewDjelatnikTip;

        /**
         * Instantiates a new Djelatnik view holeder.
         *
         * @param itemView the item view
         */
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