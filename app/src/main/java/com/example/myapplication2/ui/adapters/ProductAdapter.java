package com.example.myapplication2.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication2.R;
import com.example.myapplication2.datamodels.FoodList;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter {

    LayoutInflater inflater;
    private ArrayList<FoodList> data;

    public ProductAdapter(Context context, ArrayList<FoodList> data){
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.item_food_list, viewGroup, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int pos) {
        ProductViewHolder vh = (ProductViewHolder) viewHolder;
        vh.productNameTv.setText(data.get(pos).getName());
        vh.productPriceTv.setText((int)data.get(pos).getPrice() + "€");

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{

        public TextView productNameTv;
        public TextView productPriceTv;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productNameTv = itemView.findViewById(R.id.product_name_tv);
            productPriceTv = itemView.findViewById(R.id.product_price_tv);
        }
    }
}
