package com.example.pupalinksapp;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import java.util.Objects;

import static com.example.pupalinksapp.Connection.connect;

public class Musicfragment extends Fragment {
    JsonObjectRequest jsonObjectRequest;
    RequestQueue requestQueue;
    String id=null;
    // fragmentactivity view time
    private String content_type;
    RecyclerView recyclerView;
    ArrayList<AudioModel> arrayList1 = new ArrayList<>();
    AudioAdapter audioAdapter;
    String url1;
    public  static  ArrayList<Audioplayurl> audioplayurls;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        assert container != null;
        @SuppressLint("InflateParams")
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.music_fragment, null);
        recyclerView = view.findViewById(R.id.audio_view);
        jsonrequest();
        return view;
    }

    private void jsonrequest() {
        audioplayurls = new ArrayList<>();
        final String url = connect+"getAvailableList.php";
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {

            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("message");
                    for(int i=0; i<jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        id=jsonObject.getString("id");
                        content_type = jsonObject.getString("contentType");
                        if(content_type.equals("Audio")) {
                            url1 =connect+"getStream.php?id="+id;
                            arrayList1.add(new AudioModel(jsonObject.getString("thumbPath"), url1,jsonObject.getString("title"),jsonObject.getString("format")));
                            audioplayurls.add(new Audioplayurl(url1));

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

        requestQueue =Volley.newRequestQueue(Objects.requireNonNull(getContext()));
        requestQueue.add(jsonObjectRequest);

    }


    private void setrecycleview(ArrayList<AudioModel> arrayList1) {

        
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,true));
        audioAdapter = new AudioAdapter(arrayList1,getContext());
        recyclerView.setAdapter(audioAdapter);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }
}
