package com.example.databasesqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class LoggedData extends AppCompatActivity {
    TextView muser_name,mpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_data);

        muser_name=(TextView)findViewById(R.id.tv_username);
        mpassword=(TextView)findViewById(R.id.tv_password);

        Intent in=getIntent();
        muser_name.setText(in.getStringExtra("name"));
        mpassword.setText(in.getStringExtra("password"));
    }
}
