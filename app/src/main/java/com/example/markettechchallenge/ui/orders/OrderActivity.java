package com.example.markettechchallenge.ui.orders;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.markettechchallenge.R;
import com.example.markettechchallenge.adapter.OrderAdapter;
import com.example.markettechchallenge.data.model.Order;

import java.util.List;

public class OrderActivity extends AppCompatActivity implements OrderAdapter.ItemClickListener {

    OrderViewModel orderViewModel;
    RecyclerView recyclerView;
    OrderAdapter orderAdapter;
    boolean openClose=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        orderViewModel =
                ViewModelProviders.of(this).get(OrderViewModel.class);

        recyclerView = findViewById(R.id.rvOrders);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        orderAdapter = new OrderAdapter(this);

        recyclerView.setAdapter(orderAdapter);
        orderViewModel.getCampaignsContent().observe(this, new Observer<List<Order>>() {
            @Override
            public void onChanged(@Nullable List<Order> orderList) {
                orderAdapter.setItems(orderList);
            }
        });

        orderAdapter.setClickListener(this);
    }
    
    @Override
    public void onItemClick(LinearLayout layout) {
        if(openClose){
            layout.setVisibility(View.VISIBLE);
            openClose = false;
        }else{
            layout.setVisibility(View.GONE);
            openClose = true;
        }
    }
}
