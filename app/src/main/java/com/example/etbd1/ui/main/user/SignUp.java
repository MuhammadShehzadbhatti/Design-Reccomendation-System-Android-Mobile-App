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
import android.widget.Toast;

import com.example.etbd1.HomeFragment;
import com.example.etbd1.R;
import com.example.etbd1.ui.HomeDefinition;
import com.example.etbd1.ui.ProductDetailsFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static androidx.constraintlayout.widget.Constraints.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUp extends Fragment {

    private EditText txtUserName, txtUserEmail, txtUserPassword, txtAgainUserPassword;
    private Button btnSignUp;
    private FirebaseAuth mAuth;
    private Users userData;
    FragmentTransaction fragmentTransaction;
    public SignUp() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_sign_up, container, false);

        txtUserName = view.findViewById(R.id.enterUserName_SignUp);
        txtUserEmail = view.findViewById(R.id.enterEmail_SignUp);
        txtUserPassword = view.findViewById(R.id.enterPassword_SignUp);
        txtAgainUserPassword = view.findViewById(R.id.enterConfirmPassword_SignUp);
        btnSignUp = view.findViewById(R.id.btnSignup_SignUp);
        HomeFragment homeFragment= new HomeFragment();



        mAuth = FirebaseAuth.getInstance();

        FragmentManager fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        /*if (mAuth.getCurrentUser().getUid()!=null){
            fragmentTransaction.replace(R.id.fragment_container,homeFragment,"Product_Details_Fragment").commit();
        }*/

        userData = new Users();
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterUser();
            }
        });

        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser!=null){

        }
    }

    private void RegisterUser() {
        /*if (!uploadImage().isEmpty()){
            userData.setUserProfilePicUrl(uploadImage());
        }
        else {
            toastMessage("Error in uploading picture ");
        }*/

        if (TextUtils.isEmpty(txtUserName.getText().toString())) {

            txtUserName.setError("Name Can't be empty");
            txtUserName.requestFocus();
        }

        if (!TextUtils.isEmpty(txtUserEmail.getText().toString())) {
            if (isEmailValid(txtUserEmail.getText().toString())) {
                userData.setUserEmail(txtUserEmail.getText().toString().trim());
            } else {
                txtUserEmail.setError("Enter a valid email address");
                txtUserEmail.requestFocus();
            }

        } else {

            txtUserEmail.setError("Email Can't be empty");
            txtUserEmail.requestFocus();

        }
        if (!TextUtils.isEmpty(txtUserPassword.getText().toString())) {
            if (txtUserPassword.getText().toString().length() >= 6) {

                if (!TextUtils.isEmpty(txtAgainUserPassword.getText().toString())) {

                    if (txtUserPassword.getText().toString().equals(txtAgainUserPassword.getText().toString())) {
                        //userPassword = txtUserPassword.getText().toString().trim();
                        userData.setUserPassword(txtUserPassword.getText().toString().trim());

                    } else {

                        txtAgainUserPassword.setError("Password Does not match");
                        txtAgainUserPassword.requestFocus();
                        txtUserPassword.setError("Password Does not match");
                        txtUserPassword.requestFocus();

                    }

                } else {

                    txtAgainUserPassword.setError("Confirm Password Can't be empty");
                    txtAgainUserPassword.requestFocus();

                }
            } else {

                txtUserPassword.setError("Password must contain at least 6 characters");
                txtUserPassword.requestFocus();

            }

        } else {

            txtUserPassword.setError("Password Can't be empty");
            txtUserPassword.requestFocus();

        }
        /*mAuth.createUserWithEmailAndPassword(userData.getUserEmail(), userData.getUserPassword())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getContext(), "Authentication failed.",Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });*/
        try {
            if (!txtUserEmail.getText().toString().isEmpty() && !txtUserPassword.getText().toString().isEmpty()) {
                mAuth.createUserWithEmailAndPassword(txtUserEmail.getText().toString(), txtUserPassword.getText().toString().trim())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Log.i("name and pass:",txtUserEmail.getText().toString()+"  "+txtUserPassword.getText().toString());
                                    //userData.setUserId(mAuth.getCurrentUser().getUid());
                                    mAuth.getCurrentUser().sendEmailVerification()
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        if (mAuth.getCurrentUser().isEmailVerified()) {
                                                            startActivity(new Intent(getContext(), HomeDefinition.class));

                                                        } else {
                                                            mAuth.signOut();
                                                            SignIn signIn = new SignIn();
                                                            fragmentTransaction.replace(R.id.fragment_containerHomeDef, signIn).commit();
                                                            //finish();
                                                        }
                                                        Toast.makeText(getContext(), "Check your email for verification", Toast.LENGTH_SHORT).show();

                                                        //startActivity(new Intent(User_SignUp.this, HomeDefinition.class));
                                                        //finish();
                                                    } else {
                                                        Toast.makeText(getContext(), "Enter a valid email", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });
                                } else {
                                    //do on failure
                                    FirebaseAuthException e = (FirebaseAuthException )task.getException();
                                    Toast.makeText(getContext(), "Failed Registration: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                                    Log.i("Failed Registration: ",e.getMessage());
                                    //message.hide();
                                    return;
                                }
                            }
                        });


            } else {
                Toast.makeText(getContext(), "Email or Password can't be empty", Toast.LENGTH_SHORT).show();
            }
        } catch (NullPointerException e) {
            Toast.makeText(getContext(), "Email already exists!", Toast.LENGTH_SHORT).show();
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
