package com.example.bobabuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends AppCompatActivity {

    //for debugging purposes
    public static final String TAG = "SignUpActivity";

    private Button signupbtn;
    private EditText etusername, etpassword;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signupbtn = findViewById(R.id.btnNewSignup);
        etpassword = findViewById(R.id.etNewPassword);
        etusername = findViewById(R.id.etNewUsername);

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //make a new user
                ParseUser user = new ParseUser();
                //set up the username and password
                user.setUsername(etusername.getText().toString());
                user.setPassword(etpassword.getText().toString());

                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                      if(e == null){
                          //if error is null, we have a succsessful signup, go to the main activity
                          goMainActivity();
                      } else{
                          Log.e(TAG, "Issue with login");
                      }

                    }
                });

            }
        });
    }

    //Move to main activity screen
    private void goMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}