package com.example.loginsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username , password, rePassword;
    Button signUp, signIn;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        rePassword = findViewById(R.id.rePassword);
        signUp = findViewById(R.id.btnSignUp);
        signIn = findViewById(R.id.btnSignIn);
        DB = new DBHelper(this);


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = rePassword.getText().toString();

                if(user.equals("") ||pass.equals("")||repass.equals("")){
                    Toast.makeText(MainActivity.this, "Please enter all the fields", Toast.LENGTH_LONG).show();
                }else{
                    if(pass.equals(repass)){
                        Boolean checkUser = DB.checkusername(user);
                        if(checkUser == false){
                            Boolean insert = DB.insertData(user,pass);
                            if(insert == true){
                                Toast.makeText(MainActivity.this, "Registered successfully", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                                startActivity(intent);
                            }else {
                                Toast.makeText(MainActivity.this, "Registration failed", Toast.LENGTH_LONG).show();
                            }
                        }else{
                            Toast.makeText(MainActivity.this, "User already existe!", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(MainActivity.this, "Password not matching", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });
    }

}
