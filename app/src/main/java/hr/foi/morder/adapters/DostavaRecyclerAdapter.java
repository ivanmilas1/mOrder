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

import hr.foi.morder.R;
import hr.foi.morder.model.Racun;
import hr.foi.morder.scannerlib.ScannerStart;

public class DostavaRecyclerAdapter extends RecyclerView.Adapter<DostavaRecyclerAdapter.DostavaViewHolder> {
    private Context context;
    private List<Racun> racunList;
    private FirebaseFirestore database;

    public  DostavaRecyclerAdapter(Context context, List<Racun> racuns, FirebaseFirestore database){

        this.context = context;
        this.racunList = racuns;
        this.database = database;
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
        dostavaViewHolder.textViewPin.setText(String.valueOf(racun.getSifra()));

    }
    @Override
    public int getItemCount(){return racunList.size();}

    public class DostavaViewHolder extends RecyclerView.ViewHolder{

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
                @Override public void onClick(View view) {
                Intent intent = new Intent(context, ScannerStart.class);
                intent.putExtra("Pin",textViewPin.getText().toString());
                context.startActivity(intent);
                }
            });
        }
    }

}
