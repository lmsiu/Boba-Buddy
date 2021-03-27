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

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private Context context;
    private List<User> users;

    public UserAdapter(Context context, List<User> users){
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itemprofiles, parent, false);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = users.get(position);
        holder.bind(user);

    }



    @Override
    public int getItemCount() {
        return users.size();
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

        public void bind(User user) {
            //Bind data into view elements
            //try getUser().getUsername();
            tvusername.setText(user.getUsername());
            tvbio.setText(user.getBio());
            tvplaces.setText(user.getPlaces().toString());

            ParseFile image = user.getImage();

            if(image != null) {
                Glide.with(context).load(user.getImage().getUrl()).into(ivprofilepic);
            }
        }
    }
}
