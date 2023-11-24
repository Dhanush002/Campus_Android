package com.example.thebloomroom;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class user_homepage extends AppCompatActivity {

    String flowercatlist[] = {"Rose", "Tulip", "Irish", "Lily", "Sunflower"};
    int flowercatImages[] = {R.drawable.rose, R.drawable.tulip, R.drawable.irish, R.drawable.lily, R.drawable.sunflower};

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_homepage);
        listView = (ListView) findViewById(R.id.customlistview);
        CustomBaseAdapter customBaseAdapter = new CustomBaseAdapter(getApplicationContext(), flowercatlist, flowercatImages);
        listView.setAdapter(customBaseAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("CUSTOM_LIST_VIEW", "Item is clicked @ position :: " + position);

                String selectedFlowerType = flowercatlist[position];

                switch (selectedFlowerType) {
                    case "Rose":
                        openRoseActivity();
                        break;
                    case "Tulip":
                        openTulipActivity();
                        break;
                    // Add cases for other flower types
                    // ...

                    default:
                        // Handle the default case, or do nothing
                }
            }
        });
    }

    // Define methods to open specific activities
    private void openRoseActivity() {
        Intent intent = new Intent(user_homepage.this, RoseFlowerPage.class);
        startActivity(intent);
    }

     private void openTulipActivity() {
       Intent intent = new Intent(user_homepage.this, TulipFlowerPage.class);
       startActivity(intent);
    }

    // Define methods for other flower types
    // ...
}
