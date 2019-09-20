package com.example.markettechchallenge.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Order {

    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("month")
    @Expose
    private int month;
    @SerializedName("marketName")
    @Expose
    private String marketName;
    @SerializedName("orderName")
    @Expose
    private String orderName;
    @SerializedName("productState")
    @Expose
    private String productState;
    @SerializedName("productPrice")
    @Expose
    private String productPrice;

    @SerializedName("productDetail")
    @Expose
    public ProductDetail productDetail;



    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getProductState() {
        return productState;
    }

    public void setProductState(String productState) {
        this.productState = productState;
    }


    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

}