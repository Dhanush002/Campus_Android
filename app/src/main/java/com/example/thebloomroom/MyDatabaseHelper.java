// MyDatabaseHelper.java

package com.example.thebloomroom;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Flowershop.db";
    private static final int DATABASE_VERSION = 1;

    // Flower table
    private static final String TABLE_NAME = "flower_list";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "flower_name";
    private static final String COLUMN_DESCRIPTION = "flower_description";
    private static final String COLUMN_PRICE = "flower_price";
    private static final String COLUMN_IMAGE_URI = "flower_image_uri";

    // User table
    private static final String TABLE_NAME_USERS = "user_list";
    private static final String COLUMN_USER_ID = "_id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_PHONE_NUMBER = "phone_number";
    private static final String COLUMN_EMAIL_ADDRESS = "email_address";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the flower_list table
        String flowerTableQuery =
                "CREATE TABLE " + TABLE_NAME +
                        "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAME + " TEXT, " +
                        COLUMN_DESCRIPTION + " TEXT, " +
                        COLUMN_PRICE + " INTEGER, " +
                        COLUMN_IMAGE_URI + " TEXT);";
        db.execSQL(flowerTableQuery);

        // Create the user_list table
        String userTableQuery =
                "CREATE TABLE " + TABLE_NAME_USERS +
                        "(" + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_USERNAME + " TEXT, " +
                        COLUMN_EMAIL_ADDRESS + " TEXT, " +
                        COLUMN_PHONE_NUMBER + " TEXT, " +
                        COLUMN_PASSWORD + " TEXT);";
        db.execSQL(userTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_USERS);
        onCreate(db);
    }

    // Method to add a flower
    void addFlower(String name, String description, int price, String imageUri) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_DESCRIPTION, description);
        cv.put(COLUMN_PRICE, price);
        cv.put(COLUMN_IMAGE_URI, imageUri); // Save the image URI

        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed to add flower", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Flower added successfully", Toast.LENGTH_SHORT).show();
        }
    }

    // Method to add a user
    void addUser(String username, String password, String phoneNumber, String emailAddress) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_USERNAME, username);
        cv.put(COLUMN_PASSWORD, password); // Corrected line
        cv.put(COLUMN_PHONE_NUMBER, phoneNumber);
        cv.put(COLUMN_EMAIL_ADDRESS, emailAddress);

        long result = db.insert(TABLE_NAME_USERS, null, cv);
        if (result == -1) {
            Toast.makeText(context, "User registration failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "User registered successfully", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery(query, null);
    }

    Cursor readAllUserData() {
        String query = "SELECT * FROM " + TABLE_NAME_USERS;
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery(query, null);
    }

    // Validate user credentials
    public boolean validateUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_USERNAME + " = ? AND " + COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query(TABLE_NAME_USERS, null, selection, selectionArgs, null, null, null);
        boolean isValid = cursor.moveToFirst();
        cursor.close();
        return isValid;
    }

}
