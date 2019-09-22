package com.example.markettechchallenge.adapter;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
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

import java.text.DateFormatSymbols;
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
        changeColor(order,holder);
        holder.tvDay.setText(order.getDate());
        holder.tvMonth.setText(getMonth(order.getMonth()));
        holder.tvMarket.setText(order.getMarketName());
        holder.tvOrderName.setText(order.getOrderName());
        holder.tvProductState.setText(order.getProductState());
        holder.tvProductPrice.setText(order.getProductPrice()+" TL");
        holder.tvOrderDetail.setText(order.productDetail.getOrderDetail());
        holder.tvSummaryPrice.setText(order.productDetail.getSummaryPrice()+" TL");
        holder.cardView.setOnClickListener(v -> mClickListener.onItemClick(holder.layout));

    }
    private String getMonth(int month) {
        return new DateFormatSymbols().getMonths()[month-1];
    } //Ay ismi yazma
    private void changeColor(Order order, ViewHolder holder){
        Drawable[] compoundDrawables=holder.tvProductState.getCompoundDrawables();
        Drawable drawableLeft=compoundDrawables[0].mutate();

        if(order.getProductState().equals("Yolda")){
            holder.tvProductState.setTextColor(mCtx.getResources().getColor(R.color.green));
            drawableLeft.setColorFilter(new PorterDuffColorFilter(mCtx.getResources().getColor(R.color.green), PorterDuff.Mode.SRC_IN));
        } else if (order.getProductState().equals("Hazırlanıyor")){
            holder.tvProductState.setTextColor(mCtx.getResources().getColor(R.color.orange));
            drawableLeft.setColorFilter(new PorterDuffColorFilter(mCtx.getResources().getColor(R.color.orange), PorterDuff.Mode.SRC_IN));
        }else{
            holder.tvProductState.setTextColor(mCtx.getResources().getColor(R.color.textColor));
            drawableLeft.setColorFilter(new PorterDuffColorFilter(mCtx.getResources().getColor(R.color.textColor), PorterDuff.Mode.SRC_IN));
        }
    }//Duruma göre renk değiştirme

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
            tvMarket = itemView.findViewById(R.id.tvMarketName);
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