package com.example.pupalinksapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
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
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.example.pupalinksapp.Connection.connect;

public class Videofragment extends Fragment {
    //url for json parse
    private  String Url = connect+"getAvailableList.php";
    String Url1 =connect+"getrandom_ad.php";
    String catagories[]={"All","Action","Comedy","Funny video","Drama","Family","Kids","Romance","Thriller","Top Charts","Retro","Party","Rock","Dance","Remix","Daily Drama","Reality","Horror","Family Soap"};
    JsonObjectRequest jsonObjectRequest,jsonObjectRequest2,jsonObjectRequest3;
    RequestQueue requestQueue,requestQueue2,requestQueue3;
    String id,id2;
    @SuppressLint("StaticFieldLeak")
    Spinner spinner;
    String content_type;
    RecyclerView recyclerView;
    ArrayList<Model> arrayList = new ArrayList<>();
    ArrayList<Model> arrayList1;
    ViewAdapter viewAdapter;
    String genre,image;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        assert container != null;
        @SuppressLint("InflateParams")
        View view=LayoutInflater.from(container.getContext()).inflate(R.layout.video_fragment,null);

        recyclerView= view.findViewById(R.id.videoview);
        spinner = view.findViewById(R.id.spinner);
        spinner.setAdapter(new ArrayAdapter<>(Objects.requireNonNull(getContext()),android.R.layout.simple_list_item_1,catagories));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position >=0 && position<catagories.length){
                    @SuppressLint("UseSparseArrays")
                    Map<Integer,String> filter =new HashMap<>();
                    filter.put(0,"All");
                    filter.put(1,"Action");
                    filter.put(2,"Comedy");
                    filter.put(3,"Funny video");
                    filter.put(4,"Drama");
                    filter.put(5,"Family");
                    filter.put(6,"Kids");
                    filter.put(7,"Romance");
                    filter.put(8,"Thriller");
                    filter.put(9,"Top Charts");
                    filter.put(10,"Retro");
                    filter.put(11,"Party");
                    filter.put(12,"Rock");
                    filter.put(13,"Dance");
                    filter.put(14,"Remix");
                    filter.put(15,"Daily Drama");
                    filter.put(16,"Reality");
                    filter.put(17,"Horror");
                    filter.put(18,"Family Soap");
                    filter.put(19,"");

                   getcatagoriesdata(filter.get(position));


                }else{
                    Toast.makeText(getContext(),"Selected catagory not found",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        // json request for server data
        jsonrequest();
        jsonAd();
        return view;
    }

   //filter on spinner item click........

    private void getcatagoriesdata(final String name) {


        jsonObjectRequest3 = new JsonObjectRequest(Request.Method.GET, Url, null, new Response.Listener<JSONObject>() {

            public void onResponse(JSONObject response) {

                try {
                    arrayList1 = new ArrayList<>();
                    JSONArray jsonArray = response.getJSONArray("message");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        id = jsonObject.getString("id");
                        content_type = jsonObject.getString("contentType");
                        image = jsonObject.getString("thumbPath");
                        genre = jsonObject.getString("genre");
                        if (name.equals("All")) {
                            if (content_type.equals("Video")) {
                                arrayList1.add(new Model(image, id, jsonObject.getString("title"), id2));
                        }}else if (genre.equals(name)) {
                            if (content_type.equals("Video")) {
                                arrayList1.add(new Model(image, id, jsonObject.getString("title"), id2));
                            }
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // view setup for data
                setrecycleview(arrayList1);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue3 = Volley.newRequestQueue(Objects.requireNonNull(getContext()));
        requestQueue3.add(jsonObjectRequest3);


    }


         //video_ad json data parse through id...........
    private void jsonAd() {
            jsonObjectRequest2 = new JsonObjectRequest(Request.Method.GET,Url1,null,new Response.Listener<JSONObject>() {

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

            requestQueue2 = Volley.newRequestQueue(Objects.requireNonNull(getContext()));
            requestQueue2.add(jsonObjectRequest2);
        }


    private void jsonrequest() {
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,Url,null, new Response.Listener<JSONObject>() {

                public void onResponse(JSONObject response) {

                    try {
                        JSONArray jsonArray = response.getJSONArray("message");
                        for(int i=0; i<jsonArray.length(); i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            id=jsonObject.getString("id");
                            content_type = jsonObject.getString("contentType");
                            image = jsonObject.getString("thumbPath");
                            genre=jsonObject.getString("genre");
                           if(content_type.equals("Video")) {
                                arrayList.add(new Model(image,id,jsonObject.getString("title"),id2));
                            }
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    // view setup for data
                setrecycleview(arrayList);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue =  Volley.newRequestQueue(Objects.requireNonNull(getContext()));
        requestQueue.add(jsonObjectRequest);

    }

    private void setrecycleview(ArrayList<Model> arrayList) {

        Collections.sort(arrayList, new Comparator<Model>(){
            public int compare(Model obj1, Model obj2) {
                return (int) Integer.valueOf(obj2.getId()).compareTo(Integer.valueOf(obj1.getId())); // To compare integer values

            }
        });
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        viewAdapter = new ViewAdapter(arrayList, getContext());
        viewAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(viewAdapter);

    }


}
