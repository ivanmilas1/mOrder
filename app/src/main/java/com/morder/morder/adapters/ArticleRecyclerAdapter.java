package com.morder.morder.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.morder.morder.R;
import com.morder.morder.entities.Article;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ArticleRecyclerAdapter extends RecyclerView.Adapter<ArticleRecyclerAdapter.ViewHolder>{

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
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        final int itemPosition = position;
        final Article article = articleList.get(position);
        viewHolder.setName(article.getNaziv());
        viewHolder.setPrice(article.getJedinicna_cijena());
        viewHolder.setImage(ctx, article.getSlika());
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View mView;
        private Context ctx;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
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


    }
}


