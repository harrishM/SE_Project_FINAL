package com.example.se_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class payment_page extends AppCompatActivity {

    Button confirm_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_page);

        confirm_button = (Button) findViewById(R.id.confirm_and_order_button);

        confirm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(payment_page.this,confirmation_page.class);
                startActivity(intent);
            }
        });



    }
}
