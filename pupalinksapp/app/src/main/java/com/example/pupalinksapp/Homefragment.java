package com.example.pupalinksapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
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
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import static com.example.pupalinksapp.Connection.connect;
import static com.example.pupalinksapp.Musicfragment.audioplayurls;


public class Homefragment extends Fragment {
    JsonObjectRequest jsonObjectRequest,jsonObjectRequest2;
    RequestQueue requestQueue,requestQueue2;
    private String id,Adpath,id2,category,url1;
    RelativeLayout view_layout;
    private String content_type;
    TextView more_film,more_music_video;
    ImageView flipper;
    HomeAudioviewAdapter homeAudioviewAdapter;
    ViewAdapter viewAdapter,viewAdapter2,viewAdapter3;
    DocumentAdapter documentAdapter;
    private Activity mactivity;
    HomeVideoviewAdapter homeVideoviewAdapter;
    SliderpageAdapter sliderpageAdapter;

    RecyclerView recyclerViewvideo,recyclerViewfilm,recyclerViewMusid_video,recyclerViewaudio,recyclerViewdocument;

    ArrayList<Model> arrayList = new ArrayList<>();
    ArrayList<AudioModel> arrayList1 = new ArrayList<>();
    ArrayList<DocumentModel> arrayList2 = new ArrayList<>();
    ArrayList<Model> arrayList3 = new ArrayList<>();
    ArrayList<Model> arrayList4 = new ArrayList<>();
    //ArrayList<Model> listslide = new ArrayList<>();
    ViewPager pager;
    TabLayout tabLayout;







    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        assert container != null;
        @SuppressLint("InflateParams")
        View view=LayoutInflater.from(container.getContext()).inflate(R.layout.home_fragment,null);

        flipper = view.findViewById(R.id.flipper);
        recyclerViewvideo = view.findViewById(R.id.recycleviewhome);
        recyclerViewfilm = view.findViewById(R.id.recycleviewhome4);
        recyclerViewMusid_video = view.findViewById(R.id.recycleviewhome5);
        recyclerViewaudio = view.findViewById(R.id.recycleviewhome2);
        recyclerViewdocument = view.findViewById(R.id.recycleviewhome3);
        view_layout = view.findViewById(R.id.view_layout);
        more_film = view.findViewById(R.id.more1);
        more_music_video = view.findViewById(R.id.more2);
        pager = view.findViewById(R.id.slider);
        tabLayout = view.findViewById(R.id.tab);

      //  listslide = new ArrayList<>();
//        listslide.add(new Slider(R.drawable.thor,"Thor Ragnarok"));
//        listslide.add(new Slider(R.drawable.batman2,"Batman vs Suparman"));
//        listslide.add(new Slider(R.drawable.wolverine,"Wolverine"));





