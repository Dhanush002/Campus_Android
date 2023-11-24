package com.example.thebloomroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewFlower extends AppCompatActivity {

    MyDatabaseHelper myDB;
    ArrayList<String> flower_id, flower_name, flower_description, flower_price, flower_image_uri;
    CustomAdapter customAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_flower);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        myDB = new MyDatabaseHelper(this);
        flower_id = new ArrayList<>();
        flower_name = new ArrayList<>();
        flower_description = new ArrayList<>();
        flower_price = new ArrayList<>();
        flower_image_uri = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(
                ViewFlower.this,
                flower_id,
                flower_name,
                flower_description,
                flower_price,
                flower_image_uri
        );
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ViewFlower.this));

    }

    void storeDataInArrays() {
        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                flower_id.add(cursor.getString(0));
                flower_name.add(cursor.getString(1));
                flower_description.add(cursor.getString(2));
                flower_price.add(cursor.getString(3));
                flower_image_uri.add(cursor.getString(4)); // Add the image URI
            }
        }
    }
}
