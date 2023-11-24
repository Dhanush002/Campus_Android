package com.example.thebloomroom;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.IOException;

public class AddFlower extends AppCompatActivity {

    EditText name_input, description_input, price_input;
    Button selectImageBtn, addFlowerBtn;
    ImageView flowerImage;

    private static final int REQUEST_WRITE_STORAGE = 112;
    private static final int PICK_IMAGE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_flower);

        name_input = findViewById(R.id.name_input);
        description_input = findViewById(R.id.description_input);
        price_input = findViewById(R.id.price_input);
        selectImageBtn = findViewById(R.id.select_image_button);
        addFlowerBtn = findViewById(R.id.add_flower_button);
        flowerImage = findViewById(R.id.flower_image);

        selectImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ContextCompat.checkSelfPermission(AddFlower.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(AddFlower.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            REQUEST_WRITE_STORAGE);
                } else {

                    pickImageFromGallery();
                }
            }
        });

        addFlowerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveFlowerData();
            }
        });
    }

    private void pickImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();


            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                flowerImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveFlowerData() {
        MyDatabaseHelper myDB = new MyDatabaseHelper(AddFlower.this);

        Uri imageUri = getImageUriFromImageView(flowerImage);

        // Checking if the image if selected or not......................................
        if (imageUri != null) {
            myDB.addFlower(
                    name_input.getText().toString().trim(),
                    description_input.getText().toString().trim(),
                    Integer.valueOf(price_input.getText().toString().trim()),
                    imageUri.toString());

            name_input.setText("");
            description_input.setText("");
            price_input.setText("");
            flowerImage.setImageResource(R.drawable.logonobg);
        } else {
            Toast.makeText(AddFlower.this, "Please select an image", Toast.LENGTH_SHORT).show();
        }
    }

    private Uri getImageUriFromImageView(ImageView imageView) {
        Bitmap bitmap = null;
        try {
            imageView.setDrawingCacheEnabled(true);
            imageView.buildDrawingCache();
            bitmap = Bitmap.createBitmap(imageView.getDrawingCache());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (bitmap != null) {
            String path = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "Title", null);
            return Uri.parse(path);
        } else {
            return null;
        }
    }

    //We are writing the Permissopn Code here below.............................................
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_WRITE_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, pick an image
                pickImageFromGallery();
            } else {
                Toast.makeText(this, "Write permission is required to save images", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
