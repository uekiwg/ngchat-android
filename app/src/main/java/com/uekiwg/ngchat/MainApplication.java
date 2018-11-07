package com.uekiwg.ngchat;

import android.app.Application;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * アプリケーションコンテキスト
 */
public class MainApplication extends Application {

    public static final int RC_SIGN_IN = 9001;

    public static MainApplication instance;
    public GoogleSignInAccount account;

    private DatabaseReference mCommentRef;
    public DatabaseReference commentRef() {
        if (mCommentRef == null) {
            mCommentRef = FirebaseDatabase.getInstance().getReference().child("comments");
        }
        return mCommentRef;
    }


//    private FirebaseAuth mFirebaseAuth;
//    private GoogleSignInClient mGoogleSignInClient;

    /**
     * 起動時処理
     */
    @Override
    public void onCreate() {
        super.onCreate();
    }

    /**
     * 終了処理
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
    }

//    public FirebaseAuth getFirebaseAuth() {
//        if (mFirebaseAuth == null) {
//            mFirebaseAuth = FirebaseAuth.getInstance();
//        }
//        return mFirebaseAuth;
//    }
//
//    public GoogleSignInClient getGoogleSignInClient() {
//        if (mGoogleSignInClient == null) {
//            // Configure Google Sign In
//            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                    .requestIdToken(getString(R.string.default_web_client_id))
//                    .requestEmail()
//                    .build();
//            mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
//        }
//        return mGoogleSignInClient;
//    }
}
