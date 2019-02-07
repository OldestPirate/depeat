package com.example.myapplication2.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.myapplication2.R;
import com.example.myapplication2.datamodels.FoodList;
import com.example.myapplication2.ui.adapters.ProductAdapter;

import java.util.ArrayList;

public class CheckoutActivity extends AppCompatActivity {

    RecyclerView foodListRv;
    RecyclerView.LayoutManager layoutManager;
    ProductAdapter adapter;
    ArrayList<FoodList> arrayList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        foodListRv = findViewById(R.id.products_rv);
        layoutManager = new LinearLayoutManager(this);
        adapter = new ProductAdapter(this, getData());

        foodListRv.setLayoutManager(layoutManager);
        foodListRv.setAdapter(adapter);
    }

    private ArrayList<FoodList> getData(){
       arrayList = new ArrayList<>();
        arrayList.add(new FoodList("hamburger", 1.0F));
        arrayList.add(new FoodList("hamburger", 1.0F));
        arrayList.add(new FoodList("hamburger", 1.0F));
        arrayList.add(new FoodList("hamburger", 1.0F));
        arrayList.add(new FoodList("hamburger", 1.0F));
        arrayList.add(new FoodList("hamburger", 1.0F));
        arrayList.add(new FoodList("hamburger", 1.0F));
        arrayList.add(new FoodList("hamburger", 1.0F));
        arrayList.add(new FoodList("hamburger", 1.0F));
        arrayList.add(new FoodList("hamburger", 1.0F));
        arrayList.add(new FoodList("hamburger", 1.0F));
        arrayList.add(new FoodList("hamburger", 1.0F));
        arrayList.add(new FoodList("hamburger", 1.0F));
        arrayList.add(new FoodList("hamburger", 1.0F));
        arrayList.add(new FoodList("hamburger", 1.0F));
        arrayList.add(new FoodList("hamburger", 1.0F));



        return arrayList;
    }
}
