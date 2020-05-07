package com.example.novopayassignment;

import com.google.gson.annotations.SerializedName;

public class NewsSourceList {

    @SerializedName("source")
    public Source source;
    @SerializedName("author")
    public String author;
    @SerializedName("title")
    public String title;
    @SerializedName("description")
    public String descripiton;
    @SerializedName("url")
    public String url;
    @SerializedName("urlToImage")
    public String urlToImage;
    @SerializedName("publishedAt")
    public String publishedAt;
    @SerializedName("content")
    public String content;


}

