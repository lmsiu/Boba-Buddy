package com.example.bobabuddy.fragments;

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

import com.example.bobabuddy.R;
import com.example.bobabuddy.User;
import com.example.bobabuddy.UserAdapter;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    public static final String TAG = "HomeFragment";

    //Holds list that allows for people to scroll and see things
    private RecyclerView rvHome;
    private UserAdapter adapter;
    private List<User> allUsers;

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
        rvHome = view.findViewById(R.id.rvHome);
        allUsers = new ArrayList<>();
        adapter = new UserAdapter(getContext(), allUsers);

        rvHome.setAdapter(adapter);
        rvHome.setLayoutManager(new LinearLayoutManager(getContext()));

        //Populate the recycler view
        //
        queryUsers();
    }

    private void queryUsers(){
        ParseQuery<User> query = ParseQuery.getQuery(User.class);
        query.include(User.KEY_USERID);
        query.findInBackground(new FindCallback<User>() {
            @Override
            public void done(List<User> users, ParseException e) {
                if (e != null){
                    Log.e(TAG, "Issue getting posts", e);
                    return;
                }
                //seems to be problem here, unsure why user isn't valid
                for (User user : users){
                    Log.i(TAG, "Quried");
                }

                allUsers.addAll(users);
                adapter.notifyDataSetChanged();
            }
        });

    }
}