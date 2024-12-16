package com.example.newsapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsService {
    @GET("top-headlines")
    Call<NewsModel> getTopHeadlines(@Query("country") String country, @Query("apiKey") String apiKey);
    @GET("top-headlines")
    Call<NewsModel> getTopHeadlinesWithCategory(@Query("country") String country, @Query("category") String category, @Query("apiKey") String apiKey);

}
