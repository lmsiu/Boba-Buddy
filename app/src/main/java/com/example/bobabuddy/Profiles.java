package com.example.bobabuddy;

//This is just a layout. Can be changed depending on approach

import com.parse.Parse;
import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Profiles")
public class Profiles  extends ParseObject {
    //Contains:
    //Keys that we use to accsess the places from the JSON in back4app.
    //TODO update the keys with the correct names
    //Username
    public static final String KEY_USER = "username";
    //Profile Picture
    public static final String KEY_PROFILEPIC = "ProfilePic";
    //Places they like that are similar
    public static final String KEY_PLACES = "Places";

    //Few approaches to this, can have a "Profile" object in back4app, or can just pull data"


    public ParseUser getUser(){
        return getParseUser(KEY_USER);
    }


}
