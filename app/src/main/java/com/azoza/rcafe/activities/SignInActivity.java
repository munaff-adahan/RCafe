package com.azoza.rcafe.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.azoza.rcafe.R;
import com.azoza.rcafe.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

public class SignInActivity extends AppCompatActivity {
    EditText email, password;
    private FirebaseAuth auth;
    User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        ImageView imageView = findViewById(R.id.imageView);
        Picasso.get().load(R.drawable.logo)
                .resize(400,500)
                .into(imageView);



        auth = FirebaseAuth.getInstance();

        TextView view = findViewById(R.id.signUpText);
        changeUI(view);

        findViewById(R.id.signInButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setParameter();
                if (setParameter().equals("Okay")) {
                    signIn();
                } else {
                    Toast.makeText(SignInActivity.this, setParameter().toString(), Toast.LENGTH_LONG).show();
                }

            }
        });

    }
    public void changeUI(View textField) {
        textField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
            }
        });
    }
    public String setParameter() {
        email = findViewById(R.id.sign_in_email);
        password = findViewById(R.id.password);
        if (email.equals(null)) {
            return "Email Field is empty";
        } else if (password.equals(null)) {
            return "Password Field is Empty";
        }
        return "Okay";
    }
    public void signIn() {
        String uMail = email.getText().toString();
        String uPassword = password.getText().toString();

        if (TextUtils.isEmpty(uMail)) {
            Toast.makeText(SignInActivity.this, "Enter Email", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(uPassword)) {
            Toast.makeText(SignInActivity.this, "Enter Password", Toast.LENGTH_LONG).show();
        } else {
            auth.signInWithEmailAndPassword(uMail, uPassword).addOnCompleteListener(SignInActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(SignInActivity.this, "Login Successfully", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(SignInActivity.this, MainActivity.class));
                    } else {
                        Toast.makeText(SignInActivity.this, "Login Fail " + task.getException(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

    }
}