package com.azoza.rcafe.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.azoza.rcafe.R;


import org.json.JSONObject;

public class PaymentActivity extends AppCompatActivity {



    Toolbar toolbar;

    TextView subTotal, offers, delivery, total;
    Button paymentBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        //ToolBar
        toolbar = findViewById(R.id.payment_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        double amount = 0.0;
        amount = getIntent().getDoubleExtra("amount", 0.0);


        subTotal = findViewById(R.id.sub_total);
        offers = findViewById(R.id.textView17);
        delivery = findViewById(R.id.textView18);
        total = findViewById(R.id.total_amount);
        paymentBtn = findViewById(R.id.pay_btn);



        subTotal.setText("Rs." + amount);
        offers.setText("Rs.0.00");
        delivery.setText("Rs.0.0");

        total.setText("Rs."+amount);

        paymentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                paymentMethod();
            }
        });


    }

//
}