        tabLayout.setupWithViewPager(pager,true);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new Homefragment.Sliddertime(),4000,6000);



        more_film.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),Film.class);
                startActivity(intent);
            }
        });
        more_music_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getContext(),Musicvideo.class);
                startActivity(intent);
            }
        });

        jsonrequest();
        jsonimagead();
        jsonvideoAd();

        return view;
    }

    private void jsonvideoAd() {
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

        requestQueue2 = Volley.newRequestQueue(Objects.requireNonNull(getContext()));
        requestQueue2.add(jsonObjectRequest2);
    }

    private void jsonimagead() {
        String adurl =connect+"get_random_imagead.php";
        jsonObjectRequest2 = new JsonObjectRequest(Request.Method.GET,adurl,null, new Response.Listener<JSONObject>() {

            public void onResponse(JSONObject response) {

                try {
                    content_type = response.getString("contentType");
                    Adpath = response.getString("path");

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // view setup for image_ad data
                setbannerad();




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue2 = Volley.newRequestQueue(Objects.requireNonNull(getContext()));
        requestQueue2.add(jsonObjectRequest2);
    }

    private void setbannerad() {
        Glide.with(Objects.requireNonNull(getActivity())).load(connect+Adpath).into(flipper);
    }


    private void jsonrequest() {
        audioplayurls = new ArrayList<>();
        String url = connect+"getAvailableList.php";

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {

            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("message");
                    for(int i=0; i<jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        id=jsonObject.getString("id");
                        content_type = jsonObject.getString("contentType");
                        category = jsonObject.getString("category");

               //         listslide.add(new Slider(jsonObject.getString("thumbpath"),jsonObject.getString("title"),id));

                        switch (content_type) {
                            case "Video":
                               arrayList.add(new Model(jsonObject.getString("thumbPath"), id, jsonObject.getString("title"), id2));
                                switch (category) {
                                    case "Film":
                                        arrayList3.add(new Model(jsonObject.getString("thumbPath"), id, jsonObject.getString("title"), id2));
                                        break;
                                    case "Music Videos":
                                        arrayList4.add(new Model(jsonObject.getString("thumbPath"), id, jsonObject.getString("title"), id2));
                                        break;
                                }
                                break;
                            case "Audio":
                                //getting url using id
                                url1 =connect+"getStream.php?id="+id;
                                arrayList1.add(new AudioModel(jsonObject.getString("thumbPath"), url1, jsonObject.getString("title"), jsonObject.getString("format")));
                                //play audio in circular array...
                                audioplayurls.add(new Audioplayurl(url1));
                                break;
                            case "Documents":
                                arrayList2.add(new DocumentModel(jsonObject.getString("thumbPath"), jsonObject.getString("name")));
                                break;
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

        requestQueue =Volley.newRequestQueue(Objects.requireNonNull(getContext()));
        requestQueue.add(jsonObjectRequest);

    }

    private void setrecycleview(ArrayList<Model> arrayList) {
        //to make view in decending order....
        Collections.sort(arrayList, new Comparator<Model>(){
            public int compare(Model obj1, Model obj2) {
                // ## Ascending order
                // return obj1.getId().compareToIgnoreCase(obj2.getId()); // To compare string values
                // return Integer.valueOf(obj1.getId()).compareTo(obj2.getId()); // To compare integer values
                // ## Descending order
                // return obj2.getId().compareToIgnoreCase(obj1.getId()); // To compare string values
                return (int) Integer.valueOf(obj2.getId()).compareTo(Integer.valueOf(obj1.getId())); // To compare integer values

            }
        });
        Collections.sort(arrayList3, new Comparator<Model>(){
            public int compare(Model obj1, Model obj2) {
                // ## Ascending order
                // return obj1.getId().compareToIgnoreCase(obj2.getId()); // To compare string values
                // return Integer.valueOf(obj1.getId()).compareTo(obj2.getId()); // To compare integer values
                // ## Descending order
                // return obj2.getId().compareToIgnoreCase(obj1.getId()); // To compare string values
                return (int) Integer.valueOf(obj2.getId()).compareTo(Integer.valueOf(obj1.getId())); // To compare integer values

            }
        });
        Collections.sort(arrayList4, new Comparator<Model>(){
            public int compare(Model obj1, Model obj2) {
                // ## Ascending order
                // return obj1.getId().compareToIgnoreCase(obj2.getId()); // To compare string values
                // return Integer.valueOf(obj1.getId()).compareTo(obj2.getId()); // To compare integer values
                // ## Descending order
                // return obj2.getId().compareToIgnoreCase(obj1.getId()); // To compare string values
                return (int) Integer.valueOf(obj2.getId()).compareTo(Integer.valueOf(obj1.getId())); // To compare integer values

            }
        });

        sliderpageAdapter = new SliderpageAdapter(getContext(),arrayList);
        pager.setAdapter(sliderpageAdapter);

        recyclerViewvideo.setItemAnimator(new DefaultItemAnimator());
        recyclerViewvideo.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        homeVideoviewAdapter = new HomeVideoviewAdapter(arrayList,getContext());
        recyclerViewvideo.setAdapter(homeVideoviewAdapter);

        recyclerViewfilm.setItemAnimator(new DefaultItemAnimator());
        recyclerViewfilm.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        viewAdapter2 = new ViewAdapter(arrayList3,getContext());
        recyclerViewfilm.setAdapter(viewAdapter2);

        recyclerViewMusid_video.setItemAnimator(new DefaultItemAnimator());
        recyclerViewMusid_video.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        viewAdapter3 = new ViewAdapter(arrayList4,getContext());
        recyclerViewMusid_video.setAdapter(viewAdapter3);

        recyclerViewaudio.setItemAnimator(new DefaultItemAnimator());
        recyclerViewaudio.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        homeAudioviewAdapter= new HomeAudioviewAdapter(arrayList1,getContext());
        recyclerViewaudio.setAdapter(homeAudioviewAdapter);

        recyclerViewdocument.setItemAnimator(new DefaultItemAnimator());
        recyclerViewdocument.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        documentAdapter= new DocumentAdapter(arrayList2,getContext());
        recyclerViewdocument.setAdapter(documentAdapter);


    }

    @Override
    public void onPause() {
        super.onPause();
        Objects.requireNonNull(getActivity()).overridePendingTransition(R.anim.goup,R.anim.godown);

    }
//for top view slide image class to change image afer some time expand
    class Sliddertime extends TimerTask{

        @Override
        public void run() {
            mactivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(pager.getCurrentItem()<4-1){
                        pager.setCurrentItem(pager.getCurrentItem()+1);
                    }else {
                        pager.setCurrentItem(0);
                    }

                }
            });

        }
    }
//for another fragment attach call acitvity on that time of runuithread
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mactivity = activity;
    }
}
