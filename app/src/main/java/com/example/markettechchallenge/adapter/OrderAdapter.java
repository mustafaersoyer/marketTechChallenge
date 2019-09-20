package com.example.markettechchallenge.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.markettechchallenge.R;
import com.example.markettechchallenge.data.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    Context mCtx;
    List<Order> orderList;
    private ItemClickListener mClickListener;


    public OrderAdapter(Context mCtx) {
        this.mCtx = mCtx;
        this.orderList = new ArrayList<>();
    }

    @NonNull
    @Override
    final public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.cardview_order, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Order order = orderList.get(position);
        //ProductDetail productDetail =
        holder.tvDay.setText(order.getDate());
        holder.tvMonth.setText(order.getMonth());
        holder.tvMarket.setText(order.getMarketName());
        holder.tvOrderName.setText(order.getOrderName());
        holder.tvProductState.setText(order.getProductState());
        holder.tvProductPrice.setText(order.getProductPrice()+" TL");
        holder.tvOrderDetail.setText(order.getDate());
        holder.tvSummaryPrice.setText(order.getDate());
        holder.cardView.setOnClickListener(v -> mClickListener.onItemClick(holder.layout));

    }
    public void setItems(List<Order> orderList) {
        this.orderList = orderList;
        notifyDataSetChanged();
    }

    @Override
    final public int getItemCount() {
        return orderList.size();
    }

    public void setClickListener(OrderAdapter.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvDay,tvMonth,tvMarket,tvOrderName,tvProductState,tvProductPrice,tvOrderDetail,tvSummaryPrice;
        LinearLayout layout;
        CardView cardView;
        public ViewHolder(View itemView) {
            super(itemView);
            tvDay = itemView.findViewById(R.id.tvDay);
            tvMonth = itemView.findViewById(R.id.tvMonth);
            tvMarket = itemView.findViewById(R.id.tvMarket);
            tvOrderName = itemView.findViewById(R.id.tvOrderName);
            tvProductState = itemView.findViewById(R.id.tvProductState);
            tvOrderDetail = itemView.findViewById(R.id.tvOrderDetail);
            tvSummaryPrice = itemView.findViewById(R.id.tvSummaryPrice);
            tvProductPrice = itemView.findViewById(R.id.tvProductPrice);
            cardView = itemView.findViewById(R.id.card_view);
            layout = itemView.findViewById(R.id.detail);
        }
    }
    public interface ItemClickListener{
        void onItemClick(LinearLayout layout);
    }
}