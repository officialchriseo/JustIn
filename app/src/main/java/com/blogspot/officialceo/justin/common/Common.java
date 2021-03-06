package com.blogspot.officialceo.justin.common;

import com.blogspot.officialceo.justin.Interface.IconBetterIdeaService;
import com.blogspot.officialceo.justin.Interface.NewsService;
import com.blogspot.officialceo.justin.remote.IconBetterIdeaClient;
import com.blogspot.officialceo.justin.remote.RetrofitClient;

public class Common {

    private static  final String BASE_URL = "https://newsapi.org/";
    public static final String API_KEY = "578e28d5ed924e489519de26badca3c3";


    public static NewsService getNewsService(){

        return RetrofitClient.getClient(BASE_URL).create(NewsService.class);

    }


    public static IconBetterIdeaService getIconService(){

        return IconBetterIdeaClient.getClient().create(IconBetterIdeaService.class);

    }


    //https://newsapi.org/v2/everything?domains=wsj.com&apiKey=578e28d5ed924e489519de26badca3c3
    //https://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=578e28d5ed924e489519de26badca3c3

    public static String getAPIUrl(String source, String apiKey){

        StringBuilder apiUrl = new StringBuilder("https://newsapi.org/v2/top-headlines?sources=");
        return apiUrl.append(source).append("&apiKey=").append(apiKey).toString();

    }

}
