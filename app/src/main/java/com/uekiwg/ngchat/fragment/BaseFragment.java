package com.uekiwg.ngchat.fragment;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uekiwg.ngchat.MainApplication;


public class BaseFragment extends Fragment {

    private static final String TAG = "BaseFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, this.getClass().getSimpleName() + "#onCreateView Start.");
        super.onCreateView(inflater, container, savedInstanceState);
        Log.d(TAG, this.getClass().getSimpleName() + "#onCreateView End.");
        return null;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.d(TAG, this.getClass().getSimpleName() + "#onActivityCreated Start.");
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, this.getClass().getSimpleName() + "#onActivityCreated End.");
    }

    @Override
    public void onStart() {
        Log.d(TAG, this.getClass().getSimpleName() + "#onStart Start.");
        super.onStart();
        Log.d(TAG, this.getClass().getSimpleName() + "#onStart End.");
    }

    @Override
    public void onStop() {
        Log.d(TAG, this.getClass().getSimpleName() + "#onStop Start.");
        super.onStop();
        Log.d(TAG, this.getClass().getSimpleName() + "#onStop End.");
    }

    protected String str(@StringRes int resId) {
        return MainApplication.instance.getString(resId);
    }
}
