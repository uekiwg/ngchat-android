package com.uekiwg.ngchat.view;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.common.ChangeEventType;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.ObservableSnapshotArray;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.uekiwg.ngchat.R;
import com.uekiwg.ngchat.model.Comment;
import com.uekiwg.ngchat.util.Str;

public abstract class CommentRecyclerAdapter extends FirebaseRecyclerAdapter<Comment, CommentViewHolder> {

    private static final String TAG = "CommentRecyclerAdapter";

    public CommentRecyclerAdapter(Query query) {
        super(new FirebaseRecyclerOptions.Builder<Comment>().setQuery(query, Comment.class).build());
    }

    @Override
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void startListening() {
        super.startListening();
    }

    @Override
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void stopListening() {
        super.stopListening();
    }

    @Override
    public void onChildChanged(@NonNull ChangeEventType type, @NonNull DataSnapshot snapshot, int newIndex, int oldIndex) {
        super.onChildChanged(type, snapshot, newIndex, oldIndex);
    }

    @Override
    public void onDataChanged() {
        super.onDataChanged();
    }

    @Override
    public void onError(@NonNull DatabaseError error) {
        Log.w(TAG, Str.toString("onError", error));
    }

    @NonNull
    @Override
    public ObservableSnapshotArray<Comment> getSnapshots() {
        return super.getSnapshots();
    }

    @NonNull
    @Override
    public Comment getItem(int position) {
        return super.getItem(position);
    }

    @NonNull
    @Override
    public DatabaseReference getRef(int position) {
        return super.getRef(position);
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
    }

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.viewholder_comment, viewGroup,false);
        return new CommentViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(CommentViewHolder viewHolder, int position, final Comment model) {
        Log.d(TAG, "onActivityCreated#FirebaseRecyclerAdapter#onCreateViewHolder Start.");
        final DatabaseReference postRef = getRef(position);

        // Set click listener for the whole post view
        final String postKey = postRef.getKey();
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch PostDetailActivity
//                Intent intent = new Intent(getActivity(), PostDetailActivity.class);
//                intent.putExtra(PostDetailActivity.EXTRA_POST_KEY, postKey);
//                startActivity(intent);
            }
        });

        // Determine if the current user has liked this post and set UI accordingly
//                if (model.stars.containsKey(getUid())) {
//                    viewHolder.starView.setImageResource(R.drawable.ic_toggle_star_24);
//                } else {
//                    viewHolder.starView.setImageResource(R.drawable.ic_toggle_star_outline_24);
//                }

        // Bind Post to ViewHolder, setting OnClickListener for the star button
        viewHolder.bindToPost(model, new View.OnClickListener() {
            @Override
            public void onClick(View starView) {
                Log.d(TAG, "onActivityCreated#FirebaseRecyclerAdapter#onCreateViewHolder viewHolder.bindToPost Start.");
                // Need to write to both places the post is stored
//                        DatabaseReference globalPostRef = mDatabase.child("posts").child(postRef.getKey());
//                        DatabaseReference userPostRef = mDatabase.child("user-posts").child(model.uid).child(postRef.getKey());
//                DatabaseReference globalPostRef = mDatabase.child("comments").child(postRef.getKey());
//                //DatabaseReference userPostRef = mDatabase.child("comments").child(model.uid).child(postRef.getKey());
//
//                // Run two transactions
//                onStarClicked(globalPostRef);
                //onStarClicked(userPostRef);
                Log.d(TAG, "onActivityCreated#FirebaseRecyclerAdapter#onCreateViewHolder viewHolder.bindToPost End.");
            }
        });
    }

}
