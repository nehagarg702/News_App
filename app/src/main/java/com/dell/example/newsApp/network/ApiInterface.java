package com.dell.example.newsApp.network;


import com.dell.example.newsApp.model.ArticleResponse;
import com.dell.example.newsApp.model.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("top-headlines")
    Call<NewsResponse> getHeadlines(@Query("sources") String sources,
                                    @Query("apiKey") String apiKey);


    @GET("everything")
    Call<NewsResponse> getSearchResults(@Query("q") String query,
                                           @Query("sortBy") String sortBy,
                                           @Query("language") String language,
                                           @Query("apiKey") String apiKey);

}
