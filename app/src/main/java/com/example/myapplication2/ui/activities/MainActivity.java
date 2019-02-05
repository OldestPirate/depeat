package com.example.myapplication2.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.myapplication2.R;
import com.example.myapplication2.datamodels.Restaurant;
import com.example.myapplication2.ui.adapters.RestaurantAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private boolean layoutActualStateIsGrid = false;
    public static int orientation;
    RecyclerView restaurantRV;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.LayoutManager layoutManager2;
    RestaurantAdapter adapter;
    ArrayList<Restaurant> arrayList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        restaurantRV = findViewById(R.id.places_rv);
        layoutManager = new LinearLayoutManager(this);
        layoutManager2 = new GridLayoutManager(this, 2);
        adapter = new RestaurantAdapter(this, getData());

        restaurantRV.setLayoutManager(layoutManager);
        restaurantRV.setAdapter(adapter);
    }

    private ArrayList<Restaurant> getData(){
        arrayList = new ArrayList<>();
        arrayList.add(new Restaurant("ristorante 1", "via rossi 00 roma", "+39 06 35627895", R.drawable.ic_restaurant, 15));
        arrayList.add(new Restaurant("fastfood 1", "via bianchi 11 roma", "+39 06 31589493", R.drawable.ic_fastfood, 12));
        arrayList.add(new Restaurant("fastfood 2", "via bianchi 11 roma", "+39 06 31589493", R.drawable.ic_fastfood, 10));
        arrayList.add(new Restaurant("fastfood 3", "via bianchi 11 roma", "+39  06 31589493", R.drawable.ic_fastfood, 18));
        arrayList.add(new Restaurant("ristorante 2", "via rossi 00 roma", "+39 06 35627895", R.drawable.ic_restaurant, 15));
        arrayList.add(new Restaurant("pub", "via dublino 77 roma", "+39 06 87571737", R.drawable.ic_bar, 5));
        arrayList.add(new Restaurant("ristorante 3", "via rossi 00 roma", "+39 06 35627895", R.drawable.ic_restaurant, 20));

        return arrayList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.login_menu) {
            startActivity(new Intent(this, LoginActivity.class));
            return true;
        } else if (item.getItemId() == R.id.checkout_menu){
            startActivity(new Intent(this, CheckoutActivity.class));
            return true;
        } else if (item.getItemId() == R.id.grid_menu){
            if (layoutActualStateIsGrid == false){
                restaurantRV.setLayoutManager(layoutManager2);
                restaurantRV.setAdapter(adapter);
                orientation = 1;
                layoutActualStateIsGrid = true;
            } else if(layoutActualStateIsGrid == true){
                restaurantRV.setLayoutManager(layoutManager);
                restaurantRV.setAdapter(adapter);
                orientation = 0;
                layoutActualStateIsGrid = false;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
