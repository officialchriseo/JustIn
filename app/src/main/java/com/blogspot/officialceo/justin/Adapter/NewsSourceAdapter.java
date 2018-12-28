package com.blogspot.officialceo.justin.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blogspot.officialceo.justin.Interface.IconBetterIdeaService;
import com.blogspot.officialceo.justin.Interface.ItemClickListener;
import com.blogspot.officialceo.justin.POJO.IconBetterIdea;
import com.blogspot.officialceo.justin.POJO.Website;
import com.blogspot.officialceo.justin.R;
import com.blogspot.officialceo.justin.common.Common;
import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsSourceAdapter extends RecyclerView.Adapter<NewsSourceAdapter.NewsViewHolder>{

    private Context context;
    private Website website;

    IconBetterIdeaService iconBetterIdeaService;

    public NewsSourceAdapter(Context context, Website website) {
        this.context = context;
        this.website = website;

        iconBetterIdeaService = Common.getIconService();
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.source_layout, viewGroup, false);

        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final NewsViewHolder newsViewHolder, int i) {

//        Sources website1 = website.getSources().get(i);
//
//        String sourceName = website1.getName();

        StringBuilder iconBetterApi = new StringBuilder("https://i.olsh.me/allicons.json?url=");
        iconBetterApi.append(website.getSources().get(i).getUrl());

        iconBetterIdeaService.getIconUrl(iconBetterApi.toString()).enqueue(new Callback<IconBetterIdea>() {
            @Override
            public void onResponse(Call<IconBetterIdea> call, Response<IconBetterIdea> response) {

                if (response.body().getIcons().size() > 0){

                    Glide.with(context).load(response.body().getIcons().get(0).getUrl()).into(newsViewHolder.source_image);


                }


            }

            @Override
            public void onFailure(Call<IconBetterIdea> call, Throwable t) {

            }
        });


        newsViewHolder.source_title.setText(website.getSources().get(i).getName());


    }

    @Override
    public int getItemCount() {


        return website.getSources().size();


    }

    class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ItemClickListener itemClickListener;

        TextView source_title;
        CircleImageView source_image;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);

            source_title = itemView.findViewById(R.id.source_name);
            source_image = itemView.findViewById(R.id.source_image);

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
