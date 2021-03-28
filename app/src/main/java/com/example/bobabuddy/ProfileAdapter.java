package com.example.bobabuddy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import java.util.List;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {

    private Context context;
    private List<Profile> profiles;



    public ProfileAdapter(Context context, List<Profile> profiles){
        this.context = context;
        this.profiles = profiles;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itemprofiles, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Profile profile = profiles.get(position);
        holder.bind(profile);

    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvusername, tvbio, tvplaces;
        private ImageView ivprofilepic;
        private Button btnchat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvusername = itemView.findViewById(R.id.tvProfileUsername);
            tvbio = itemView.findViewById(R.id.tvBio);
            tvplaces = itemView.findViewById(R.id.tvPlaces);
            ivprofilepic = itemView.findViewById(R.id.imageView);
            btnchat = itemView.findViewById(R.id.btnChat);
        }

        public void bind(Profile profile) {
            //Bind data to elements

            tvusername.setText(profile.getUser().getUsername());
            tvbio.setText("Bio: " + profile.getBio());
            //tvplaces.setText("Fav Places: " + profile.getPlaces().toString());

            ParseFile image = profile.getImage();

            if(image != null) {
                Glide.with(context).load(profile.getImage().getUrl()).into(ivprofilepic);
            }

        }

        
    }


}
