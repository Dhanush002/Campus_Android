package com.example.thebloomroom;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class admin_login extends AppCompatActivity {

    Button button;
    EditText usernameEditText, passwordEditText;
    String adminUsername = "admin"; // Replace with your actual admin username
    String adminPassword = "password"; // Replace with your actual admin password

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        button = findViewById(R.id.admin_login_btn);
        usernameEditText = findViewById(R.id.adminusername);
        passwordEditText = findViewById(R.id.adminpassword);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String enteredUsername = usernameEditText.getText().toString();
                String enteredPassword = passwordEditText.getText().toString();

                if (enteredUsername.equals(adminUsername) && enteredPassword.equals(adminPassword)) {
                    // Admin login successful
                    Intent intent = new Intent(admin_login.this, Admin_options.class);
                    startActivity(intent);
                } else {
                    // Admin login failed
                    Toast.makeText(admin_login.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
