package com.example.nehakumari.newstoday.Model;

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by NEHA KUMARI on 03-03-2018.
 */

public class News {

    private String status;
    private int totalResults;
    @SerializedName("articles")
    @Expose
    private List<News_Data> news_data;

    public News() {
    }

    public News(String status, int totalResults, List<News_Data> news_data) {
        this.status = status;
        this.totalResults = totalResults;
        this.news_data = news_data;
    }

    public String getStatus() {
        return status;
    }

    public int gettotalResults() {
        return totalResults;
    }

    public List<News_Data> getNews_data() {
        Log.d("NewsData",news_data+"");
        return news_data;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void settotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public void setNews_data(List<News_Data> news_data) {
        this.news_data = news_data;
    }

    @Override
    public String toString()
    {
        return status+" "+totalResults+" "+news_data;
    }
}
