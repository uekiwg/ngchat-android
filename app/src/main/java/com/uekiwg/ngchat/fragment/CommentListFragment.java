package com.uekiwg.ngchat.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Query;
import com.google.firebase.database.Transaction;
import com.uekiwg.ngchat.R;
import com.uekiwg.ngchat.model.Comment;
import com.uekiwg.ngchat.view.CommentRecyclerAdapter;
import com.uekiwg.ngchat.view.CommentViewHolder;


public abstract class CommentListFragment extends BaseFragment {

    private static final String TAG = "CommentListFragment";

    private DatabaseReference mDatabase;
    private CommentRecyclerAdapter mAdapter;
    private RecyclerView mRecycler;
    private LinearLayoutManager mManager;
    public CommentListFragment() {
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_comment_list, container, false);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mRecycler = rootView.findViewById(R.id.commentList);
        mRecycler.setHasFixedSize(true);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Set up Layout Manager, reverse layout
        mManager = new LinearLayoutManager(getActivity());
        mManager.setReverseLayout(true);
        mManager.setStackFromEnd(true);
        mRecycler.setLayoutManager(mManager);

        // Set up FirebaseRecyclerAdapter with the Query
        Query query = getQuery(mDatabase);

//        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<Comment>()
////                .setQuery(postsQuery, Post.class)
//                .setQuery(postsQuery, Comment.class)
//                .build();

        mAdapter = new CommentRecyclerAdapter(query) {

            @Override
            public CommentViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
                LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
                CommentViewHolder postViewHolder = new CommentViewHolder(inflater.inflate(R.layout.viewholder_comment, viewGroup, false));
                return postViewHolder;
            }

            @Override
            protected void onBindViewHolder(CommentViewHolder viewHolder, int position, final Comment model) {
                Log.d(TAG, "onActivityCreated#FirebaseRecyclerAdapter#onCreateViewHolder Start.");
                final DatabaseReference postRef = getRef(position);

                // Set click listener for the whole post view
                final String postKey = postRef.getKey();
                viewHolder.itemView.setOnClickListener((View v) -> {
                        // Launch PostDetailActivity
//                        Intent intent = new Intent(getActivity(), PostDetailActivity.class);
//                        intent.putExtra(PostDetailActivity.EXTRA_POST_KEY, postKey);
//                        startActivity(intent);
                });

                // Determine if the current user has liked this post and set UI accordingly
//                if (model.stars.containsKey(getUid())) {
//                    viewHolder.starView.setImageResource(R.drawable.ic_toggle_star_24);
//                } else {
//                    viewHolder.starView.setImageResource(R.drawable.ic_toggle_star_outline_24);
//                }

                // Bind Post to ViewHolder, setting OnClickListener for the star button
                viewHolder.bindToPost(model, (View starView) -> {
                    Log.d(TAG, "onActivityCreated#FirebaseRecyclerAdapter#onCreateViewHolder viewHolder.bindToPost Start.");
                    // Need to write to both places the post is stored
//                        DatabaseReference globalPostRef = mDatabase.child("posts").child(postRef.getKey());
//                        DatabaseReference userPostRef = mDatabase.child("user-posts").child(model.uid).child(postRef.getKey());
                    DatabaseReference globalPostRef = mDatabase.child("comments").child(postRef.getKey());
                    //DatabaseReference userPostRef = mDatabase.child("comments").child(model.uid).child(postRef.getKey());

                    // Run two transactions
                    onStarClicked(globalPostRef);
                    //onStarClicked(userPostRef);
                    Log.d(TAG, "onActivityCreated#FirebaseRecyclerAdapter#onCreateViewHolder viewHolder.bindToPost End.");
                });
            }
        };
        mRecycler.setAdapter(mAdapter);
        Log.d(TAG, "onActivityCreated#FirebaseRecyclerAdapter#onCreateViewHolder End.");
    }

    private void onStarClicked(DatabaseReference postRef) {
        postRef.runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {
//                Post p = mutableData.getValue(Post.class);
//                if (p == null) {
//                    return Transaction.success(mutableData);
//                }
//
//                if (p.stars.containsKey(getUid())) {
//                    // Unstar the post and remove self from stars
//                    p.starCount = p.starCount - 1;
//                    p.stars.remove(getUid());
//                } else {
//                    // Star the post and add self to stars
//                    p.starCount = p.starCount + 1;
//                    p.stars.put(getUid(), true);
//                }
//
//                // Set value and report transaction success
//                mutableData.setValue(p);
                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(DatabaseError databaseError, boolean b,
                                   DataSnapshot dataSnapshot) {
                // Transaction completed
                Log.d(TAG, "postTransaction:onComplete:" + databaseError);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mAdapter != null) {
            mAdapter.startListening();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAdapter != null) {
            mAdapter.stopListening();
        }
    }

    public String getUid() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    public abstract String getName();

    public abstract Query getQuery(DatabaseReference databaseReference);

}
