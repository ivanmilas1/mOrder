package hr.foi.morder.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import hr.foi.morder.R;
import hr.foi.morder.model.Racun;
import hr.foi.morder.scannerlib.ValidacijaDostave;
import hr.foi.morder.scannerlib.ValidacijaDostaveManager;

public class DostavaRecyclerAdapter extends RecyclerView.Adapter<DostavaRecyclerAdapter.DostavaViewHolder> {
    private Context context;
    private List<Racun> racunList;
    View view;

    public DostavaRecyclerAdapter(Context context, List<Racun> racuna) {
        this.context = context;
        this.racunList = racuna;
    }

    @NonNull
    @Override
    public DostavaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.card_view_pregled_dostave, null);



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

    public class DostavaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        View view;
        TextView textViewId, textViewQR, textViewPin;

        private DostavaViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            textViewId = itemView.findViewById(R.id.textViewNarudzbaId);
            textViewQR = itemView.findViewById(R.id.textViewQR);
            textViewPin = itemView.findViewById(R.id.textViewPin);

            // programmatically adding buttons to a layout
            LinearLayout layout = itemView.findViewById(R.id.cardViewLinearLayout);

//            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
//            String imgSett = prefs.getString(keyChannel, "");

            HashMap<String, ValidacijaDostave> hashMapMetodeValidacije = ValidacijaDostaveManager.getInstance().getMetodeValidacijeDostave();
            for (String key : hashMapMetodeValidacije.keySet()) {
                Button buttonToAdd = new Button(context);
                buttonToAdd.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                buttonToAdd.setText(key);
                buttonToAdd.setId(View.generateViewId());
                buttonToAdd.setOnClickListener(this);
                layout.addView(buttonToAdd);
            }
        }

        @Override
        public void onClick(View view) {
            String racunKod = textViewPin.getText().toString();
            ValidacijaDostaveManager.getInstance().odabirMetodeValidacijeDostave(((Button) view).getText().toString(), racunKod, context);
        }
    }
}