package com.uekiwg.ngchat.view;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.uekiwg.ngchat.MainApplication;
import com.uekiwg.ngchat.R;
import com.uekiwg.ngchat.model.Comment;

public class CommentViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = "CommentViewHolder";

    public ImageView starImageView;
    public TextView dateTimeView;
    public TextView emailView;
    //public ImageView starView;
    public TextView contentView;

    public CommentViewHolder(View itemView) {
        super(itemView);
        starImageView = itemView.findViewById(R.id.star);
        dateTimeView = itemView.findViewById(R.id.dateTime);
        emailView = itemView.findViewById(R.id.email);
        contentView = itemView.findViewById(R.id.content);
    }

    public void bindToPost(Comment comment, View.OnClickListener starClickListener) {
        try {
            if (app().account.getEmail().equals(comment.email)) {
                starImageView.setImageResource(R.drawable.ic_toggle_star_24);
            }
            dateTimeView.setText(comment.getDateTimeStr());
            emailView.setText(comment.email);
            contentView.setText(comment.content);
            //starView.setOnClickListener(starClickListener);
        } catch (Exception e) {
            Log.w(TAG, "bindToPost failed", e);
        }
    }

    private MainApplication app() {
        return MainApplication.instance;
    }
}
