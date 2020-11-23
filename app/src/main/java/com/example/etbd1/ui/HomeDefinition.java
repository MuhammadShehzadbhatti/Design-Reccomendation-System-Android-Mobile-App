package com.example.etbd1.ui;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.etbd1.HomeFragment;
import com.example.etbd1.MainActivity;
import com.example.etbd1.R;
import com.example.etbd1.ui.main.user.SignIn;
import com.example.etbd1.ui.main.user.SignUp;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeDefinition extends AppCompatActivity {

    Intent intent;
    public static FirebaseAuth firebaseAuth;
    public static FirebaseUser firebaseUser;
    public static FirebaseAnalytics firebaseAnalytics;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_definition);
        firebaseAnalytics = FirebaseAnalytics.getInstance(getApplicationContext());
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null) {
            if (firebaseAuth.getCurrentUser().isEmailVerified()) {

                firebaseAnalytics.setUserId(firebaseUser.getUid());
                Log.e("Tag123",firebaseUser.getUid());
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            } else {
                toastMessage("Email verification required...");
                firebaseAuth.signOut();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerHomeDef, new SignIn()).commit();
            }
        } else {
            toastMessage("Login is required");
            Bundle bundle= new Bundle();
            bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS,"SignIn");
            bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME,"SignIn fragment");
            firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW,bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerHomeDef, new SignIn(),"signIn Fragment").commit();
        }
    }



    public void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
