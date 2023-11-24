package com.example.thebloomroom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class FlowerAdapter extends ArrayAdapter<String> {

    public FlowerAdapter(Context context, List<String> flowers) {
        super(context, 0, flowers);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String flower = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        TextView textView = convertView.findViewById(android.R.id.text1);
        textView.setText(flower);

        return convertView;
    }
}
