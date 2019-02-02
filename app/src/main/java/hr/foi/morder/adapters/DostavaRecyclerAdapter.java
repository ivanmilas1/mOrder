package hr.foi.morder.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import hr.foi.morder.R;
import hr.foi.morder.model.Racun;
import hr.foi.morder.scannerlib.DostavaManager;
import hr.foi.morder.scannerlib.MetodaValidacijeDostave;
import hr.foi.morder.scannerlib.ValidiranjeActivity;
import hr.foi.morder.scannerlib.ValidiranjePrekoLozinkeActivity;
import hr.foi.morder.scannerlib.ValidiranjePutemQRKoda;

public class DostavaRecyclerAdapter extends RecyclerView.Adapter<DostavaRecyclerAdapter.DostavaViewHolder> {
    private Context context;
    private List<Racun> racunList;
    private String nacinRada = "";


    public DostavaRecyclerAdapter(Context context, List<Racun> racuna, String nacinRada) {
        this.context = context;
        this.racunList = racuna;
        this.nacinRada = nacinRada;
    }

    @NonNull
    @Override
    public DostavaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_view_pregled_dostave, null);
        return new DostavaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DostavaRecyclerAdapter.DostavaViewHolder dostavaViewHolder, int i) {
        final Racun racun = racunList.get(i);
        dostavaViewHolder.textViewId.setText(String.valueOf(racun.getId()));
        dostavaViewHolder.textViewQR.setText(String.valueOf(racun.getStatus()));
        dostavaViewHolder.textViewPin.setText(String.valueOf(racun.getKod()));
    }

    @Override
    public int getItemCount() {
        return racunList.size();
    }

    public class DostavaViewHolder extends RecyclerView.ViewHolder {
        View view;
        public TextView textViewId;
        public TextView textViewQR;
        public TextView textViewPin;

        public DostavaViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            textViewId = itemView.findViewById(R.id.textViewNarudzbaId);
            textViewQR = itemView.findViewById(R.id.textViewQR);
            textViewPin = itemView.findViewById(R.id.textViewPin);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MetodaValidacijeDostave fragZaValidaciju;

                    Intent intent = new Intent(context, ValidiranjeActivity.class);
                    intent.putExtra("Pin", textViewId.getText().toString());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    if (nacinRada.equals("validatePassword")){
                        fragZaValidaciju = new ValidiranjePrekoLozinkeActivity();
                        intent.putExtra("Pin", textViewId.getText().toString());
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    }
                    else {
                        fragZaValidaciju = new ValidiranjePutemQRKoda();
                        intent.putExtra("Pin", textViewId.getText().toString());
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    }
                    DostavaManager.getInstance().setMetodaValidacijeDostave(fragZaValidaciju);
                    context.startActivity(intent);
                }
            });
        }
    }
}