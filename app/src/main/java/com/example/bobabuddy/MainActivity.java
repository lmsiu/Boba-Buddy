package com.example.bobabuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.bobabuddy.fragments.HomeFragment;
import com.example.bobabuddy.fragments.MyProfileFragment;
import com.example.bobabuddy.fragments.YelpFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    final FragmentManager fragmentManager = getSupportFragmentManager();

    private BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        //switches between screens (fragments) when a button on the bottom navigation is clicked
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.myprofileitem:
                        fragment = new MyProfileFragment();
                        break;
                    case R.id.miadd:
                        fragment = new YelpFragment();
                        break;
                    case R.id.homeitem:
                    default:
                        fragment = new HomeFragment();
                        break;
                }

                fragmentManager.beginTransaction().replace(R.id.flCoontainer, fragment).commit();
                return true;
            }
        });


    }



}