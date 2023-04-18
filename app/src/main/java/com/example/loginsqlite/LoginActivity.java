package com.example.loginsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText username , password;
    Button signIn;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.usernameL);
        password = findViewById(R.id.passwordL);
        signIn = findViewById(R.id.btnSignInL);
        DB = new DBHelper(this);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(user.equals("")||pass.equals("") ){
                    Toast.makeText(LoginActivity.this, "Please enter all fields", Toast.LENGTH_LONG).show();
                }else{
                    Boolean checkuserpass = DB.chekcusernamepassword(user,pass);
                    if(checkuserpass == true){
                        Toast.makeText(LoginActivity.this, "Sign in successfull", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

}
