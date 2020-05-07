package com.example.novopayassignment;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

    @SerializedName("articles")
    public List<NewsSourceList> newsSourceListList;
}
