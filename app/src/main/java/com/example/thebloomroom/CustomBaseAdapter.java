package com.example.thebloomroom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomBaseAdapter extends BaseAdapter {

    Context context;
    String listflower[];
    int listimages[];
    LayoutInflater inflater;


    public CustomBaseAdapter(Context ctx, String [] flowerlist, int [] flowerimages) {
        this.context = ctx;
        this.listflower = flowerlist;
        this.listimages = flowerimages;
        inflater = LayoutInflater.from(ctx);

    }

    @Override
    public int getCount() {
        return listflower.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint("ResourceType")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.activity_customlistview, null);
        TextView customtextView = convertView.findViewById(R.id.customtextView);
        ImageView flowerimages = convertView.findViewById(R.id.listimage);
        customtextView.setText(listflower[position]);
        flowerimages.setImageResource(listimages[position]);
        return convertView;

    }
}
