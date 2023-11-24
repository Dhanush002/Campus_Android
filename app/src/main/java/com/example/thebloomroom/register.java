package com.example.thebloomroom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class register extends AppCompatActivity {
    EditText nameEditText, emailEditText, phoneEditText, passwordEditText;
    Button registerButton;
    MyDatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameEditText = findViewById(R.id.register_name);
        emailEditText = findViewById(R.id.register_email);
        phoneEditText = findViewById(R.id.register_phone);
        passwordEditText = findViewById(R.id.register_password);
        registerButton = findViewById(R.id.register_btn);
        myDB = new MyDatabaseHelper(this);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = nameEditText.getText().toString().trim();
                String email = emailEditText.getText().toString().trim();
                String phone = phoneEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (!username.isEmpty() && !email.isEmpty() && !phone.isEmpty() && !password.isEmpty()) {
                    // Insert user data into the database
                    myDB.addUser(username, password, phone, email);

                    Toast.makeText(register.this, "Registration successful", Toast.LENGTH_SHORT).show();
                    finish(); // Close the registration activity
                } else {
                    Toast.makeText(register.this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
