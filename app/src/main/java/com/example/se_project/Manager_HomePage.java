package com.example.se_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class Manager_HomePage extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {


    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
    Cursor cursor;

    Button plus_for_drinks,minus_for_drinks,plus_for_sandwiches,minus_for_sandwiches,plus_for_snacks,minus_for_snacks;
    TextView text_for_drinks,text_for_sandwiches,text_for_snacks,text_for_drinks_price,text_for_sandwiches_price,text_for_snacks_price;
    TextView drinks_text_display,sandwiches_text_display,snacks_text_display;
    int No_Of_Drinks=0,No_Of_Sandwiches=0,No_Of_Snacks=0;



    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    String[] vehicles={"","Food Truck 1","Food Truck 2","Cart 1","Cart 2","Cart 3","Cart 4","Cart 5"};
    TextView vehicle_display_name;

    Button view_cart,add_to_cart;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager__home_page);
        vehicle_display_name=(TextView)findViewById(R.id.Vehicle_display_name) ;

        plus_for_drinks=(Button)findViewById(R.id.plus_for_drinks);
        minus_for_drinks=(Button)findViewById(R.id.minus_for_drinks);
        plus_for_sandwiches=(Button)findViewById(R.id.plus_for_sandwiches);
        minus_for_sandwiches=(Button)findViewById(R.id.minus_for_sandwiches);
        plus_for_snacks=(Button)findViewById(R.id.plus_for_snacks);
        minus_for_snacks=(Button)findViewById(R.id.minus_for_snacks);

        text_for_drinks=findViewById(R.id.text_for_drinks);
        text_for_drinks_price=findViewById(R.id.drinks_price);
        text_for_sandwiches=findViewById(R.id.text_for_sandwiches);
        text_for_sandwiches_price=findViewById(R.id.sandwiches_price);
        text_for_snacks=findViewById(R.id.text_for_snacks);
        text_for_snacks_price=findViewById(R.id.snacks_price);

        drinks_text_display=findViewById(R.id.drinks_display);
        sandwiches_text_display=findViewById(R.id.sandwiches_display);
        snacks_text_display=findViewById(R.id.snacks_display);

        add_to_cart=findViewById(R.id.add_to_cart_button);




        openHelper = new DatabaseHelper(this);
        db = openHelper.getReadableDatabase();

        Intent intent= getIntent();
        username=intent.getStringExtra("username");


        drawerLayout = findViewById(R.id.drawer);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigation_view);

        view_cart = (Button) findViewById(R.id.view_cart);


        Spinner spin = (Spinner) findViewById(R.id.select_vehicle);
        spin.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,vehicles);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawerOpen,R.string.drawerClose);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                menuItem.setChecked(true);
                int id = menuItem.getItemId();
                if(id == R.id.logout_button){
                    final AlertDialog.Builder builder = new AlertDialog.Builder(Manager_HomePage.this);
                    builder.setMessage("Are you sure you want to logout?");
                    builder.setCancelable(true);
                    builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            Toast.makeText(getApplicationContext(),"Logged Out Successfully",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(Manager_HomePage.this,login.class);
                            startActivity(intent);
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

                if(id == R.id.view_profile){
                    Intent intent = new Intent(Manager_HomePage.this,view_profile.class);
                    intent.putExtra("username",username);
                    startActivity(intent);
                }

                if(id == R.id.view_cart){
                    Intent intent = new Intent(Manager_HomePage.this, view_cart.class);
                    startActivity(intent);
                }
                if(id == R.id.view_vehicle_inventory){
                    Intent intent = new Intent(Manager_HomePage.this,view_vehicle_inventory.class);
                    startActivity(intent);

                }

                if(id == R.id.assign_operator){
                    Intent intent = new Intent(Manager_HomePage.this,assign_operator.class);
                    startActivity(intent);
                }

                if(id == R.id.vehicle_location)
                {
                    Intent intent = new Intent(Manager_HomePage.this,assign_vehicle_location.class);
                    startActivity(intent);
                }

                if(id == R.id.operator_location){
                    Intent intent = new Intent(Manager_HomePage.this,operator_location.class);
                    startActivity(intent);
                }


                return true;
            }
        });

        view_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                Intent intent = new Intent(User_HomePage.this, view_cart.class);
                startActivity(intent);
                */

                cursor = db.rawQuery("SELECT * FROM "+DatabaseHelper.TABLE_NAME+" WHERE "+ DatabaseHelper.COL_2+" =?",new String[]{username});


                if(cursor!=null){

                    if(cursor.getCount()>0){
                        cursor.moveToNext();
                        //    String answer = db.rawQuery("SELECT type_of_user FROM "+DatabaseHelper.TABLE_NAME+" WHERE "+ DatabaseHelper.COL_2+" =? AND " + DatabaseHelper.COL_3 +"=?",new String[]{username,password});

                        Toast.makeText(getApplicationContext(),"Email:"+cursor.getString(5),Toast.LENGTH_LONG).show();


                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_SHORT).show();
                    }
                }






            }
        });




    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(vehicles[position].equals("")){

        }
        else {
            vehicle_display_name.setText(vehicles[position]);
            plus_for_drinks.setVisibility(View.VISIBLE);
            minus_for_drinks.setVisibility(View.VISIBLE);
            plus_for_sandwiches.setVisibility(View.VISIBLE);
            minus_for_sandwiches.setVisibility(View.VISIBLE);
            plus_for_snacks.setVisibility(View.VISIBLE);
            minus_for_snacks.setVisibility(View.VISIBLE);

            text_for_sandwiches_price.setVisibility(View.VISIBLE);
            text_for_drinks_price.setVisibility(View.VISIBLE);
            text_for_snacks_price.setVisibility(View.VISIBLE);

            drinks_text_display.setVisibility(View.VISIBLE);
            sandwiches_text_display.setVisibility(View.VISIBLE);
            snacks_text_display.setVisibility(View.VISIBLE);

            add_to_cart.setVisibility(View.VISIBLE);

            plus_for_drinks.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    No_Of_Drinks++;
                    text_for_drinks.setText(""+No_Of_Drinks);
                    Double set_drinks_price=No_Of_Drinks*1.50;
                    text_for_drinks_price.setText(""+set_drinks_price);
                }
            });

            minus_for_drinks.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    No_Of_Drinks--;
                    text_for_drinks.setText(""+No_Of_Drinks);
                    Double set_drinks_price=No_Of_Drinks*1.50;
                    if(!set_drinks_price.equals(0.0)){
                        text_for_drinks_price.setText(""+set_drinks_price);
                    }
                    else{
                        text_for_drinks_price.setText("");
                    }


                }
            });

            plus_for_sandwiches.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    No_Of_Sandwiches++;
                    text_for_sandwiches.setText(""+No_Of_Sandwiches);
                    Double set_sandwiches_price=No_Of_Sandwiches*5.75;
                    text_for_sandwiches_price.setText(""+set_sandwiches_price);
                }
            });
            minus_for_sandwiches.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    No_Of_Sandwiches--;
                    text_for_sandwiches.setText(""+No_Of_Sandwiches);
                    Double set_sandwiches_price=No_Of_Sandwiches*5.75;
                    if(!set_sandwiches_price.equals(0.0)) {
                        text_for_sandwiches_price.setText("" + set_sandwiches_price);
                    }
                    else{
                        text_for_sandwiches_price.setText("");
                    }
                }
            });

            plus_for_snacks.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    No_Of_Snacks++;
                    text_for_snacks.setText(""+No_Of_Snacks);
                    Double set_snacks_price=No_Of_Snacks*1.25;
                    text_for_snacks_price.setText(""+set_snacks_price);
                }
            });
            minus_for_snacks.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    No_Of_Snacks--;
                    text_for_snacks.setText(""+No_Of_Snacks);
                    Double set_snacks_price=No_Of_Snacks*1.25;
                    if(!set_snacks_price.equals(0.0)) {
                        text_for_snacks_price.setText("" + set_snacks_price);
                    }
                    else{
                        text_for_snacks_price.setText("");
                    }
                }
            });






        }












    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
