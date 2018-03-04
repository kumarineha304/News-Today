package com.example.nehakumari.newstoday.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by NEHA KUMARI on 03-03-2018.
 */

public class News_Data {

    //private List<Source> sourceList;
    @SerializedName("source")
    @Expose
    private Source source;
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;

    public News_Data(Source source,String author, String title, String description, String url, String urlToImage, String publishedAt) {
      this.source = source;
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void seturlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public void setpublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }


    public Source getSource() {
        return source;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String geturlToImage() {
        return urlToImage;
    }

    public String getpublishedAt() {
        return publishedAt;
    }
    @Override
    public String toString(){
        return source+" "+author+" "+title+" "+description+" "+url+" "+urlToImage+" "+publishedAt;
    }
}
