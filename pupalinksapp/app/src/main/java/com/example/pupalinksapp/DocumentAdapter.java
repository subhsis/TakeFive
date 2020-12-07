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

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;


import java.util.ArrayList;

public class DocumentAdapter extends RecyclerView.Adapter<DocumentAdapter.Myviewholder> {

    private Context context;
    private ArrayList<DocumentModel> arrayList;
    private RequestOptions options;
    View view;

     DocumentAdapter(ArrayList<DocumentModel> arrayList, Context context){
        this.context=context;
        this.arrayList= arrayList;

        options = new RequestOptions().centerCrop().placeholder(R.drawable.edittextback).error(R.drawable.edittextback);

    }

    @NonNull
    @Override
    public DocumentAdapter.Myviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.videoiteam,viewGroup,false);
        final DocumentAdapter.Myviewholder myviewholder = new DocumentAdapter.Myviewholder(view);

        myviewholder.view_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,DocumentActivity.class);
                intent.putExtra("name",arrayList.get(myviewholder.getAdapterPosition()).getId());
                context.startActivity(intent);
            }
        });
        return myviewholder;

    }

    @Override
    public void onBindViewHolder(@NonNull DocumentAdapter.Myviewholder myviewholder, int i) {
        myviewholder.view_content.setAnimation(AnimationUtils.loadAnimation(context,R.anim.recyclerviewanim));
        Glide.with(context).load(arrayList.get(i).getImage_url()).apply(options).into(myviewholder.imageView);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

     class Myviewholder extends RecyclerView.ViewHolder {
        ImageView imageView;
        RelativeLayout view_content;
         Myviewholder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.video_thumbnail);
            view_content = itemView.findViewById(R.id.view_content);

        }
    }
}
