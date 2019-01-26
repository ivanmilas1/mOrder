package hr.foi.morder.adapters;

import android.support.annotation.NonNull;
import hr.foi.morder.R;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import hr.foi.morder.model.StavkaNarudzbe;

public class KosaricaAdapter extends RecyclerView.Adapter<KosaricaAdapter.KosaricaViewHolder> {
    private Context context;
    private List<StavkaNarudzbe> stavkaNarudzbeList;
    private FirebaseFirestore database;

    public KosaricaAdapter(Context context, List<StavkaNarudzbe>stavkaNarudzbe, FirebaseFirestore database){
        this.context = context;
        this.stavkaNarudzbeList = stavkaNarudzbe;
        this.database = database;
    }


    @NonNull
    @Override
    public KosaricaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_view_kosarica, null);
        return new KosaricaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KosaricaViewHolder kosaricaViewHolder, int i) {
        final StavkaNarudzbe stavkaNarudzbe = stavkaNarudzbeList.get(i);
       // kosaricaViewHolder.textViewNazivProizvoda.setText(stavkaNarudzbe.getArtikl_id());

    }

    @Override
    public int getItemCount() {
        return stavkaNarudzbeList.size();
    }


    public class KosaricaViewHolder extends RecyclerView.ViewHolder{

        View view;
        public TextView textViewNazivProizvoda;
        public TextView textViewJedinicnaCijena;
        public TextView textViewKolicina;
        public TextView textViewCijena;


         public KosaricaViewHolder(@NonNull View itemView) {

            super(itemView);
            view = itemView;
            textViewNazivProizvoda = itemView.findViewById(R.id.textNazivProizvoda);
            textViewCijena = itemView.findViewById(R.id.textCijena);
            textViewKolicina = itemView.findViewById(R.id.textKolicina);
            textViewJedinicnaCijena = itemView.findViewById(R.id.textJedinicnaCijena);
        }
    }
}
