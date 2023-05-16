package com.stock.myapplication.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class StockResponse_s {

    @SerializedName("data")
    private List<List<String>> mStockList = new ArrayList<>();

    public List<List<String>> getStockList() {
        return mStockList;
    }

}
