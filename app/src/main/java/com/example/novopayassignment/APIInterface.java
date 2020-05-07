package com.example.novopayassignment;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {

    @GET("http://newsapi.org/v2/everything?q=bitcoin&from=2020-05-06&sortBy=publishedAt&apiKey=33f49dbd360c4961b2d18f69e7effd40")
    Call<Data> doGetListResources();
}
