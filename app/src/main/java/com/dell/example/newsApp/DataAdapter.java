package com.dell.example.newsApp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dell.example.newsApp.model.ArticleStructure;
import com.dell.example.newsApp.model.Constants;
import com.dell.example.newsApp.view.ArticleActivity;

import java.util.ArrayList;


/*
** Adapter for adding data to Recycle View. Also used Glide to load the Image from url.
**/
public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private ArrayList<ArticleStructure> articles;
    private Context mContext;
    private int lastPosition = -1;

    public DataAdapter(Context mContext, ArrayList<ArticleStructure> articles) {
        this.mContext = mContext;
        this.articles = articles;
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder holder, int position) {

        String title = articles.get(position).getTitle();
        if (title.endsWith("- Times of India")) {
            title = title.replace("- Times of India", "");
        } else if(title.endsWith(" - Firstpost")) {
            title = title.replace(" - Firstpost", "");
        }
        holder.tv_card_main_title.setText(title);
        Glide.with(mContext)
                .load(articles.get(position).getUrlToImage())
                .thumbnail(0.1f)
                .centerCrop()
                .error(R.drawable.ic_placeholder)
                .into(holder.img_card_main);

        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.item_animation_fall_down);
            holder.cardView.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tv_card_main_title;
        private ImageView img_card_main;
        private CardView cardView;

        public ViewHolder(View view) {
            super(view);
            tv_card_main_title = view.findViewById(R.id.tv_card_main_title);
            img_card_main = view.findViewById(R.id.img_card_main);
            cardView = view.findViewById(R.id.card_row);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            String headLine = articles.get(getAdapterPosition()).getTitle();
            if (headLine.endsWith(" - Times of India")) {
                headLine = headLine.replace(" - Times of India", "");
            } else if(headLine.endsWith(" - Firstpost")) {
                headLine = headLine.replace(" - Firstpost", "");
            }
            String description = articles.get(getAdapterPosition()).getDescription();
            String date = articles.get(getAdapterPosition()).getPublishedAt();
            String imgURL = articles.get(getAdapterPosition()).getUrlToImage();
            String URL = articles.get(getAdapterPosition()).getUrl();
            Intent intent = new Intent(mContext, ArticleActivity.class);
            intent.putExtra(Constants.INTENT_HEADLINE, headLine);
            intent.putExtra(Constants.INTENT_DESCRIPTION, description);
            intent.putExtra(Constants.INTENT_DATE, date);
            intent.putExtra(Constants.INTENT_IMG_URL, imgURL);
            intent.putExtra(Constants.INTENT_ARTICLE_URL, URL);
            mContext.startActivity(intent);
            ((Activity) mContext).overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
        }
    }
}
