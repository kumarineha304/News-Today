package com.example.nehakumari.newstoday;

import com.example.nehakumari.newstoday.Model.News;
import com.example.nehakumari.newstoday.Model.News_Data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by NEHA KUMARI on 03-03-2018.
 */

public interface NewsApi_Interface {

    String BASE_URL = "https://newsapi.org/";

    @GET("v2/everything?sources=the-times-of-india&apiKey=b05e734cb63a4ff0934999281cbcdaf0")
    Call<News> getNews_Data();
}
