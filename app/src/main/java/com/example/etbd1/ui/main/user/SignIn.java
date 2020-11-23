package com.example.etbd1.ui.main.user;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.etbd1.MainActivity;
import com.example.etbd1.R;
import com.example.etbd1.ui.HomeDefinition;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignIn extends Fragment {
    EditText txtUserEmail,txtUserPassword;
    Button btnLogin;
    TextView txtForgetPassword,txtgoToSignUp;
    Users userData;
    FragmentTransaction fragmentTransaction;
    FirebaseAnalytics firebaseAnalytics;
    public SignIn() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        txtUserEmail =(EditText) view.findViewById(R.id.enterUserLoginEmail);
        txtUserPassword = (EditText) view.findViewById(R.id.enterUserLoginPassword);
        btnLogin = view.findViewById(R.id.btnLogin);
        txtForgetPassword = view.findViewById(R.id.forgetLoginPassword);
        txtgoToSignUp = view.findViewById(R.id.txtRegister);
        firebaseAnalytics = FirebaseAnalytics.getInstance(getContext());
        Fragment currentFragment = getFragmentManager().findFragmentByTag("signIn Fragment");
        Log.e("current fragment",currentFragment.getTag());
        //if (currentFragment.getTag()=="signIn Fragment"){
        Bundle bundle=new Bundle();
        bundle.putString("fa_screen_name","SignInFragment");
        bundle.putString("fa_screen_class","SignIn");
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW,bundle);
        //}
        FragmentManager fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        final String email = txtUserEmail.getText().toString().trim();
        final String password= txtUserPassword.getText().toString().trim();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("button_name", "Login Button");
                bundle.putString("button_id", "btnLogin");
                HomeDefinition.firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT,bundle);
                //varifyEntries();
                logInUser();

            }
        });
        txtgoToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SignUp signUp = new SignUp();
                fragmentTransaction.replace(R.id.fragment_containerHomeDef,signUp).commit();
            }
        });

        return view;

    }

    public void varifyEntries(){
        if (!TextUtils.isEmpty(txtUserEmail.getText().toString())) {
            if (isEmailValid(txtUserEmail.getText().toString())) {
                Log.d("txtUserEmail: ",txtUserEmail.getText().toString());
                userData.setUserEmail(txtUserEmail.getText().toString());
            } else {
                txtUserEmail.setError("Enter a valid email address");
                txtUserEmail.requestFocus();
            }

        } else {

            txtUserEmail.setError("Email Can't be empty");
            txtUserEmail.requestFocus();

        }
        if (!TextUtils.isEmpty(txtUserPassword.getText().toString())){
            userData.setUserPassword(txtUserPassword.getText().toString().trim());
        }
    }


    public void logInUser(){
        if (!TextUtils.isEmpty(txtUserEmail.getText().toString()) && !TextUtils.isEmpty(txtUserPassword.getText().toString())) {

                if (HomeDefinition.firebaseUser == null) {
                    HomeDefinition.firebaseAuth.signInWithEmailAndPassword(txtUserEmail.getText().toString(), txtUserPassword.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        //if (firebaseUser.isEmailVerified()) {
                                        if (HomeDefinition.firebaseUser!=null){
                                            /*Bundle bundle = new Bundle();
                                            bundle.putString("user_id",firebaseUser.getUid());
                                            bundle.putString("user_name",firebaseUser.getDisplayName());
                                            bundle.putString("user_email",firebaseUser.getEmail())*/;
                                            //firebaseAnalytics.setUserProperty("loggedinUser",bundle);
                                            /*firebaseAnalytics.setUserProperty("userId",firebaseUser.getUid());
                                            firebaseAnalytics.setUserProperty("username", firebaseUser.getDisplayName());
                                            firebaseAnalytics.setUserProperty("userEmail", firebaseUser.getEmail());*/
                                        }
                                        //firebaseAnalytics.setUserProperty("user_id",firebaseUser.getUid());
                                        //firebaseAnalytics.setUserProperty("user_name",firebaseUser.getDisplayName());
                                        //firebaseAnalytics.setUserProperty("user_email",firebaseUser.getEmail());
                                        startActivity(new Intent(getContext(), HomeDefinition.class));

//                                        }else {
//                                            Toast.makeText(getContext(), "Verify Email: ", Toast.LENGTH_LONG).show();
//                                        }
                                    } else {
                                        Toast.makeText(getContext(), "   Operation unsuccessfully completed", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }
        }
    }



    private boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            Toast.makeText(getContext(), "please enter valid Email ", Toast.LENGTH_SHORT).show();

        }
        return matcher.matches();
    }
}
