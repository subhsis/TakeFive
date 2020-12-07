package com.example.pupalinksapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.WindowManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static com.example.pupalinksapp.Connection.connect;

public class Musicvideo extends AppCompatActivity {
    RecyclerView muscivideo;
    JsonObjectRequest jsonObjectRequest,jsonObjectRequest2;
    RequestQueue requestQueue,requestQueue2;
    private String id,catagory,image,id2,content_type;
    ArrayList<Model> arrayList = new ArrayList<>();
    ViewAdapter viewAdaptermusicvideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musicvideo);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        muscivideo = findViewById(R.id.musicvideo);
        jsonAd();
        String Url = connect+"getAvailableList.php";
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,Url,null, new Response.Listener<JSONObject>() {

            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("message");
                    for(int i=0; i<jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        id=jsonObject.getString("id");
                        image = jsonObject.getString("thumbPath");
                        catagory=jsonObject.getString("category");
                        content_type = jsonObject.getString("contentType");
                        if(content_type.equals("Video")) {
                            if (catagory.equals("Music Videos")) {
                                arrayList.add(new Model(image, id, jsonObject.getString("title"), id2));
                            }
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // view setup for data
                setrecycleview2(arrayList);

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue =  Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }

    private void jsonAd() {
        String Url1 =connect+"getrandom_ad.php";
        jsonObjectRequest2 = new JsonObjectRequest(Request.Method.GET,Url1,null, new Response.Listener<JSONObject>() {

            public void onResponse(JSONObject response) {

                try {

                    id2 =response.getString("id");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // view setup for data

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue2 = Volley.newRequestQueue(this);
        requestQueue2.add(jsonObjectRequest2);
    }

    private void setrecycleview2(ArrayList<Model> arrayList) {
        Collections.sort(arrayList, new Comparator<Model>(){
            public int compare(Model obj1, Model obj2) {
                return (int) Integer.valueOf(obj2.getId()).compareTo(Integer.valueOf(obj1.getId())); // To compare integer values

            }
        });
        muscivideo.setItemAnimator(new DefaultItemAnimator());
        muscivideo.setLayoutManager(new GridLayoutManager(this,2));
        viewAdaptermusicvideo = new ViewAdapter(arrayList, this);
        viewAdaptermusicvideo.notifyDataSetChanged();
        muscivideo.setAdapter(viewAdaptermusicvideo);
    }
}
