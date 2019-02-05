package com.example.myapplication2.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication2.R;
import com.example.myapplication2.datamodels.Restaurant;
import com.example.myapplication2.ui.activities.MainActivity;

import java.util.ArrayList;

public class RestaurantAdapter extends RecyclerView.Adapter {
    LayoutInflater inflater;
    private ArrayList<Restaurant> data;

    public RestaurantAdapter(Context context, ArrayList<Restaurant> data){
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.item_restaurant, viewGroup, false);
        return new RestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int pos) {
        RestaurantViewHolder vh = (RestaurantViewHolder) viewHolder;
        vh.restaurantNameTv.setText(data.get(pos).getName());
        vh.restaurantAddressTv.setText(data.get(pos).getAddress());
        vh.restaurantPhoneNumberTv.setText(data.get(pos).getPhoneNumber());
        vh.restaurantIconIv.setImageResource(data.get(pos).getImage());
        vh.restaurantMinOrderTv.setText("ordine minimo: " + data.get(pos).getMinOrder() + "€");
        vh.externalLayoutLl.setOrientation(MainActivity.orientation);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class RestaurantViewHolder extends RecyclerView.ViewHolder{

        public TextView restaurantNameTv;
        public TextView restaurantAddressTv;
        public TextView restaurantPhoneNumberTv;
        public ImageView restaurantIconIv;
        public TextView restaurantMinOrderTv;
        public LinearLayout externalLayoutLl;

        public RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);
            restaurantNameTv = itemView.findViewById(R.id.restaurant_name_tv);
            restaurantAddressTv = itemView.findViewById(R.id.restaurant_address_tv);
            restaurantPhoneNumberTv = itemView.findViewById(R.id.restaurant_phone_number_tv);
            restaurantIconIv = itemView.findViewById(R.id.restaurant_iv);
            restaurantMinOrderTv = itemView.findViewById(R.id.restaurant_min_order_tv);
            externalLayoutLl = itemView.findViewById(R.id.external_linear_layout_ll);
        }
    }
}
