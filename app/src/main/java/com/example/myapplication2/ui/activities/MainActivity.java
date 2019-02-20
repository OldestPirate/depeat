package com.example.myapplication2.ui.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.myapplication2.R;
import com.example.myapplication2.datamodels.Restaurant;
import com.example.myapplication2.services.RestController;
import com.example.myapplication2.ui.adapters.RestaurantAdapter;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Response.Listener<String>, Response.ErrorListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    RecyclerView restaurantRV;
    RecyclerView.LayoutManager layoutManager;
    RestaurantAdapter adapter;
    ArrayList<Restaurant> arrayList = new ArrayList<>();
    private RestController restController;
    SharedPreferences sharedPreferences;
    private static final String SharedPrefs = "org.elis.depeat.general_prefs";
    private static final String VIEW_MODE = "VIEW_MODE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        restaurantRV = findViewById(R.id.places_rv);
        layoutManager = getLayoutManager(getSavedLayoutManager());
        adapter = new RestaurantAdapter(this);
        adapter.setGridMode(getSavedLayoutManager());
        restaurantRV.setLayoutManager(layoutManager);
        restaurantRV.setAdapter(adapter);
        restController = new RestController(this);
        restController.getRequest(Restaurant.ENDPOINT,this,this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        menu.findItem(R.id.view_mode).setIcon(getSavedLayoutManager()?R.drawable.ic_list_white_24dp:R.drawable.ic_grid_on_white_24dp);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.view_mode){
            setLayoutManager();
            item.setIcon(adapter.isGridMode()?R.drawable.ic_list_white_24dp : R.drawable.ic_grid_on_white_24dp);
            return true;
        }
        if(item.getItemId() == R.id.login_menu){

            startActivity(new Intent(this,LoginActivity.class));
             return true;
        }else if(item.getItemId() == R.id.checkout_menu){
            startActivity(new Intent(this,CheckoutActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setLayoutManager(){
        layoutManager = getLayoutManager(!adapter.isGridMode());
        adapter.setGridMode(!adapter.isGridMode());
        restaurantRV.setLayoutManager(layoutManager);
        restaurantRV.setAdapter(adapter);
        saveLayoutManager(adapter.isGridMode());
    }

    private void saveLayoutManager(boolean isGridLayout){
        sharedPreferences = getSharedPreferences(SharedPrefs,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(VIEW_MODE,isGridLayout);
        editor.apply();
    }

    private RecyclerView.LayoutManager getLayoutManager(boolean isGridLayout){
        return isGridLayout? new GridLayoutManager(this,2) : new LinearLayoutManager(this);
    }

    private boolean getSavedLayoutManager(){
        sharedPreferences = getSharedPreferences(SharedPrefs,MODE_PRIVATE);
        return sharedPreferences.getBoolean(VIEW_MODE,false);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e(TAG,error.getMessage());
        Toast.makeText(this,error.getMessage(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(String response) {
        try {
            JSONArray jsonArray = new JSONArray(response);
            for (int i = 0; i<jsonArray.length();i++){
                arrayList.add(new Restaurant(jsonArray.getJSONObject(i)));
            }
            adapter.setData(arrayList);
        } catch (JSONException e) {
            Log.e(TAG,e.getMessage());
        }
    }
}
