package com.uekiwg.ngchat.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.uekiwg.ngchat.MainActivity;
import com.uekiwg.ngchat.MainApplication;


public class BaseActivity extends AppCompatActivity {

    private static final String TAG = "BaseActivity";

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, this.getClass().getSimpleName() + "#onCreate Start.");
        super.onCreate(savedInstanceState);
        Log.d(TAG, this.getClass().getSimpleName() + "#onCreate End.");
    }

    @Override
    public void onStart() {
        Log.d(TAG, this.getClass().getSimpleName() + "#onStart Start.");
        super.onStart();
        Log.d(TAG, this.getClass().getSimpleName() + "#onStart End.");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, this.getClass().getSimpleName() + "#onActivityResult Start.");
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, this.getClass().getSimpleName() + "#onActivityResult End.");
    }

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setMessage("Loading...");
        }
        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    protected void snackbar(View view, String text, String action) {
        Snackbar.make(view, text, Snackbar.LENGTH_LONG).setAction(action, null).show();
    }

    protected void toast(BaseActivity activity, String text) {
        Toast.makeText(activity, text, Toast.LENGTH_LONG).show();
    }
    public String getUid() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    public MainApplication app() {
        return (MainApplication) getApplication();
    }
}
