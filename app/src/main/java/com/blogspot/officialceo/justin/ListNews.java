package com.blogspot.officialceo.justin;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.blogspot.officialceo.justin.Adapter.ListNewsAdapter;
import com.blogspot.officialceo.justin.Interface.NewsService;
import com.blogspot.officialceo.justin.POJO.Articles;
import com.blogspot.officialceo.justin.POJO.News;
import com.blogspot.officialceo.justin.common.Common;
import com.bumptech.glide.Glide;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.github.florent37.diagonallayout.DiagonalLayout;

import java.util.List;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListNews extends AppCompatActivity {

    KenBurnsView kbv;
    SpotsDialog spotsDialog;
    NewsService mService;
    TextView top_title, top_author;
    String source = "", webHostUrl = "";
    DiagonalLayout diagonalLayout;
    RecyclerView lstNews;
    SwipeRefreshLayout swipeRefreshLayout;
    ListNewsAdapter listNewsAdapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_news);

        //Service

        mService = Common.getNewsService();

        spotsDialog = new SpotsDialog(this);




        //views

        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadNews(source, true);
            }
        });

        diagonalLayout = findViewById(R.id.diagonalLayout);
        diagonalLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detail = new Intent(getBaseContext(), DetailArticle.class);
                detail.putExtra("webURL", webHostUrl);
                startActivity(detail);
            }
        });

        kbv = findViewById(R.id.top_image);
        top_author = findViewById(R.id.top_author);
        top_title = findViewById(R.id.top_title);

        lstNews = findViewById(R.id.lstNews);
        lstNews.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        lstNews.setLayoutManager(layoutManager);

        //intent

        if (getIntent() != null){

            source = getIntent().getStringExtra("source");

            if (!source.isEmpty()){

                loadNews(source, false);

            }

        }

    }

    private void loadNews(String source, boolean isRefreshed) {

        if (!isRefreshed){

            spotsDialog.show();
            mService.getNewestArticles(Common.getAPIUrl(source, Common.API_KEY)).enqueue(new Callback<News>() {
                @Override
                public void onResponse(Call<News> call, Response<News> response) {
                    spotsDialog.dismiss();



//                        Log.d("TEST...", "ON RESPONSE " + response.raw());

                        Log.d("TEST...", "RESPONSE..." + response.body().getArticles());


                    Glide.with(getBaseContext()).load(response.body().getArticles().get(0).getUrlToImage()).into(kbv);

                    top_title.setText(response.body().getArticles().get(0).getTitle());
                    top_author.setText(response.body().getArticles().get(0).getAuthor());

                    webHostUrl = response.body().getArticles().get(0).getUrl();

                    //Load Remaining articles
                    List<Articles> removeFirsItem = response.body().getArticles();
                    removeFirsItem.remove(0);
                    listNewsAdapter = new ListNewsAdapter(removeFirsItem, getBaseContext());
                    listNewsAdapter.notifyDataSetChanged();
                    lstNews.setAdapter(listNewsAdapter);


                }

                @Override
                public void onFailure(Call<News> call, Throwable t) {

                }
            });

        }else{

            spotsDialog.show();
            mService.getNewestArticles(Common.getAPIUrl(source, Common.API_KEY)).enqueue(new Callback<News>() {
                @Override
                public void onResponse(Call<News> call, Response<News> response) {
                    spotsDialog.dismiss();



//                        Log.d("TEST...", "ON RESPONSE " + response.raw());

                    Log.d("TEST...", "RESPONSE..." + response.body().getArticles());


                    Glide.with(getBaseContext()).load(response.body().getArticles().get(0).getUrlToImage()).into(kbv);

                    top_title.setText(response.body().getArticles().get(0).getTitle());
                    top_author.setText(response.body().getArticles().get(0).getAuthor());

                    webHostUrl = response.body().getArticles().get(0).getUrl();

                    //Load Remaining articles
                    List<Articles> removeFirsItem = response.body().getArticles();
                    removeFirsItem.remove(0);
                    listNewsAdapter = new ListNewsAdapter(removeFirsItem, getBaseContext());
                    listNewsAdapter.notifyDataSetChanged();
                    lstNews.setAdapter(listNewsAdapter);


                }

                @Override
                public void onFailure(Call<News> call, Throwable t) {

                }
            });

            swipeRefreshLayout.setRefreshing(false);

        }

    }
}
