package com.example.pupalinksapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import java.util.ArrayList;



public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.MyviewHolder> {
    private Context context;
    private ArrayList<Model> arrayList;
    private RequestOptions options;
    View view;
    static String Video_id;

     ViewAdapter(ArrayList<Model> arrayList, Context context){
        this.context=context;
        this.arrayList= arrayList;


        options = new RequestOptions().centerCrop().placeholder(R.drawable.edittextback).error(R.drawable.edittextback);

    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
         view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.videoiteam,viewGroup,false);
         final MyviewHolder myviewHolder = new MyviewHolder(view);

        //on item click recyclerview play video on adapter position
        myviewHolder.view_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Video_id = arrayList.get(myviewHolder.getAdapterPosition()).getId();
                Intent intent = new Intent(context,Videoplay.class);
                intent.putExtra("id",arrayList.get(myviewHolder.getAdapterPosition()).getId());
                intent.putExtra("adId",arrayList.get(myviewHolder.getAdapterPosition()).getAdId());
                context.startActivity(intent);

            }
        });

        return myviewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder myviewHolder, int i) {
           myviewHolder.view_content.setAnimation(AnimationUtils.loadAnimation(context,R.anim.recyclerviewanim));
            Glide.with(context).load(arrayList.get(i).getImage_url()).apply(options).into(myviewHolder.imageView);
            myviewHolder.title.setText(arrayList.get(i).getTitle());

    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

     class MyviewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        RelativeLayout view_content;
        TextView title;

         MyviewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.video_thumbnail);
            view_content = itemView.findViewById(R.id.view_content);
            title = itemView.findViewById(R.id.title_text2);


        }

    }
}
