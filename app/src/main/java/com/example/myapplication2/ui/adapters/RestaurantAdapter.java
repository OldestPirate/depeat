package com.example.myapplication2.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.myapplication2.R;
import com.example.myapplication2.datamodels.Restaurant;
import com.example.myapplication2.ui.activities.ShopActivity;
import java.util.ArrayList;

public class RestaurantAdapter extends RecyclerView.Adapter {
    private LayoutInflater inflater;
    private ArrayList<Restaurant> data;
    private Context context;
    private boolean isGridMode;

    public RestaurantAdapter(Context context, ArrayList<Restaurant> data){
        inflater = LayoutInflater.from(context);
        this.data = data;
        this.context = context;
    }

    public RestaurantAdapter(Context context){
        inflater = LayoutInflater.from(context);
        this.data = new ArrayList<>();
        this.context = context;
    }

    public void setGridMode(boolean gridMode) {
        isGridMode = gridMode;
    }

    public boolean isGridMode() {
        return isGridMode;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        int layout = isGridMode? R.layout.item_restaurant_grid : R.layout.item_restaurant;
        View view = inflater.inflate(layout,viewGroup,false);
        return new RestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        RestaurantViewHolder vh = (RestaurantViewHolder) viewHolder;
        Restaurant item = data.get(position);
        vh.restaurantName.setText(item.getName());
        vh.descriptionTv.setText(item.getDescription());
        vh.minOrderTv.setText(context.getString(R.string.min_order).concat(String.valueOf(item.getMinimumOrder())));
        Glide.with(context).load(item.getImageUrl()).into(vh.image);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(ArrayList<Restaurant> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public class RestaurantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView restaurantName,descriptionTv,minOrderTv;
        public ImageView image;
        public Button menuBtn;

        public RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);
            restaurantName = itemView.findViewById(R.id.name_tv);
            image = itemView.findViewById(R.id.image);
            descriptionTv = itemView.findViewById(R.id.description);
            minOrderTv = itemView.findViewById(R.id.min_order);
            menuBtn = itemView.findViewById(R.id.menu_btn);
            menuBtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(view.getId() == R.id.menu_btn){
                Intent intent = new Intent(context, ShopActivity.class);
                intent.putExtra(ShopActivity.RESTAURANT_ID_KEY,data.get(getAdapterPosition()).getId());
                context.startActivity(intent);
            }
        }
    }
}
