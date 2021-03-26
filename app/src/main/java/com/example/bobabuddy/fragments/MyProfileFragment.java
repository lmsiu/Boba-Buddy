package com.example.bobabuddy.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.bobabuddy.LoginActivity;
import com.example.bobabuddy.R;
import com.parse.ParseUser;


public class MyProfileFragment extends Fragment {

    Button logoutbtn;

    public MyProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_myprofile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        logoutbtn = view.findViewById(R.id.btnLogout);

        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Logs out user TODO comment out when back4app is setup
                //ParseUser.logOut();
                goLoginActivity();




            }
        });

    }

    public void goLoginActivity(){
        Intent i = new Intent(getActivity(), LoginActivity.class);
        startActivity(i);


    }


}