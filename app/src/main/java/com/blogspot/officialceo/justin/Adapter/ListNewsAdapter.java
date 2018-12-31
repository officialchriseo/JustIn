package com.blogspot.officialceo.justin.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blogspot.officialceo.justin.Interface.ItemClickListener;
import com.blogspot.officialceo.justin.POJO.Articles;
import com.blogspot.officialceo.justin.R;
import com.blogspot.officialceo.justin.common.ISO8601Parse;
import com.bumptech.glide.Glide;
import com.github.curioustechizen.ago.RelativeTimeTextView;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ListNewsAdapter extends RecyclerView.Adapter<ListNewsAdapter.ListNewsViewHolder>{

    private List<Articles> articlesList;
    private Context context;


    public ListNewsAdapter(List<Articles> articlesList, Context context) {
        this.articlesList = articlesList;
        this.context = context;
    }


    @NonNull
    @Override
    public ListNewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.news_layout, viewGroup, false);

        return new ListNewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListNewsViewHolder listNewsViewHolder, int i) {

        Glide.with(context).load(articlesList.get(i).getUrlToImage()).into(listNewsViewHolder.article_image);

        if (articlesList.get(i).getTitle().length() > 65){

            listNewsViewHolder.article_title.setText(articlesList.get(i).getTitle().substring(0,65) + "...");

        }else{

            listNewsViewHolder.article_title.setText(articlesList.get(i).getTitle());

        }

        Date date = null;

        try {

            date = ISO8601Parse.parse(articlesList.get(i).getPublishedAt());

        } catch (ParseException e) {
            e.printStackTrace();
        }

        listNewsViewHolder.article_time.setReferenceTime(date.getTime());

    }


    @Override
    public int getItemCount() {
        return articlesList.size();
    }

    class ListNewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        ItemClickListener itemClickListener;
        TextView article_title;
        RelativeTimeTextView article_time;
        CircleImageView article_image;

        public ListNewsViewHolder(@NonNull View itemView) {
            super(itemView);

            article_image = itemView.findViewById(R.id.article_image);
            article_time = itemView.findViewById(R.id.article_time);
            article_title = itemView.findViewById(R.id.article_title);

            itemView.setOnClickListener(this);

        }


        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {

            itemClickListener.onClick(v, getAdapterPosition(), false);

        }
    }

}
