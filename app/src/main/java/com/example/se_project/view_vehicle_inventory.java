package com.example.se_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class view_vehicle_inventory extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    String[] vehicles={"Food Truck 1","Food Truck 2","Cart 1","Cart 2","Cart 3","Cart 4","Cart 5"};

    Button plus_for_drinks,minus_for_drinks,plus_for_sandwiches,minus_for_sandwiches,plus_for_snacks,minus_for_snacks;
    TextView text_for_drinks,text_for_sandwiches,text_for_snacks;
    int No_Of_Drinks=0,No_Of_Sandwiches=0,No_Of_Snacks=0;

    Button restock_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_vehicle_inventory);

        plus_for_drinks = (Button)findViewById(R.id.plus_for_drinks);
        minus_for_drinks=(Button)findViewById(R.id.minus_for_drinks);
        text_for_drinks=(TextView)findViewById(R.id.text_for_drinks);


        try {
            plus_for_drinks.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    No_Of_Drinks++;
                    text_for_drinks.setText(""+No_Of_Drinks);

                }
            });
        }
        catch(Exception e){
            Log.v("PROBLEM IN HERE: ",""+e);
        }
        minus_for_drinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                No_Of_Drinks--;
                text_for_drinks.setText(""+No_Of_Drinks);
            }
        });

        plus_for_sandwiches=(Button)findViewById(R.id.plus_for_sandwiches);
        minus_for_sandwiches=(Button)findViewById(R.id.minus_for_sandwiches);
        text_for_sandwiches=(TextView)findViewById(R.id.text_for_sandwiches);

        plus_for_sandwiches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                No_Of_Sandwiches++;
                text_for_sandwiches.setText(""+No_Of_Sandwiches);
            }
        });
        minus_for_sandwiches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                No_Of_Sandwiches--;
                text_for_sandwiches.setText(""+No_Of_Sandwiches);
            }
        });

        plus_for_snacks=(Button)findViewById(R.id.plus_for_snacks);
        minus_for_snacks=(Button)findViewById(R.id.minus_for_snacks);
        text_for_snacks=(TextView)findViewById(R.id.text_for_snacks);

        plus_for_snacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                No_Of_Snacks++;
                text_for_snacks.setText(""+No_Of_Snacks);
            }
        });
        minus_for_snacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                No_Of_Snacks--;
                text_for_snacks.setText(""+No_Of_Snacks);
            }
        });

        restock_button = (Button) findViewById(R.id.restock_confirmation_button);

        Spinner spin = (Spinner) findViewById(R.id.select_vehicle);
        spin.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,vehicles);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);


        restock_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(view_vehicle_inventory.this);
                builder.setMessage("Confirm restock");
                builder.setCancelable(true);
                builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        Toast.makeText(getApplicationContext(),"Restock Successful",Toast.LENGTH_LONG).show();
                    }
                });
                builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
