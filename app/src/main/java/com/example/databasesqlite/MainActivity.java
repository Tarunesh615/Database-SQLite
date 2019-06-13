package com.example.databasesqlite;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnSignIn,buttonSignUp;
    LoginDataBaseAdapter loginDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create a instance of SQLite Databse
        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();

        //Get the Refferences of Buttons
        btnSignIn=(Button)findViewById(R.id.buttonSIGNIN);
        buttonSignUp=(Button)findViewById(R.id.buttonSignUP);

        //Set OnClick Listener on SignUp button
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Auto-generated method stub

                ///Create Intent for SignUpActivity abd Start The Activity
                Intent intentSignUP=new Intent(getApplicationContext(),
                        SignUPActivity.class);
                startActivity(intentSignUP);
            }
        });
    }
    public void signIn(View V){
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.login);
        dialog.setTitle("Login");

        final EditText editTextUserName=(EditText)dialog.
                findViewById(R.id.editTextUserNameToLogin);
        final EditText editTextPassword=(EditText)dialog.
                findViewById(R.id.editTextPasswordToLogin);

        Button btnSignIn=(Button)dialog.findViewById(R.id.buttonSignIn);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName=editTextUserName.
                        getText().toString();
                String password=editTextPassword.getText().toString();

                String storedPassword=loginDataBaseAdapter.getSingleEntry(userName);

                if (password.equals(storedPassword)){
                    Intent in=new Intent(MainActivity.this,LoggedData.class);
                    in.putExtra("name",userName);
                    in.putExtra("password",password);
                    startActivity(in);

                    Toast.makeText(MainActivity.this,"Congrats: Login Successfully",
                            Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                }
                else{
                    Toast.makeText(MainActivity.this,"User Name or Password does not match",Toast.LENGTH_LONG).show();
                }
            }
        });
        dialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Close the Database
        loginDataBaseAdapter.close();
    }
}
