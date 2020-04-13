package com.example.se_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;

    Button _register,_loginpage;

    RadioGroup _radioGroup;
    RadioButton _radioButton;

    EditText _username,_password,_lastname,_firstname,_email,_phone,_streetaddress,_city,_state,_pincode;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openHelper = new DatabaseHelper(this);

        _register=(Button)findViewById(R.id.register);

        _radioGroup=(RadioGroup)findViewById(R.id.radiogroup);





        _username=(EditText)findViewById(R.id.username);
        _password=(EditText)findViewById(R.id.password);
        _lastname=(EditText)findViewById(R.id.lastname);
        _firstname=(EditText)findViewById(R.id.firstname);
        _email=(EditText)findViewById(R.id.email);
        _phone=(EditText)findViewById(R.id.phone);
        _streetaddress=(EditText)findViewById(R.id.streetaddress);
        _city=(EditText)findViewById(R.id.city);
        _state=(EditText)findViewById(R.id.state);
        _pincode=(EditText)findViewById(R.id.zipcode);


        _register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db=openHelper.getWritableDatabase();
                String username=_username.getText().toString();
                String password=_password.getText().toString();
                String lastname=_lastname.getText().toString();
                String firstname=_firstname.getText().toString();
                String email=_email.getText().toString();
                String phone=_phone.getText().toString();
                String streetaddress=_streetaddress.getText().toString();
                String city=_city.getText().toString();
                String state=_state.getText().toString();
                String zipcode=_pincode.getText().toString();

                int _radioid=_radioGroup.getCheckedRadioButtonId();

                _radioButton=findViewById(_radioid);

                insertdata(username,password,lastname,firstname,email,phone,streetaddress,city,state,zipcode,_radioButton.getText());

                // Toast.makeText(getApplicationContext(),"Registration Successfull",Toast.LENGTH_SHORT).show();




            }
        });



    }


    public void insertdata(String username, String password, String lastname, String firstname, String email, String phone, String streetaddress, String city, String state, String zipcode, CharSequence type_of_user){

        if(email.contains("@UTAvending.com") && (type_of_user.equals("Manager") || type_of_user.equals("Operator"))) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(DatabaseHelper.COL_2, username);
            contentValues.put(DatabaseHelper.COL_3, password);
            contentValues.put(DatabaseHelper.COL_4, lastname);
            contentValues.put(DatabaseHelper.COL_5, firstname);
            contentValues.put(DatabaseHelper.COL_6, email);
            contentValues.put(DatabaseHelper.COL_7, phone);
            contentValues.put(DatabaseHelper.COL_8, streetaddress);
            contentValues.put(DatabaseHelper.COL_9, city);
            contentValues.put(DatabaseHelper.COL_10, state);
            contentValues.put(DatabaseHelper.COL_11, zipcode);
            contentValues.put(DatabaseHelper.COL_12, type_of_user.toString());

            long id = db.insert(DatabaseHelper.TABLE_NAME, null, contentValues);

            Intent intent = new Intent(MainActivity.this,login.class);
            startActivity(intent);


            Toast.makeText(this,"Successfully Registered",Toast.LENGTH_SHORT).show();


        }
        else if (!email.contains("@UTAvending.com") && type_of_user.equals("User")){
            ContentValues contentValues = new ContentValues();
            contentValues.put(DatabaseHelper.COL_2, username);
            contentValues.put(DatabaseHelper.COL_3, password);
            contentValues.put(DatabaseHelper.COL_4, lastname);
            contentValues.put(DatabaseHelper.COL_5, firstname);
            contentValues.put(DatabaseHelper.COL_6, email);
            contentValues.put(DatabaseHelper.COL_7, phone);
            contentValues.put(DatabaseHelper.COL_8, streetaddress);
            contentValues.put(DatabaseHelper.COL_9, city);
            contentValues.put(DatabaseHelper.COL_10, state);
            contentValues.put(DatabaseHelper.COL_11, zipcode);
            contentValues.put(DatabaseHelper.COL_12, type_of_user.toString());

            long id = db.insert(DatabaseHelper.TABLE_NAME, null, contentValues);


            Intent intent = new Intent(MainActivity.this,login.class);
            startActivity(intent);


            Toast.makeText(this,"Successfully Registered",Toast.LENGTH_LONG).show();

        }
        else{
            Toast.makeText(this,"You can't register as a Manager/Operator",Toast.LENGTH_SHORT).show();
        }







    }


    public void checkButton(View v){
        int _radioid = _radioGroup.getCheckedRadioButtonId();

        _radioButton = findViewById(_radioid);

        Toast.makeText(this,_radioButton.getText().toString(),Toast.LENGTH_SHORT).show();
    }


}
