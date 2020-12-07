package com.example.pupalinksapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import java.util.ArrayList;

import static com.example.pupalinksapp.Audioplayer.intentService;
import static com.example.pupalinksapp.Audioplayer.player1;


public class AudioAdapter extends RecyclerView.Adapter<AudioAdapter.Myviewholder> {
    private Context context;
    private ArrayList<AudioModel> arrayList;
    private RequestOptions options;
    View view;

    AudioAdapter(ArrayList<AudioModel> arrayList, Context context){
        this.context=context;
        this.arrayList= arrayList;
        options = new RequestOptions().centerCrop().placeholder(R.drawable.edittextback).error(R.drawable.edittextback);

    }

    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.audio_item,viewGroup,false);
            final Myviewholder myviewholder = new Myviewholder(view);
        myviewholder.view_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //making the player create service every time adapter view click
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
    public void onBindViewHolder(@NonNull AudioAdapter.Myviewholder myviewholder, int i) {
            Glide.with(context).load(arrayList.get(i).getImage_url()).apply(options).into(myviewholder.imageView);
            myviewholder.title.setText(arrayList.get(i).getTitle());
            myviewholder.format.setText(arrayList.get(i).getFormat());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class Myviewholder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title,format;
        LinearLayout view_content;
        Myviewholder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.Audio_thumbnail);
            view_content = itemView.findViewById(R.id.audio_content);
            title = itemView.findViewById(R.id.text_title);
            format = itemView.findViewById(R.id.text_catagory);

        }
    }
}
