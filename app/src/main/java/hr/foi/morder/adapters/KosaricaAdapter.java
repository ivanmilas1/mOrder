package hr.foi.morder.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

import hr.foi.morder.R;
import hr.foi.morder.model.Artikl;
import hr.foi.morder.model.StavkaNarudzbe;

public class KosaricaAdapter extends RecyclerView.Adapter<KosaricaAdapter.KosaricaViewHolder> {
    private Context context;
    private List<StavkaNarudzbe> stavkaNarudzbeList;
    private FirebaseFirestore database;

    public KosaricaAdapter(Context context, List<StavkaNarudzbe> stavkaNarudzbe, FirebaseFirestore database) {
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
    public void onBindViewHolder(@NonNull final KosaricaViewHolder kosaricaViewHolder, int i) {

        final StavkaNarudzbe stavkaNarudzbe = stavkaNarudzbeList.get(i);
        database.collection("Artikl").whereEqualTo("id", stavkaNarudzbe.getArtikl_id()).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            String naziv = null;
                            for (DocumentSnapshot documentSnapshot : task.getResult()) {
                                Artikl artikl = documentSnapshot.toObject(Artikl.class);
                                naziv = artikl.getNaziv();
                            }
                            kosaricaViewHolder.textViewNazivProizvoda.setText(naziv);
                            kosaricaViewHolder.textViewKolicina.setText(String.valueOf(stavkaNarudzbe.getKolicina()));
                            kosaricaViewHolder.textViewCijena.setText(String.valueOf(stavkaNarudzbe.getCijena()));
                        } else {
                            Log.d("Error", "Error getting data");
                        }
                    }
                });
    }

    @Override
    public int getItemCount() {
        return stavkaNarudzbeList.size();
    }

    public class KosaricaViewHolder extends RecyclerView.ViewHolder {
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
