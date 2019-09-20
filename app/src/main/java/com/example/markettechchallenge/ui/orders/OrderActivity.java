package com.example.markettechchallenge.ui.orders;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.markettechchallenge.R;
import com.example.markettechchallenge.adapter.OrderAdapter;
import com.example.markettechchallenge.data.model.Order;

import java.util.List;

public class OrderActivity extends AppCompatActivity implements OrderAdapter.ItemClickListener {

    OrderViewModel orderViewModel;
    RecyclerView recyclerView;
    OrderAdapter orderAdapter;
    Button btnExit;
    boolean openClose=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        orderViewModel =
                ViewModelProviders.of(this).get(OrderViewModel.class);

        recyclerView = findViewById(R.id.rvOrders);
        btnExit = findViewById(R.id.btnExit);
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
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(OrderActivity.this);
                builder.setTitle("Marketim");
                builder.setMessage("Çıkış yapmak istediğinize emin misiniz?");
                builder.setNegativeButton("Hayır", null);
                builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
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
