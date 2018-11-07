package com.uekiwg.ngchat.fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.uekiwg.ngchat.MainApplication;
import com.uekiwg.ngchat.R;
import com.uekiwg.ngchat.activity.BaseActivity;

public class CommentListFragments {

    public static CommentListFragment[] newFragments() {
        return new CommentListFragment[] {
                new CommentListFragments.RecentPosts(),
                new CommentListFragments.MyPosts(),
                new CommentListFragments.MyTopPosts(),
        };
    }

    public static class RecentPosts extends CommentListFragment {
        @Override
        public String getName() {
            return str(R.string.heading_recent);
        }
        @Override
        public Query getQuery(DatabaseReference databaseReference) {
            return databaseReference.child("comments").limitToFirst(10);
        }
    }
    public static class MyPosts extends CommentListFragment {
        @Override
        public String getName() {
            return str(R.string.heading_my_posts);
        }
        @Override
        public Query getQuery(DatabaseReference databaseReference) {
//            return databaseReference.child("comments").child(getUid());
            return databaseReference.child("comments").equalTo("", "email");
        }
    }
    public static class MyTopPosts extends CommentListFragment {
        @Override
        public String getName() {
            return str(R.string.heading_my_top_posts);
        }
        @Override
        public Query getQuery(DatabaseReference databaseReference) {
            return databaseReference.child("comments").limitToLast(10);
        }
    }
}
