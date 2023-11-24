package com.example.thebloomroom;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ListView;

public class TulipFlowerPage extends AppCompatActivity {

    String tuliplist[] = {"Lilies", "Tulips", "Alliums"};

    int tulipImages [] = {R.drawable.lilies, R.drawable.tulip, R.drawable.alliums};

    ListView listView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tulip_flower_page);
        listView = (ListView) findViewById(R.id.tulip_flowers_listview);
        CustomBaseAdapter customBaseAdapter = new CustomBaseAdapter(getApplicationContext(),tuliplist,tulipImages);
        listView.setAdapter(customBaseAdapter);
    }
}