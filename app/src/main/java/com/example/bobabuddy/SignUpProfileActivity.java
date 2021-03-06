package com.example.bobabuddy;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import org.json.JSONArray;

import java.io.File;
import java.util.Arrays;

public class SignUpProfileActivity extends AppCompatActivity {

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 83;
    private static final String TAG = "SignUpProfileActivity";
    private Button btnaddProfilePic, btnfinishSignUp, btnAddPlace;
    private EditText etBio, etPlaces;
    private ImageView ivProfilePicture;
    private File photoFile;
    private String photoFileName = "photo.jpg";
    private JSONArray places;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_profile);

        btnAddPlace = findViewById(R.id.btnAdd);
        btnaddProfilePic= findViewById(R.id.btnAddProfilePic);
        btnfinishSignUp = findViewById(R.id.btnFinishSignUp);
        etBio = findViewById(R.id.etBio);
        etPlaces = findViewById(R.id.etAddPlace);
        ivProfilePicture = findViewById(R.id.ivAddProfilePic);


        btnfinishSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bio = etBio.getText().toString();
                String placesString = etPlaces.getText().toString();
                String[] places = placesString.split(",");

                if (bio.isEmpty()) {
                    Toast.makeText(SignUpProfileActivity.this, "Bio can't be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                ParseUser currentUser = ParseUser.getCurrentUser();
                saveProfile(bio, currentUser, photoFile, placesString);
                goMainActivity();
            }
        });

        btnaddProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                //startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });

        btnAddPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String place = etPlaces.getText().toString();

            }
        });
    }

    private void launchCamera() {
        // create Intent to take a picture and return control to the calling application
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Create a File reference for future access
        photoFile = getPhotoFileUri(photoFileName);

        // wrap File object into a content provider
        // required for API >= 24
        // See https://guides.codepath.com/android/Sharing-Content-with-Intents#sharing-files-with-api-24-or-higher
        Uri fileProvider = FileProvider.getUriForFile(SignUpProfileActivity.this, "com.codepath.fileprovider", photoFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider);

        // If you call startActivityForResult() using an intent that no app can handle, your app will crash.
        // So as long as the result is not null, it's safe to use the intent.
        if (intent.resolveActivity(getPackageManager()) != null) {
            // Start the image capture intent to take photo
            startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // by this point we have the camera photo on disk
                Bitmap takenImage = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
                // RESIZE BITMAP, see section below
                // Load the taken image into a preview
                ivProfilePicture.setImageBitmap(takenImage);
            } else { // Result was a failure
                Toast.makeText(this, "Picture wasn't taken!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private File getPhotoFileUri(String photoFileName) {
        // Get safe storage directory for photos
        // Use `getExternalFilesDir` on Context to access package-specific directories.
        // This way, we don't need to request external read/write runtime permissions.
        File mediaStorageDir = new File(SignUpProfileActivity.this.getExternalFilesDir(Environment.DIRECTORY_PICTURES), TAG);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()){
            Log.d(TAG, "failed to create directory");
        }

        // Return the file target for the photo based on filename
        return new File(mediaStorageDir.getPath() + File.separator + photoFileName);
    }

    private void saveProfile(String bio, ParseUser currentUser, File photoFile, String places) {
        Profile profile = new Profile();
        profile.setBio(bio);
        profile.setUser(currentUser);
        profile.addAllUnique("Places", Arrays.asList(places.split(",")));
        profile.setImage(new ParseFile(photoFile));
        profile.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null){
                    Log.e(TAG, "Error making profile");
                    Toast.makeText(SignUpProfileActivity.this, "Error making profile", Toast.LENGTH_SHORT).show();
                }

                Log.i(TAG, "Profile Successfully created");
                etBio.setText("");
                ivProfilePicture.setImageResource(0);

            }
        });
    }

    private void goMainActivity(){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();

    }
}