package com.example.bobabuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

// Nhi was here!
//and Alysia!
//and Laura
public class LoginActivity extends AppCompatActivity {

    //Debugging tag for log statements. Go to debug on the logcat and type in "LoginActivity" to see logs we made
    public static final String TAG = "LoginActivity";

    private Button loginbtn, signupbtn;
    private EditText etusername, etpassword;
    private ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //if user is already logged in, keep them logged in even after exiting the app
        if(ParseUser.getCurrentUser() != null){
            //if there is a user right now, then go straight to the main activity
            goMainActivity();
        }

        //get references to the buttons for each variable
        loginbtn = findViewById(R.id.btnLogin);
        signupbtn = findViewById(R.id.btnSignUp);
        etusername = findViewById(R.id.etUsername);
        etpassword = findViewById(R.id.etPassword);
        logo = findViewById(R.id.logo);
        logo.setImageResource(R.drawable.bobabuddylogo);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get the username and password as a string
                String username = etusername.getText().toString();
                String password = etpassword.getText().toString();
                loginUser(username, password);

            }
        });

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goSignUpActivity();
            }
        });
    }

    private void loginUser(String username, String password) {
        //if credentials are correct, go to main activity, checked using a parse function
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            //login happens in background
            @Override
            public void done(ParseUser user, ParseException e) {
                //if login successs, exception e will be null, else it will have an exception
                if(e != null){
                    //if credentials are wrong
                    Log.e(TAG, "Issue with login");
                    return;
                }

                //if credentials are right:
                goMainActivity();

            }
        });
    }

    //go to the signup activity
    private void goSignUpActivity() {
        Intent i = new Intent(this, SignUpActivity.class);
        startActivity(i);
        finish();
    }

    public void goMainActivity(){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();

    }
}