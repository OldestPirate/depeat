package com.example.myapplication2.services;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.Map;

public class RestController {

    private final static String BASE_URL = "http://138.68.86.70/";
    private final static String VERSION = "";
    private RequestQueue queue;

    public RestController(Context context){
        queue = Volley.newRequestQueue(context);
    }

    public void getRequest(String endpoint, Response.Listener<String> success, Response.ErrorListener error){
        StringRequest request = new StringRequest(Request.Method.GET,
                BASE_URL.concat(VERSION).concat(endpoint),
                success,
                error
                );
        queue.add(request);
    }

    public void postRequest(String endpoint, final Map<String,String> params, Response.Listener<String> success, Response.ErrorListener error){
        String url = BASE_URL.concat(VERSION).concat(endpoint);
        StringRequest request = new StringRequest(Request.Method.POST, url, success,error) {
           @Override
            protected Map<String, String> getParams(){
               return params;
           }
        };
        queue.add(request);
    }
}
