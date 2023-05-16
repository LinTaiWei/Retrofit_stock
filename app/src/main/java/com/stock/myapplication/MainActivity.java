package com.stock.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.stock.myapplication.Model.Stock;
import com.stock.myapplication.Model.StockResponse_s;
import com.stock.myapplication.Model.StockResponse_b;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private Retrofit mRetrofit;
    public static final String BASE_STOCK_URL = "https://www.twse.com.tw/";
    public static final int PRICE = 300000000;//三億
    public static final double PB = 1.2;
    public static final int PE = 20;
    private List<Stock> stockRangeInfoDetailList = new ArrayList<>();
    private StockApi service;
    RecyclerView recyclerView;
    StockListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        adapter = new StockListAdapter();
        recyclerView.setAdapter(adapter);
        service = getBaseStockRetrofit().create(StockApi.class);
        service.callAllStockInfoPerDay("json").enqueue(GetStockPerDay());
    }

    private Retrofit getBaseStockRetrofit() {
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_STOCK_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(new OkHttpClient())
                    .build();
        }
        return mRetrofit;
    }

    private Callback<StockResponse_b> GetStock_BWIBBU_d() {
        //region Call 每日收盤個股資訊
        Callback<StockResponse_b> apiCallGetStockPerDay = new Callback<StockResponse_b>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(@NonNull Call<StockResponse_b> call, @NonNull Response<StockResponse_b> response) {
                try {
                    if (response.isSuccessful() && response.body() != null) {
                        Observable.fromIterable(response.body().getStockList())
                                .flatMap(stockData -> Observable.fromIterable(stockRangeInfoDetailList)
                                        .filter(stock -> stock.getStockId().equals(stockData.get(0)))
                                        .doOnNext(stock -> {
                                            stock.setPEratio(stockData.get(4));
                                            stock.setPBratio(stockData.get(5));

                                        }))
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(stock -> {
                                    if (stock.getPEratio() != null && !stock.getPEratio().isEmpty()
                                            && !stock.getPEratio().matches("-")) {
                                        if (Double.parseDouble(stock.getPEratio()) < PE
                                                && Double.parseDouble(stock.getPBratio()) < PB) {
                                            adapter.addItem(stock);
                                            adapter.notifyDataSetChanged();
                                        }
                                    }
                                });

                    }

                } catch (Exception ignored) {
                }
            }

            @Override
            public void onFailure(@NonNull Call<StockResponse_b> call, @NonNull Throwable t) {
            }
        };
        return apiCallGetStockPerDay;
    }

    private Callback<StockResponse_s> GetStockPerDay() {
        //region Call 每日收盤個股資訊
        Callback<StockResponse_s> apiCallGetStockPerDay = new Callback<StockResponse_s>() {
            @Override
            public void onResponse(@NonNull Call<StockResponse_s> call, @NonNull Response<StockResponse_s> response) {
                try {
                    if (response.isSuccessful() && response.body() != null) {
                        //region 將API 字串陣列 轉換為 資料
                        stockRangeInfoDetailList.clear();
                        for (List<String> stockData : response.body().getStockList()) {
                            long number = Long.parseLong(stockData.get(3).replace(",", ""));
                            if (number > PRICE) {
                                stockRangeInfoDetailList.add(new Stock(stockData.get(0)
                                        , stockData.get(1), stockData.get(2), stockData.get(3)
                                        , stockData.get(4), stockData.get(5), stockData.get(6)
                                        , stockData.get(7), stockData.get(8), stockData.get(9)
                                        , "", "", "", "", ""));
                            }
                        }
                        stockRangeInfoDetailList.sort(Comparator.comparingLong(Stock::getTradingValue).reversed());
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                        String currentDate = sdf.format(new Date());
                        service.callStockInfoBWIBBU_dy("json", currentDate).enqueue(GetStock_BWIBBU_d());
                    }
                } catch (Exception ignored) {
                }
            }

            @Override
            public void onFailure(@NonNull Call<StockResponse_s> call, @NonNull Throwable t) {
            }
        };
        return apiCallGetStockPerDay;
    }
}