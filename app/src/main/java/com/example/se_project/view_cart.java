package com.example.se_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class view_cart extends AppCompatActivity {

    Button proceed_to_payment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cart);

        proceed_to_payment = (Button) findViewById(R.id.proceed_to_payment);

        proceed_to_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view_cart.this,payment_page.class);
                startActivity(intent);
            }
        });


    }
}
