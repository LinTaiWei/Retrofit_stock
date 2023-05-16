package com.stock.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stock.myapplication.Model.Stock;

import java.util.ArrayList;

public class StockListAdapter extends RecyclerView.Adapter<StockListAdapter.ItemViewHolder> {

    final ArrayList<Stock> items = new ArrayList<>();

    public StockListAdapter() {
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.stock_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.onBind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(Stock stock) {
        items.add(stock);
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        final TextView cityNameTextView;
        final TextView priceChangeTextView;
        final TextView tradingValueTextView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            cityNameTextView = itemView.findViewById(R.id.stockNameTextView);
            priceChangeTextView = itemView.findViewById(R.id.priceChangeTextView);
            tradingValueTextView = itemView.findViewById(R.id.tradingValueTextView);
        }

        public void onBind(Stock Stock) {
            cityNameTextView.setText(Stock.getStockName()+" "+Stock.getClosingPrice());
            priceChangeTextView.setText( "漲幅：" +Stock.getPriceChange());
            tradingValueTextView.setText("成交金額：" +Stock.getTradingValue2());
        }
    }
}
