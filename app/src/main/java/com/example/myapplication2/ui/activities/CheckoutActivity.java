package com.example.myapplication2.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication2.R;
import com.example.myapplication2.datamodels.Order;
import com.example.myapplication2.datamodels.Product;
import com.example.myapplication2.datamodels.Restaurant;
import com.example.myapplication2.ui.adapters.OrderProductsAdapter;
import java.util.ArrayList;

public class CheckoutActivity extends AppCompatActivity implements View.OnClickListener, OrderProductsAdapter.onItemRemovedListener{

    private TextView restaturantTv, restaurantAddress,totalTv;
    private RecyclerView productRv;
    private Button payBtn;
    private LinearLayoutManager layoutManager;
    private OrderProductsAdapter adapter;
    private Order order;
    private float total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        restaturantTv = findViewById(R.id.restaurant_name);
        restaurantAddress = findViewById(R.id.restaurant_adress);
        totalTv = findViewById(R.id.total_tv);
        productRv = findViewById(R.id.product_rv);
        payBtn = findViewById(R.id.pay_btn);

        // Initialize datamodel object
        order = getOrder();
        total = order.getTotal();

        // setup recyclerview
        layoutManager = new LinearLayoutManager(this);
        productRv.setLayoutManager(layoutManager);
        adapter = new OrderProductsAdapter(this,order.getProducts(),order.getRestaurant().getMinimumOrder());
        adapter.setOnItemRemovedListener(this);
        productRv.setAdapter(adapter);

        //set click listener for button
        payBtn.setOnClickListener(this);

        bindData();
    }

    private void bindData(){
        restaturantTv.setText(order.getRestaurant().getName());
        restaurantAddress.setText(order.getRestaurant().getAddress());
        totalTv.setText(String.valueOf(order.getTotal()));
    }

    //TODO hardcoded

    private Order getOrder(){
        Order mockOrder =  new Order();
        mockOrder.setProducts(getProducts());
        mockOrder.setRestaurant(getRestaurant());
        mockOrder.setTotal(30.00f);
        return mockOrder;
    }

    private Restaurant getRestaurant() {
        return new Restaurant("Fraschetta", "Via le mani dal naso", 10);
    }

    //TODO hardcoded
    private ArrayList<Product> getProducts() {
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product("McMenu", 5,2));
        products.add(new Product("McMenu", 5,2));
        products.add(new Product("McMenu", 5,2));

        return products;
    }

    @Override
    public void onClick(View view) {
            //TODO manageClick
    }

    @Override
    public void onItemRemoved(float subtotal) {
        updateTotal(subtotal);
    }

    private void updateTotal(float subtotal) {
        if(total == 0) return;
        total -=subtotal;
        totalTv.setText(String.valueOf(total));
    }
}
