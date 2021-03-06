package com.example.bobabuddy.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.bobabuddy.ChatActivity;
import com.example.bobabuddy.Profile;
import com.example.bobabuddy.ProfileAdapter;
import com.example.bobabuddy.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    public static final String TAG = "HomeFragment";

    //Holds list that allows for people to scroll and see things
    private RecyclerView rvHome;
    private ProfileAdapter adapter;
    private List<Profile> allProfiles;
    private Button btnChat;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.i(TAG, "In Home fragment");
        rvHome = view.findViewById(R.id.rvHome);
        allProfiles = new ArrayList<>();
        adapter = new ProfileAdapter(getContext(), allProfiles);
        btnChat = view.findViewById(R.id.btnchat2);

        rvHome.setAdapter(adapter);
        rvHome.setLayoutManager(new LinearLayoutManager(getContext()));

        Log.i(TAG, "About to query");
        //Populate the recycler view
        queryProfiles();
        Log.i(TAG, "Queried?");

        btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ChatActivity.class);
                startActivity(i);
            }
        });
    }


private void queryProfiles() {
    ParseQuery<Profile> query = ParseQuery.getQuery(Profile.class);
    query.addDescendingOrder(Profile.KEY_CREATEDAT);
    query.include(Profile.KEY_USER);
    query.findInBackground(new FindCallback<Profile>() {
        @Override
        public void done(List<Profile> profiles, ParseException e) {
            if (e != null) {
                Log.e(TAG, "Error retrieving data");
                return;
            }

            for (Profile profile : profiles) {
                Log.i(TAG, "Bio " + profile.getBio() + "User: " + profile.getUser().getUsername() );
            }

            allProfiles.addAll(profiles);
            adapter.notifyDataSetChanged();
        }


    });

}
}