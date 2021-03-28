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

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MessageViewHolder> {

    private List<Message> mMessages;
    private Context mContext;
    private String mUserId;

    public ChatAdapter(Context context, String userId, List<Message> messages) {
        this.mMessages = messages;
        this.mUserId = userId;
        this.mContext = context;
    }

    @Override
    public int getItemCount() {
        return mMessages.size();
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_chatbox, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position) {
        Message message = mMessages.get(position);
       // holder.bind(message);
    }

    class MessageViewHolder extends RecyclerView.ViewHolder {

        private TextView etMessage, tvBody1, tvName, tvBody2 ;
        private ImageView ivProfileOther, ivProfileMe;
        private Button btSend;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);

            etMessage = itemView.findViewById(R.id.etMessage);
            tvBody1 = itemView.findViewById(R.id.tvBody1);
            tvBody2 = itemView.findViewById(R.id.tvBody2);
            tvName = itemView.findViewById(R.id.tvName);
            ivProfileOther = itemView.findViewById(R.id.ivProfileOther);
            ivProfileMe = itemView.findViewById(R.id.ivProfileMe);
            btSend = itemView.findViewById(R.id.btSend);
        }

        public void bind(Message message, Profile profile) {
            //Bind data to elements

            tvBody1.setText(message.getBody());
            tvBody2.setText(message.getBody());
            tvName.setText(message.getUserIdKey());

            // Need to bind image profile to the corresponding person
            ParseFile image = profile.getImage();

            if (image != null) {
                Glide.with(mContext).load(profile.getImage().getUrl()).into(ivProfileMe);
            }
        }
    }
}
