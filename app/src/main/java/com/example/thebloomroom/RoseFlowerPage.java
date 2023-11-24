package com.example.thebloomroom;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ListView;

public class RoseFlowerPage extends AppCompatActivity {

    String roselist[] = {"Rose1", "Rose2", "Rose3"};

    int roseImages [] = {R.drawable.rose1, R.drawable.rose2, R.drawable.rose3};

    ListView listView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rose_flower_page);
        listView = (ListView) findViewById(R.id.rose_flowers_listview);
        CustomBaseAdapter customBaseAdapter = new CustomBaseAdapter(getApplicationContext(),roselist,roseImages);
        listView.setAdapter(customBaseAdapter);
    }
}