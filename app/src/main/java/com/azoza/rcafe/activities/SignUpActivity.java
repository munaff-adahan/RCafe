package com.azoza.rcafe.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.azoza.rcafe.R;
import com.azoza.rcafe.model.User;
import com.azoza.rcafe.utill.DbHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

public class SignUpActivity extends AppCompatActivity {

    public static final String TAG = SignUpActivity.class.getName();
    EditText name, email, password;

    SharedPreferences sharedPreferences;
    private FirebaseAuth auth;
    Button button;

    User user = new User();

    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ImageView imageView = findViewById(R.id.imageView);
        Picasso.get().load(R.drawable.logo)
                .resize(400,500)
                .into(imageView);

       dbHelper = new DbHelper(getApplicationContext());    //
       //dbHelper.getReadableDatabase();                              //

        //getSupportActionBar().hide();
        auth = FirebaseAuth.getInstance();



        if(auth.getCurrentUser() != null){
            startActivity(new Intent(SignUpActivity.this, MainActivity.class));
            finish();
        }

        changeUI(findViewById(R.id.signInText));


        button = findViewById(R.id.signUpButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setParameters();
                if (setParameters().equals("Okay")) {
                    signUp();
                } else {
                    Toast.makeText(SignUpActivity.this, setParameters().toString(), Toast.LENGTH_LONG).show();
                }

            }
        });

    }


    public void signUp() {


        String uName = name.getText().toString();
        String uEmail = email.getText().toString();
        String uPassword = password.getText().toString();


        if (TextUtils.isEmpty(uName)) {
            Toast.makeText(SignUpActivity.this, "Enter Name", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(uEmail)) {
            Toast.makeText(SignUpActivity.this, "Enter Email", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(uPassword)) {
            Toast.makeText(SignUpActivity.this, "Enter Password", Toast.LENGTH_LONG).show();
        } else {

            user.setName(uName);
            user.setEmail(uEmail);
            user.setPassword(uPassword);

            // Toast.makeText(SignUpActivity.this, "Success", Toast.LENGTH_LONG).show();

            auth.createUserWithEmailAndPassword(uEmail, uPassword)
                    .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(SignUpActivity.this, "Successfully Register", Toast.LENGTH_LONG).show();
                                dbHelper.insertUser(user);

                                Intent intent = new Intent(SignUpActivity.this,MainActivity.class);
                                startActivity(intent);
                                finish();
                                //startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                            } else {
                                Toast.makeText(SignUpActivity.this, "Registration Fail " + task.getException(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });

        }


    }

    public String setParameters() {
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        sharedPreferences = getSharedPreferences("onBoardingScreen",MODE_PRIVATE);
        boolean isFirstTime = sharedPreferences.getBoolean("firstTime",true);
        if(isFirstTime){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("firstTime",false);
            editor.commit();


        }

        if (name.equals(null)) {
            return "Name Field is empty";
        } else if (email.equals(null)) {
            return "Email Field is empty";
        } else if (password.equals(null)) {
            return "Password Field is Empty";
        }

        return "Okay";
    }

    public void changeUI(View textField) {
        textField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
            }
        });
    }
}