package com.example.se_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class view_profile extends AppCompatActivity {

    ImageView imageView;
    Button update_button;
    String username_DB;
    EditText username_viewprofile;
    EditText lastname_viewprofifle;
    EditText firstname_viewprofile;
    EditText email_viewprofile;
    EditText phone_viewprofile;
    EditText streetaddress_viewprofile;
    EditText city_viewprofile;
    EditText state_viewprofile;
    EditText zipcode_viewprofile;


    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
    Cursor cursor;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        username_viewprofile = (EditText)findViewById(R.id.username_viewprofile);
        lastname_viewprofifle=(EditText)findViewById(R.id.lastname_viewprofile);
        firstname_viewprofile = (EditText)findViewById(R.id.firstname_viewprofile);
        email_viewprofile = (EditText)findViewById(R.id.email_viewprofile);
        phone_viewprofile = (EditText)findViewById(R.id.phone_viewprofile);
        streetaddress_viewprofile = (EditText)findViewById(R.id.streetaddress_viewprofile);
        city_viewprofile = (EditText)findViewById(R.id.city_viewprofile);
        state_viewprofile = (EditText)findViewById(R.id.state_viewprofile);
        zipcode_viewprofile = (EditText)findViewById(R.id.zipcode_viewprofile);

        Intent intent = getIntent();
        username_DB = intent.getStringExtra("username");

        openHelper = new DatabaseHelper(this);
        db = openHelper.getReadableDatabase();

        cursor = db.rawQuery("SELECT * FROM "+DatabaseHelper.TABLE_NAME+" WHERE "+ DatabaseHelper.COL_2+" =?",new String[]{username_DB});

        if(cursor!=null){
            if(cursor.getCount()>0){
                cursor.moveToNext();
                username_viewprofile.setText(cursor.getString(1));
                lastname_viewprofifle.setText(cursor.getString(3));
                firstname_viewprofile.setText(cursor.getString(4));
                email_viewprofile.setText(cursor.getString(5));
                phone_viewprofile.setText(cursor.getString(6));
                streetaddress_viewprofile.setText(cursor.getString(7));
                city_viewprofile.setText(cursor.getString(8));
                state_viewprofile.setText(cursor.getString(9));
                zipcode_viewprofile.setText(cursor.getString(10));


            }
        }
        cursor.close();





        try{
            Toast.makeText(getApplicationContext(),cursor.getString(1),Toast.LENGTH_LONG).show();

        }
        catch (Exception e){
            Log.v("Error here:"," Cursor.getString not working");
        }

        imageView = (ImageView) findViewById(R.id.update_profile);
        update_button = (Button) findViewById(R.id.update_button);

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view_profile.this,update_profile.class);
                intent.putExtra("username",username_DB);
                startActivity(intent);
            }
        });


    }
}
