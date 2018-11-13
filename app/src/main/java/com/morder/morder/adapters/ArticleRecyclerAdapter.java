package com.morder.morder.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.morder.morder.ArticleActivity;
import com.morder.morder.R;
import com.morder.morder.entities.Article;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class ArticleRecyclerAdapter extends RecyclerView.Adapter<ArticleRecyclerAdapter.ViewHolder>  {

    private List<Article> articleList;
    private Context ctx;
    private FirebaseFirestore database;


    public ArticleRecyclerAdapter(List<Article> articleList, Context ctx, FirebaseFirestore database) {
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
        final Article article = articleList.get(position);
        viewHolder.setName(article.getNaziv());
        viewHolder.setPrice(article.getJedinicna_cijena());
        viewHolder.setImage(ctx, article.getSlika());
        viewHolder.setQuantity(1);
        viewHolder.setPriceCurrency("kn");
        viewHolder.quantityAdd.setOnClickListener(new View.OnClickListener()
        {
            int priceArticle1 = Integer.parseInt(String.valueOf(viewHolder.price.getText()));

            @Override
            public void onClick(View v) {
                int countQuantity = Integer.parseInt(String.valueOf(viewHolder.quantity.getText()));
                countQuantity++;
                viewHolder.quantity.setText(String.valueOf(countQuantity));

                int priceArticle = Integer.parseInt(String.valueOf(viewHolder.price.getText()));
                priceArticle =  priceArticle1 + priceArticle;
                viewHolder.price.setText(String.valueOf(priceArticle));
            }
        });
        viewHolder.quantityRemove.setOnClickListener(new View.OnClickListener() {

            int priceArticle1 = Integer.parseInt(String.valueOf(viewHolder.price.getText()));

            @Override
            public void onClick(View v) {
                int countQuantity = Integer.parseInt(String.valueOf(viewHolder.quantity.getText()));
                if(countQuantity>=2 && countQuantity <=999){
                    countQuantity--;
                    viewHolder.quantity.setText(String.valueOf(countQuantity));

                    int priceArticle = Integer.parseInt(String.valueOf(viewHolder.price.getText()));
                    priceArticle =  priceArticle - priceArticle1;
                    viewHolder.price.setText(String.valueOf(priceArticle));

                }

            }
        });
    }


    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View mView;
        private Context ctx;
        public Button quantityAdd;
        public Button quantityRemove;
        public Button order;
        public TextView price;
        public TextView quantity;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            quantityAdd = mView.findViewById(R.id.button_add);
            quantityRemove = mView.findViewById(R.id.button_remove);
            order = mView.findViewById(R.id.button_order);
            price = mView.findViewById(R.id.article_price);
            quantity = mView.findViewById(R.id.article_quantity);
        }
        public void setName(String name){
            TextView article_name = mView.findViewById(R.id.article_name);
            article_name.setText(name);
        }
        public void setPrice(Number price){
            TextView article_price = mView.findViewById(R.id.article_price);
            article_price.setText(price.toString());
        }

        public void setImage(Context ctx, String image){
            ImageView article_image = (ImageView)mView.findViewById(R.id.article_image);
            Picasso.with(ctx).load(image).into(article_image);
        }

        public void setQuantity(Number quantity) {
            TextView articleQuantity = itemView.findViewById(R.id.article_quantity);
            articleQuantity.setText(quantity.toString());
        }
        public void setPriceCurrency(String quantity) {
            TextView articlePriceCurrency = itemView.findViewById(R.id.article_price_currency);
            articlePriceCurrency.setText(quantity.toString());
        }


    }
}


