package com.example.testapp;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;


import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public  class  ListAdapter extends RecyclerView.Adapter<ListAdapter.HolderView>{

    ArrayList<Upload1> list1 =new ArrayList<>();
    Context context;

    public ListAdapter(ArrayList<Upload1> list1, Context context) {
        this.list1 = list1;
        this.context=context;
    }



    @NonNull
    @Override
    public HolderView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater=LayoutInflater.from(viewGroup.getContext());
        View view=layoutInflater.inflate(R.layout.list,viewGroup,false);
        return new HolderView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderView holderView, int i) {
        // Information information=list1.get(i);
        Upload1 upload1 = list1.get(i);

        holderView.textView.setText(upload1.getTitle());

        //  holderView.imageView.setImageURI();
        //   holderView.textView2.setText(information.getName());

        holderView.textView3.setText(upload1.getUrl());


       //  holderView.imageView.setImageURI(Uri.parse(upload1.getUrl()));

       /* Picasso.with(context)
                .load(upload1.getUrl())
                .fit()
                .centerCrop()
                .into(holderView.imageView);

*/






        Glide.with(context).load(upload1.getUrl()).into(holderView.imageView);
    }

    @Override
    public int getItemCount() {
        return list1.size();
    }

    public  class HolderView extends ViewHolder{

        TextView textView,textView2,textView3;
        ImageView imageView;
        public HolderView(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.imageView);
            textView2=itemView.findViewById(R.id.textView2);
            textView3=itemView.findViewById(R.id.textView3);
            textView=itemView.findViewById(R.id.textView);

        }






    }












}