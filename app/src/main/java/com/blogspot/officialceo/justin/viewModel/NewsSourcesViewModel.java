package com.blogspot.officialceo.justin.viewModel;

import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.util.Log;

import com.blogspot.officialceo.justin.Interface.IconBetterIdeaService;
import com.blogspot.officialceo.justin.Interface.NewsService;
import com.blogspot.officialceo.justin.Listener.NewsListener;
import com.blogspot.officialceo.justin.POJO.Website;
import com.blogspot.officialceo.justin.common.Common;
import com.google.gson.Gson;

import io.paperdb.Paper;
import okhttp3.Cache;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsSourcesViewModel extends ViewModel {

    private static final String TAG = "NewsSourcesViewModel";

    static NewsListener newsListener;
    private NewsService newsService;
    private IconBetterIdeaService iconBetterIdeaService;

    public void setListener(Context context, NewsListener newsListener){

        NewsSourcesViewModel.newsListener = newsListener;
        newsService = Common.getNewsService();
        iconBetterIdeaService = Common.getIconService();

        Paper.init(context);

        }


        public void loadWebsiteSource(boolean isRefreshed){

            if (!isRefreshed){

                String cache = Paper.book().read("cache");

                if (cache != null && cache.isEmpty()){ //if have cache

                    Website website = new Gson().fromJson(cache, Website.class); //converts cache from json to object
                    newsListener.showSuccess(website);

                }else {

//                    newsListener.showProgress();
                    //fetch new data
                    newsService.getSources().enqueue(new Callback<Website>() {
                        @Override
                        public void onResponse(Call<Website> call, Response<Website> response) {
//                            newsListener.hidProgress();

                            if (response.isSuccessful()){

                                newsListener.showSuccess(response.body());
                                //save the cache
                                Paper.book().write("cache", new Gson().toJson(response.body()));

                            }
                        }

                        @Override
                        public void onFailure(Call<Website> call, Throwable t) {

                            Log.d("TAG", "Failed... " + t.getMessage());

                        }
                    });

                }

            }else{

                newsListener.showProgress();

                //fetch new data
                newsService.getSources().enqueue(new Callback<Website>() {
                    @Override
                    public void onResponse(Call<Website> call, Response<Website> response) {

                        Log.d("TAG", "onResponse ... " + response.raw());
                        newsListener.hidProgress();

                        if (response.isSuccessful()){

                            newsListener.showSuccess(response.body());

                            //save the cache
                            Paper.book().write("cache", new Gson().toJson(response.body()));

                            //dismiss the refreshing layout
                            newsListener.setRefreshing(false);

                        }

                    }

                    @Override
                    public void onFailure(Call<Website> call, Throwable t) {

                        Log.d("TAG", "onFailure..." + t.getMessage());
                        newsListener.setRefreshing(false);
                        newsListener.hidProgress();

                    }
                });

            }

        }


}
