package com.example.bobabuddy;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import org.json.JSONArray;

import java.lang.reflect.Array;

@ParseClassName("User")
public class User extends ParseObject {

    public static final String KEY_USERNAME = "username";
    public static final String KEY_USERID = "objectId";
    public static final String KEY_PROFILEPIC = "ProfilePic";
    public static final String KEY_PLACES = "Places";
    public static final String KEY_BIO = "Bio";

    public ParseUser getUser(){
        return getParseUser(KEY_USERID);
    }

    public void setUser(ParseUser user){
        put(KEY_USERID, user);
    }

    public String getUsername(){
        return getString(KEY_USERNAME);
    }

    public void setUsername(String username){
        put(KEY_USERNAME, username);
    }

    public ParseFile getImage(){
        return getParseFile(KEY_PROFILEPIC);
    }

    public void setImage(ParseFile imageURL){
        put(KEY_PROFILEPIC, imageURL);
    }

    public JSONArray getPlaces(){
        return getJSONArray(KEY_PLACES);
    }

    public void setPlaces(Array places){
        put(KEY_PLACES, places);
    }

    public String getBio(){
        return getString(KEY_BIO);
    }

    public void setBio(String bio){
        put(KEY_BIO, bio);
    }

}
