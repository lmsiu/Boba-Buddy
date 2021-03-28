package com.example.bobabuddy;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class ChatActivity extends AppCompatActivity {
    static final String TAG = ChatActivity.class.getSimpleName();
    static final String USER_ID_KEY =  "userId";
    static final String BODY_KEY = "body";


    EditText etMessage;
    Button btSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatbox);
        // User login
        if (ParseUser.getCurrentUser() != null) { // start with existing user
            startWithCurrentUser();
        }
    }

    // Get the userId from the cached currentUser object
    void startWithCurrentUser() {
        setupMessagePosting();
    }

    // Setup button event handler which posts the entered message to Parse
    void setupMessagePosting() {
        // Find the text field and button
        etMessage = (EditText) findViewById(R.id.etMessage);
        btSend = (Button) findViewById(R.id.btSend);

        // When send button is clicked, create message object on Parse
        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = etMessage.getText().toString();
                Message message = new Message();
                message.setBody(data);
                message.setUserId(ParseUser.getCurrentUser().getObjectId());

                message.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e == null) {
                            Toast.makeText(ChatActivity.this, "Successfully created message on Parse",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Log.e(TAG, "Failed to save message", e);
                        }
                    }
                });
                etMessage.setText(null);
            }
        });
    }


}
