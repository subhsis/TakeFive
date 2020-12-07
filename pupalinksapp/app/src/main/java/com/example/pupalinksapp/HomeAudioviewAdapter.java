package com.example.pupalinksapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import java.util.ArrayList;

import static com.example.pupalinksapp.Audioplayer.intentService;
import static com.example.pupalinksapp.Audioplayer.player1;

public class HomeAudioviewAdapter extends RecyclerView.Adapter<HomeAudioviewAdapter.Myviewholder>  {
    private Context context;
    private ArrayList<AudioModel> arrayList;
    private RequestOptions options;
    View view;

     HomeAudioviewAdapter(ArrayList<AudioModel> arrayList, Context context){
        this.context=context;
        this.arrayList= arrayList;


        options = new RequestOptions().centerCrop().placeholder(R.drawable.edittextback).error(R.drawable.edittextback);

    }

    @NonNull
    @Override
    public HomeAudioviewAdapter.Myviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.videoiteam,viewGroup,false);
        final HomeAudioviewAdapter.Myviewholder myviewholder = new HomeAudioviewAdapter.Myviewholder(view);
        myviewholder.view_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(context,Audioplayer.class);
//                intent.putExtra("id",arrayList.get(myviewholder.getAdapterPosition()).getId());
//                intent.putExtra("image",arrayList.get(i).getImage_url());
//                Home.relativeLayout.setVisibility(View.VISIBLE);
//                context.startActivity(intent);
                if(player1!=null){
                    context.stopService(intentService);
                    Intent intent = new Intent(context,Audioplayer.class);
                    intent.putExtra("id", arrayList.get(myviewholder.getAdapterPosition()).getId());
                    intent.putExtra("image", arrayList.get(myviewholder.getAdapterPosition()).getImage_url());
                    context.startActivity(intent);
                }else{
                    Intent intent = new Intent(context,Audioplayer.class);
                    intent.putExtra("id", arrayList.get(myviewholder.getAdapterPosition()).getId());
                    intent.putExtra("image", arrayList.get(myviewholder.getAdapterPosition()).getImage_url());
                    context.startActivity(intent);
                }

            }
        });
        return myviewholder;

    }

    @Override
    public void onBindViewHolder(@NonNull HomeAudioviewAdapter.Myviewholder myviewholder, int i) {
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
