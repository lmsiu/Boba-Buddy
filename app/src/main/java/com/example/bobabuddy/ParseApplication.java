package com.example.bobabuddy;

import android.app.Application;

import com.parse.Parse;

public class ParseApplication extends Application {
//links back4app database with the app

    @Override
    public void onCreate() {
        super.onCreate();
        // set applicationId, and server server based on the values in the back4app settings.
        // any network interceptors must be added with the Configuration Builder given this syntax
        Parse.initialize(new Parse.Configuration.Builder(this)
                //TODO myappid, yourclient key, and server url need to be filled in from back4app
                .applicationId("WPeYGcjX7tDXeonMNyXO0nT9p8Yz1PthS6XrpJij") // should correspond to Application Id env variable
                .clientKey("2d6gXwT8Z18CGoQtVM9dcvyQg7ENAZEgUPFnTzLc")  // should correspond to Client key env variable
                //make sure this server url has https NOT http
                        .server("https://parseapi.back4app.com").build());
    }
}
