package com.example.thebloomroom;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList flower_id, flower_name, flower_description, flower_price, flower_image_uri;

    CustomAdapter(Context context,
                  ArrayList flower_id,
                  ArrayList flower_name,
                  ArrayList flower_description,
                  ArrayList flower_price,
                  ArrayList flower_image_uri) {
        this.context = context;
        this.flower_id = flower_id;
        this.flower_name = flower_name;
        this.flower_description = flower_description;
        this.flower_price = flower_price;
        this.flower_image_uri = flower_image_uri;
    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {
        holder.flower_id_txt.setText(String.valueOf(flower_id.get(position)));
        holder.flower_name_txt.setText(String.valueOf(flower_name.get(position)));
        holder.flower_description_txt.setText(String.valueOf(flower_description.get(position)));
        holder.flower_price_txt.setText(String.valueOf(flower_price.get(position)));

        // Load the image using Picasso library
        String imageUri = String.valueOf(flower_image_uri.get(position));
        if (imageUri != null && !imageUri.isEmpty()) {
            Picasso.get().load(Uri.parse(imageUri)).into(holder.flower_image);
        } else {
            // If there is no image URI, you can set a placeholder image or hide the ImageView
            // holder.flower_image.setImageResource(R.drawable.placeholder_image);
            // or
            //holder.flower_image.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return flower_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView flower_id_txt, flower_name_txt, flower_description_txt, flower_price_txt;
        ImageView flower_image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            flower_id_txt = itemView.findViewById(R.id.flower_id_txt);
            flower_name_txt = itemView.findViewById(R.id.flower_name_txt);
            flower_description_txt = itemView.findViewById(R.id.flower_description_txt);
            flower_price_txt = itemView.findViewById(R.id.flower_price_txt);
            flower_image = itemView.findViewById(R.id.flower_image);
        }
    }
}
