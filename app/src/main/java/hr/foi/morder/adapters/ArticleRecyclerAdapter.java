package hr.foi.morder.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import hr.foi.morder.R;
import hr.foi.morder.model.Artikl;
import hr.foi.morder.model.Narudzba;
import hr.foi.morder.model.StavkaNarudzbe;

public class ArticleRecyclerAdapter extends RecyclerView.Adapter<ArticleRecyclerAdapter.ViewHolder> {

    private List<Artikl> articleList;
    private Context ctx;
    private FirebaseFirestore database;
    private Integer idNarudzba, id;
    private Integer quantity, brojNarudzbe;
    private Double price, iznosNarudzbe;
    private String narudzbaDokument;

    public ArticleRecyclerAdapter(List<Artikl> articleList, Context ctx, FirebaseFirestore database) {
        this.articleList = articleList;
        this.ctx = ctx;
        this.database = database;
    }

    @NonNull
    @Override
    public ArticleRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.article_list_item, viewGroup, false);
        return new ArticleRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int position) {

        final int itemPosition = position;
        final Artikl Artikl = articleList.get(itemPosition);
        viewHolder.setId(Artikl.getId());
        viewHolder.setName(Artikl.getNaziv());
        viewHolder.setPrice(Artikl.getJedinicna_cijena());
        viewHolder.setImage(ctx, Artikl.getSlika());
        viewHolder.setPrice(Artikl.getJedinicna_cijena());
        viewHolder.setQuantity(1);
        viewHolder.setPriceCurrency("kn");
        viewHolder.quantityAdd.setOnClickListener(new View.OnClickListener() {
            Double priceArticle1 = Double.parseDouble(String.valueOf(viewHolder.price.getText()));

            @Override
            public void onClick(View v) {
                int countQuantity = Integer.parseInt(String.valueOf(viewHolder.quantity.getText()));
                countQuantity++;
                viewHolder.quantity.setText(String.valueOf(countQuantity));

                Double priceArticle = Double.parseDouble(String.valueOf(viewHolder.price.getText()));
                priceArticle = priceArticle1 + priceArticle;
                viewHolder.price.setText(String.valueOf(priceArticle));
            }
        });

        viewHolder.quantityRemove.setOnClickListener(new View.OnClickListener() {

            Double priceArticle1 = Double.parseDouble(String.valueOf(viewHolder.price.getText()));

            @Override
            public void onClick(View v) {
                int countQuantity = Integer.parseInt(String.valueOf(viewHolder.quantity.getText()));
                if (countQuantity >= 2 && countQuantity <= 999) {
                    countQuantity--;
                    viewHolder.quantity.setText(String.valueOf(countQuantity));

                    Double priceArticle = Double.parseDouble(String.valueOf(viewHolder.price.getText()));
                    priceArticle = priceArticle - priceArticle1;
                    viewHolder.price.setText(String.valueOf(priceArticle));
                }
            }
        });

        viewHolder.order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity = Integer.parseInt(String.valueOf(viewHolder.quantity.getText()));
                price = Double.parseDouble(String.valueOf(viewHolder.price.getText()));
                id = Integer.parseInt(String.valueOf(viewHolder.id.getText()));

                viewHolder.setPrice(Artikl.getJedinicna_cijena());
                try {
                    zadnjiElement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                final Integer quantity = Integer.parseInt(String.valueOf(viewHolder.quantity.getText()));
                final Double price = Double.parseDouble(String.valueOf(viewHolder.price.getText()));
                addOrder(Artikl.getId(), brojNarudzbe, price, quantity);
                viewHolder.setPrice(Artikl.getJedinicna_cijena());
                viewHolder.setQuantity(1);
                Toast.makeText(ctx, "Narudžba je stavljena u košaricu.", Toast.LENGTH_SHORT).show();

                database.collection("Narudzba").orderBy("id", Query.Direction.DESCENDING).limit(1)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    List<Narudzba> narudzbaList = new ArrayList<>();

                                    for (DocumentSnapshot documentSnapshot : task.getResult()) {
                                        Narudzba narudzba = documentSnapshot.toObject(Narudzba.class);
                                        narudzbaDokument = documentSnapshot.getId();
                                        iznosNarudzbe = narudzba.getIznos_narudzbe();
                                        narudzba.getId();
                                        narudzbaList.add(narudzba);
                                    }

                                    for (Narudzba n : narudzbaList) {
                                        idNarudzba = n.getId();
                                    }
                                    addOrder(id, idNarudzba, price, quantity);
                                    //database.collection("Narudzba").document(narudzbaDokument).update("iznos_narudzbe", iznosNarudzbe + price);


                                } else {
                                    Log.d("Error", "Error getting data");
                                }
                            }
                        });
            }
        });
    }

    public void addOrder(Integer artikl, Integer narudzba, Double cijena, Integer kolicina) {
        Map<String, Object> stavkaNarudzbe = new StavkaNarudzbe(artikl, narudzba, cijena, kolicina).toMap();
        database.collection("Stavka narudzbe")
                .add(stavkaNarudzbe)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {

                    }
                });
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View mView;
        public Button quantityAdd;
        public Button quantityRemove;
        public Button order;
        public TextView price;
        public TextView quantity;
        public TextView id;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            quantityAdd = mView.findViewById(R.id.button_add);
            quantityRemove = mView.findViewById(R.id.button_remove);
            order = mView.findViewById(R.id.button_order);
            price = mView.findViewById(R.id.article_price);
            quantity = mView.findViewById(R.id.article_quantity);
            id = mView.findViewById(R.id.article_id);
        }

        public void setId(Integer id) {
            TextView articleId = itemView.findViewById(R.id.article_id);
            articleId.setText(id.toString());
        }

        public void setName(String name) {
            TextView article_name = mView.findViewById(R.id.article_name);
            article_name.setText(name);
        }

        public void setPrice(Double price) {
            TextView article_price = mView.findViewById(R.id.article_price);
            article_price.setText(price.toString());
        }

        public void setImage(Context ctx, String image) {
            ImageView article_image = mView.findViewById(R.id.article_image);
            Picasso.with(ctx).load(image).into(article_image);
        }

        public void setQuantity(Integer quantity) {
            TextView articleQuantity = itemView.findViewById(R.id.article_quantity);
            articleQuantity.setText(quantity.toString());
        }

        public void setPriceCurrency(String quantity) {
            TextView articlePriceCurrency = itemView.findViewById(R.id.article_price_currency);
            articlePriceCurrency.setText(quantity);
        }
    }

    private void zadnjiElement() {
        database.collection("Narudzba").limit(1).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            for (DocumentSnapshot documentSnapshot : task.getResult()) {
                                Narudzba narudzba = documentSnapshot.toObject(Narudzba.class);
                                brojNarudzbe = narudzba.getId();
                                brojNarudzbe = brojNarudzbe + 1;


                            }
                        } else {
                            Log.d("Error", "Error getting data");
                        }
                    }
                });
    }
}