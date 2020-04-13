    package com.example.se_project;

    import androidx.appcompat.app.AppCompatActivity;

    import android.content.Intent;
    import android.database.Cursor;
    import android.database.CursorIndexOutOfBoundsException;
    import android.database.sqlite.SQLiteDatabase;
    import android.database.sqlite.SQLiteOpenHelper;
    import android.os.Bundle;
    import android.provider.ContactsContract;
    import android.util.Log;
    import android.view.View;
    import android.widget.Button;
    import android.widget.EditText;
    import android.widget.Toast;

    public class login extends AppCompatActivity {

        SQLiteDatabase db;
        SQLiteOpenHelper openHelper;

        Button _login,_register;

        EditText _enterUsername,_enterPassword;

        Cursor cursor;



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);

            openHelper = new DatabaseHelper(this);
            db = openHelper.getReadableDatabase();


            _login=(Button)findViewById(R.id.finish_login);
            _register=(Button)findViewById(R.id.go_to_registration_page);

            _enterUsername=(EditText)findViewById(R.id.checkUsername);

            _enterPassword=(EditText)findViewById(R.id.checkPassword);

            _register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(login.this,MainActivity.class);
                    startActivity(intent);
                }
            });



            _login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String username = _enterUsername.getText().toString();

                    String password = _enterPassword.getText().toString();

                    cursor = db.rawQuery("SELECT * FROM "+DatabaseHelper.TABLE_NAME+" WHERE "+ DatabaseHelper.COL_2+" =? AND " + DatabaseHelper.COL_3 +"=?",new String[]{username,password});



                    if(cursor!=null){

                        if(cursor.getCount()>0){
                            cursor.moveToNext();
                            //    String answer = db.rawQuery("SELECT type_of_user FROM "+DatabaseHelper.TABLE_NAME+" WHERE "+ DatabaseHelper.COL_2+" =? AND " + DatabaseHelper.COL_3 +"=?",new String[]{username,password});

                            Toast.makeText(getApplicationContext(),"Login Successful ",Toast.LENGTH_LONG).show();

                            if(cursor.getString(11).equals("User")){
                                Intent intent = new Intent(login.this,User_HomePage.class);
                                intent.putExtra("username",username);

                                startActivity(intent);
                            }

                            if(cursor.getString(11).equals("Manager")){
                                Intent intent = new Intent(login.this,Manager_HomePage.class);
                                intent.putExtra("username",username);


                                startActivity(intent);
                            }

                            if(cursor.getString(11).equals("Operator")){
                                Intent intent = new Intent(login.this,Operator_HomePage.class);
                                intent.putExtra("username",username);


                                startActivity(intent);
                            }


                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_SHORT).show();
                        }
                    }


                }
            });



        }
    }
