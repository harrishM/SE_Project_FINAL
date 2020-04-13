package com.example.se_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class assign_vehicle_location extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    String[] vehicles={"Food Truck 1","Food Truck 2","Cart 1","Cart 2","Cart 3","Cart 4","Cart 5"};

    Button vehicle_assignment_button;

    String[] locations={"Cooper & UTA Blvd(1)","W Nedderman & Greek Row(2)","S Davis & W Mitchell(3)","Cooper & W Mitchell(4)","S Oak & UTA Blvd(5)","Spaniolo & W 1st(6)","Spaniolo & W Mitchell(7)","S Center & W Mitchell(8)"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_vehicle_location);

        vehicle_assignment_button = (Button) findViewById(R.id.vehicle_assignment_confirmation_button);


        vehicle_assignment_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(assign_vehicle_location.this);
                builder.setMessage("Confirm Assignment");
                builder.setCancelable(true);
                builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        Toast.makeText(getApplicationContext(),"Assigned Successfully",Toast.LENGTH_LONG).show();

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



        Spinner spin = (Spinner) findViewById(R.id.select_vehicle);
        spin.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,vehicles);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);



        Spinner spin2 = (Spinner) findViewById(R.id.select_vehicle2);
        spin2.setOnItemSelectedListener(this);
        ArrayAdapter aa2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,locations);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//Setting the ArrayAdapter data on the Spinner  
        spin2.setAdapter(aa2);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
