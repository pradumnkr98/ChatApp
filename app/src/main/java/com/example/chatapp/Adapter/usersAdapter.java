package com.example.chatapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatapp.MessageActivity;
import com.example.chatapp.ModelClass.Chat;
import com.example.chatapp.ModelClass.users;
import com.example.chatapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class usersAdapter extends RecyclerView.Adapter<usersAdapter.ViewHolder> {

    String theLastMessage;
    StorageReference storageReference;
    private Context mContext;
    private List<users> mUsers;
    private boolean isOnline;

    public usersAdapter(Context mContext, List<users> mUsers, boolean isOnline) {
        this.mContext = mContext;
        this.mUsers = mUsers;
        this.isOnline = isOnline;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.user_item, parent, false);
        return new usersAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final users users = mUsers.get(position);
        holder.userName.setText(users.getUsername());

        if (isOnline) {
            lastmessage(users.getId(), holder.recent_message);
        } else {
            holder.recent_message.setVisibility(View.GONE);
        }

        if (isOnline) {
            if (users.getStatus().equals("online")) {
                holder.statusIcon.setVisibility(View.VISIBLE);
            } else {
                holder.statusIcon.setVisibility(View.GONE);

            }
        } else {
            holder.statusIcon.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MessageActivity.class);
                intent.putExtra("UserID", users.getId());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        return mUsers.size();
    }

    public void lastmessage(final String userID, final TextView last_msg) {
        theLastMessage = "default";
        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Chats");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Chat chat = snapshot.getValue(Chat.class);
                    if (firebaseUser != null) {
                        if (chat.getReceiver().equals(firebaseUser.getUid()) && chat.getSender().equals(userID) ||
                                chat.getReceiver().equals(userID) && chat.getSender().equals(firebaseUser.getUid())) {
                            theLastMessage = chat.getMessage();
                        }
                    }
                }
                switch (theLastMessage) {
                    case "default":
                        last_msg.setText("No Message");
                        break;
                    default:
                        last_msg.setText(theLastMessage);
                        break;

                }
                theLastMessage = "default";
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView userName;
        public ImageView profileImage;
        public CircleImageView statusIcon;
        public TextView recent_message;

        public ViewHolder(View itemView) {
            super(itemView);

            userName = itemView.findViewById(R.id.UserName);
            profileImage = itemView.findViewById(R.id.user_image);
            statusIcon = itemView.findViewById(R.id.status_icon);
            recent_message = itemView.findViewById(R.id.recent_message);
        }
    }
}
