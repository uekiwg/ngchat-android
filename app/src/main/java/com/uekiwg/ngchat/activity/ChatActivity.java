package com.uekiwg.ngchat.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.uekiwg.ngchat.R;
import com.uekiwg.ngchat.fragment.CommentListFragment;
import com.uekiwg.ngchat.fragment.CommentListFragments;
import com.uekiwg.ngchat.model.Comment;

public class ChatActivity extends BaseActivity {

    private static final String TAG = "ChatActivity";

    private FragmentPagerAdapter mPagerAdapter;
    private ViewPager mViewPager;
    private EditText commentEdit;
    private Button commentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        commentEdit= findViewById(R.id.commentEdit);
        commentButton= findViewById(R.id.commentButton);

        mPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            private CommentListFragment[] mFragments = CommentListFragments.newFragments();
            @Override
            public Fragment getItem(int position) {
                return mFragments[position];
            }
            @Override
            public int getCount() {
                return mFragments.length;
            }
            @Override
            public CharSequence getPageTitle(int position) {
                return mFragments[position].getName();
            }
        };
        mViewPager = findViewById(R.id.viewPager);
        mViewPager.setAdapter(mPagerAdapter);
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        commentButton.setOnClickListener((View v) -> {
            String text = commentEdit.getText().toString();
            if (text == null || text.trim().length() == 0) {
                return;
            }
            try {
                Comment comment = new Comment(app().account.getEmail(), text);
                app().commentRef().push().setValue(comment);

            } catch (Exception e) {
                Log.w(TAG, "commentButton#onClick failed.", e);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
