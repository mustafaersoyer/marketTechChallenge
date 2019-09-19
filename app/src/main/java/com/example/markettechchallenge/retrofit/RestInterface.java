package com.example.markettechchallenge.retrofit;

import com.example.markettechchallenge.data.model.Order;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestInterface {

    @GET("/")
    Call<List<Order>> getOrders();
}
