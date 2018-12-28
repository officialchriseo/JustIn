package com.blogspot.officialceo.justin;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.blogspot.officialceo.justin.Adapter.NewsSourceAdapter;
import com.blogspot.officialceo.justin.Interface.NewsService;
import com.blogspot.officialceo.justin.Listener.NewsListener;
import com.blogspot.officialceo.justin.POJO.Website;
import com.blogspot.officialceo.justin.common.Common;
//import com.blogspot.officialceo.justin.databinding.ActivityMainBinding;
import com.blogspot.officialceo.justin.viewModel.NewsSourcesViewModel;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;
import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity{

    //ActivityMainBinding binding;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.swipe_RefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    NewsService newsService;
    NewsSourceAdapter newsSourceAdapter;
    SpotsDialog alertDialog;
    NewsSourcesViewModel newsSourcesViewModel;
    NewsListener newsListener;
    RecyclerView.LayoutManager layoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        ButterKnife.bind(this);

        alertDialog = new SpotsDialog(this);

        //init cache
        Paper.init(this);

        //init Service
        newsService = Common.getNewsService();

        //views
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        alertDialog = new SpotsDialog(this);

        loadWebsiteSource(false);


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                loadWebsiteSource(true);

            }
        });



    }

    private void loadWebsiteSource(boolean isRefreshed) {

        if (!isRefreshed){

            String cache = Paper.book().read("cache");

            if (cache != null && cache.isEmpty()) //if have cache
            {

                Website website = new Gson().fromJson(cache, Website.class); //converts cache from json to object
                newsSourceAdapter = new NewsSourceAdapter(getBaseContext(), website);
                newsSourceAdapter.notifyDataSetChanged();
                recyclerView.setAdapter(newsSourceAdapter);

            }else{

                //if have no cache

                alertDialog.show();


                //fetching new Data

                newsService.getSources().enqueue(new Callback<Website>() {
                    @Override
                    public void onResponse(Call<Website> call, Response<Website> response) {

                        newsSourceAdapter = new NewsSourceAdapter(getBaseContext(), response.body());
                        newsSourceAdapter.notifyDataSetChanged();
                        Log.d("Response...", response.body().getSources().toString());
                        recyclerView.setAdapter(newsSourceAdapter);

                        alertDialog.dismiss();

                        //save to cache

                        Paper.book().write("cache", new Gson().toJson(response.body()));

                    }

                    @Override
                    public void onFailure(Call<Website> call, Throwable t) {

                        alertDialog.dismiss();

                    }
                });


            }

        }else //from isRefreshed

        {


            alertDialog.show();

            //fetching new Data

            newsService.getSources().enqueue(new Callback<Website>() {
                @Override
                public void onResponse(Call<Website> call, Response<Website> response) {

                    newsSourceAdapter = new NewsSourceAdapter(getBaseContext(), response.body());
                    newsSourceAdapter.notifyDataSetChanged();
                    recyclerView.setAdapter(newsSourceAdapter);

                    //save to cache

                    Paper.book().write("cache", new Gson().toJson(response.body()));

                    //dismiss refresh progress
                    swipeRefreshLayout.setRefreshing(false);


                }

                @Override
                public void onFailure(Call<Website> call, Throwable t) {

                }
            });


        }

    }


}

