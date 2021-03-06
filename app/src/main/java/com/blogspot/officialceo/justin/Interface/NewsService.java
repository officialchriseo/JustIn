package com.blogspot.officialceo.justin.Interface;

import com.blogspot.officialceo.justin.POJO.News;
import com.blogspot.officialceo.justin.POJO.Website;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface NewsService {
    @GET("v1/sources?language=en")
    Call<Website> getSources();

    @GET
    Call<News> getNewestArticles(@Url String url);

}
