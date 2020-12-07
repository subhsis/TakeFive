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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

import static com.example.pupalinksapp.Connection.connect;

public class Documentfragment extends Fragment {
    Button deletead;
    ImageView imageView;
    RelativeLayout adview;
    JsonObjectRequest jsonObjectRequest;
    RequestQueue requestQueue;
    String Url =connect+"get_random_imagead.php";
    String Adpath;
    RecyclerView recyclerViewdocument;
    DocumentAdapter documentAdapter;
    String content_type;
    ArrayList<DocumentModel> arrayList1 = new ArrayList<>();
    String name;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        assert container != null;
        @SuppressLint("InflateParams")
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.document_fragment, null,false);
        deletead = view.findViewById(R.id.addelete);
        adview = view.findViewById(R.id.adview);
        imageView = view.findViewById(R.id.adiamge);
        recyclerViewdocument = view.findViewById(R.id.documentrecyclerview);

        deletead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adview.setVisibility(View.GONE);

            }
        });
        ImageAd();
        jsonDocument();

        return  view;
    }

    private void jsonDocument() {
        String url = connect+"getAvailableList.php";
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {

            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("message");
                    for(int i=0; i<jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        name=jsonObject.getString("name");
                        content_type = jsonObject.getString("contentType");
                        if(content_type.equals("Documents")) {
                            arrayList1.add(new DocumentModel(jsonObject.getString("thumbPath"), name));
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

    private void setrecycleview(ArrayList<DocumentModel> arrayList1) {
        Collections.sort(arrayList1, new Comparator<DocumentModel>(){
            public int compare(DocumentModel obj1, DocumentModel obj2) {
              return obj2.getId().compareToIgnoreCase(obj1.getId()); // To compare string values
            }
        });
        recyclerViewdocument.setItemAnimator(new DefaultItemAnimator());
        recyclerViewdocument.setLayoutManager(new GridLayoutManager(getContext(),2));
        documentAdapter = new DocumentAdapter(arrayList1,getContext());
        recyclerViewdocument.setAdapter(documentAdapter);

    }

    private void ImageAd() {
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,Url,null, new Response.Listener<JSONObject>() {

            public void onResponse(JSONObject response) {

                try {
                    content_type = response.getString("contentType");
                        Adpath = response.getString("path");

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // view setup for data
                Glide.with(Objects.requireNonNull(getActivity())).load(connect+Adpath).into(imageView);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue = Volley.newRequestQueue(Objects.requireNonNull(getContext()));
        requestQueue.add(jsonObjectRequest);
    }
}
