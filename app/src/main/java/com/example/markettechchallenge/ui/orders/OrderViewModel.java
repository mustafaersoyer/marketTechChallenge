package com.example.markettechchallenge.ui.orders;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.markettechchallenge.data.model.Order;
import com.example.markettechchallenge.retrofit.ApiClient;
import com.example.markettechchallenge.retrofit.RestInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderViewModel extends ViewModel {
    private MutableLiveData<List<Order>> orderList;

    final public LiveData<List<Order>> getCampaignsContent() {
        if (orderList == null) {
            orderList = new MutableLiveData<>();
            loadContent();
        }
        return orderList;
    }

    final private void loadContent() {
        final RestInterface api = ApiClient.getClient().create(RestInterface.class);

        Call<List<Order>> call = api.getOrders();
        call.enqueue(new Callback<List<Order>>() {
            @Override
            final public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                orderList.setValue(response.body());
            }

            @Override
            final public void onFailure(Call<List<Order>> call, Throwable t) {
                Log.d("failTag", "fail "+t);
            }
        });
    }
}

