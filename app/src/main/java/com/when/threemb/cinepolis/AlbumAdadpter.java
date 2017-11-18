package com.when.threemb.cinepolis;

/*
 * Created by Rohit on 06-11-2017.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class AlbumAdadpter extends RecyclerView.Adapter<AlbumAdadpter.MyViewHolder>{
    private Context mContext;
    private List<Album> albumList;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView title;
        public ImageView thumbnail;
        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
        }
    }

    public AlbumAdadpter(Context mContext, List<Album> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Album album = albumList.get(position);
        holder.title.setText(album.getName());
        Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);
        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext,Description.class);
                //TODO: add the put extra and call for a get request
                mContext.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return albumList.size() ;
    }


}
