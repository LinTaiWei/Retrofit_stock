package com.stock.myapplication;

import com.stock.myapplication.Model.StockResponse_s;
import com.stock.myapplication.Model.StockResponse_b;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface StockApi {

    /**
     * 每日盤後所有個股最高最低收盤漲跌價格
     * ex: http://www.twse.com.tw/exchangeReport/STOCK_DAY_ALL?response=json
     */
    @GET("exchangeReport/STOCK_DAY_ALL")
    Call<StockResponse_s> callAllStockInfoPerDay(@Query("response") String response);

    /**
     * 上市個股日本益比、殖利率及股價淨值比（依日期查詢）
     * ex: http://www.twse.com.tw/exchangeReport/BWIBBU_d?response=json
     */
    @GET("exchangeReport/BWIBBU_d")
    Call<StockResponse_b> callStockInfoBWIBBU_dy(@Query("response") String response,
                                                 @Query("date") String date);
}
