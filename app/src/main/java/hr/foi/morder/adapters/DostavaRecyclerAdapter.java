package hr.foi.morder.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

    public DostavaRecyclerAdapter(Context context, List<Racun> racuna) {
        this.context = context;
        this.racunList = racuna;
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
        TextView textViewId, textViewQR, textViewPin;
        Button buttonValidateViaQR, buttonValidateViaGeneratedPassword;

        public DostavaViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            textViewId = itemView.findViewById(R.id.textViewNarudzbaId);
            textViewQR = itemView.findViewById(R.id.textViewQR);
            textViewPin = itemView.findViewById(R.id.textViewPin);
            buttonValidateViaQR = itemView.findViewById(R.id.buttonValidacijaPrekoQRKoda);
            buttonValidateViaGeneratedPassword = itemView.findViewById(R.id.buttonValidacijaPrekoLozinke);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    MetodaValidacijeDostave fragZaValidaciju;
//
//                    Intent intent = new Intent(context, ValidiranjeActivity.class);
//                    intent.putExtra("Pin", textViewId.getText().toString());
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    if (nacinRada.equals("validatePassword")) {
//                        fragZaValidaciju = new ValidiranjePrekoLozinkeActivity();
//                        intent.putExtra("Pin", textViewId.getText().toString());
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    } else {
//                        fragZaValidaciju = new ValidiranjePutemQRKoda();
//                        intent.putExtra("Pin", textViewId.getText().toString());
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    }
//                    DostavaManager.getInstance().setMetodaValidacijeDostave(fragZaValidaciju);
//                    context.startActivity(intent);
                }
            });

            final MetodaValidacijeDostave[] fragZaValidaciju = new MetodaValidacijeDostave[1];
            final Intent intent = new Intent(context, ValidiranjeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            buttonValidateViaQR.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    fragZaValidaciju[0] = new ValidiranjePutemQRKoda();
                    intent.putExtra("Ocekivana vrijednost", textViewId.getText().toString());
                    DostavaManager.getInstance().setMetodaValidacijeDostave(fragZaValidaciju[0]);
                    context.startActivity(intent);
                }
            });

            buttonValidateViaGeneratedPassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    fragZaValidaciju[0] = new ValidiranjePrekoLozinkeActivity();
                    intent.putExtra("Ocekivana vrijednost", textViewPin.getText().toString());
                    DostavaManager.getInstance().setMetodaValidacijeDostave(fragZaValidaciju[0]);
                    context.startActivity(intent);
                }
            });
        }
    }
}