package com.example.se_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class update_profile extends AppCompatActivity {

    Button update_button;
    String username;

    EditText    username_update,firstname_update,lastname_update,email_update,phone_update,streetaddress_update,city_update,state_update,zipcode_update;

    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);



        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        openHelper = new DatabaseHelper(this);
        db = openHelper.getReadableDatabase();

        username_update = (EditText)findViewById(R.id.username_updateprofile);
        lastname_update = (EditText)findViewById(R.id.lastname_updateprofilee);
        firstname_update = (EditText)findViewById(R.id.firstname_updateprofile);
        email_update = (EditText)findViewById(R.id.email_updateprofile);
        phone_update = (EditText)findViewById(R.id.phone_updateprofile);
        streetaddress_update = (EditText)findViewById(R.id.streetaddress_updateprofile);
        city_update = (EditText)findViewById(R.id.city_updateprofile);
        state_update = (EditText)findViewById(R.id.state_updateprofile);
        zipcode_update = (EditText)findViewById(R.id.zipcode_updateprofile);

        cursor = db.rawQuery("SELECT * FROM "+DatabaseHelper.TABLE_NAME+" WHERE "+ DatabaseHelper.COL_2+" =?",new String[]{username});

        if(cursor!=null){
            if(cursor.getCount()>0){
                cursor.moveToNext();
                username_update.setText(cursor.getString(1));
                lastname_update.setText(cursor.getString(3));
                firstname_update.setText(cursor.getString(4));
                email_update.setText(cursor.getString(5));
                phone_update.setText(cursor.getString(6));
                streetaddress_update.setText(cursor.getString(7));
                city_update.setText(cursor.getString(8));
                state_update.setText(cursor.getString(9));
                zipcode_update.setText(cursor.getString(10));
            }
        }




        update_button = (Button) findViewById(R.id.Final_Update_button);

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                String UpdateQuery = "UPDATE "+DatabaseHelper.TABLE_NAME+" SET "+DatabaseHelper.COL_2+"="+username_update.getText().toString().trim()+" "

                db.execSQL("UPDATE "+DatabaseHelper.TABLE_NAME+" SET "+DatabaseHelper.COL_2+"=? AND "+DatabaseHelper.COL_4+"=? AND "+DatabaseHelper.COL_5+"=? AND "+DatabaseHelper.COL_6+"=? AND "+DatabaseHelper.COL_7+"=? AND "+DatabaseHelper.COL_8+"=? AND "+DatabaseHelper.COL_9+"=? AND "+DatabaseHelper.COL_10+"=? AND "+DatabaseHelper.COL_11+"=? WHERE "+DatabaseHelper.COL_2+"=?",new String[]{username_update.getText().toString().trim(),lastname_update.getText().toString().trim(),firstname_update.getText().toString().trim(),email_update.getText().toString().trim(),phone_update.getText().toString().trim(),streetaddress_update.getText().toString().trim(),city_update.getText().toString().trim(),state_update.getText().toString().trim(),zipcode_update.getText().toString().trim(),username});

                */
                db = openHelper.getWritableDatabase();
                String whereClause = "username=?";
                String whereArgs[] = {username};



                ContentValues contentValues = new ContentValues();
                contentValues.put("last_name",lastname_update.getText().toString().trim());
                contentValues.put("first_name",firstname_update.getText().toString().trim());
                contentValues.put("email",email_update.getText().toString().trim());
                contentValues.put("phone_num",phone_update.getText().toString().trim());
                contentValues.put("street_address",streetaddress_update.getText().toString().trim());
                contentValues.put("city",city_update.getText().toString().trim());
                contentValues.put("state",state_update.getText().toString().trim());
                contentValues.put("zipcode",zipcode_update.getText().toString().trim());
                try{db.update(DatabaseHelper.TABLE_NAME,contentValues,whereClause,whereArgs);

                }
                catch(Exception e){
                    Log.v("THE ERROR:",""+e);
                }





                final AlertDialog.Builder builder = new AlertDialog.Builder(update_profile.this);
                builder.setMessage("Confirm update?");
                builder.setCancelable(true);
                builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        Toast.makeText(getApplicationContext(),"Profile Updated",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(update_profile.this,view_profile.class);
                        intent.putExtra("username",username);
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
        });
    }
}
