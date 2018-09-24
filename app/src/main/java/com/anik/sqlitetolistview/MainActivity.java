package com.anik.sqlitetolistview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText name, loc, desig;
    Button saveBtn;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText)findViewById(R.id.txtName);
        loc = (EditText)findViewById(R.id.txtLocation);
        desig = (EditText)findViewById(R.id.txtDesignation);
        saveBtn = (Button)findViewById(R.id.btnSave);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = name.getText().toString();
                String location = loc.getText().toString();
                String designation = desig.getText().toString();

                if (username.isEmpty())
                {
                    name.setError("Please enter name!");
                    name.requestFocus();
                }
               else if (location.isEmpty())
                {
                    loc.setError("Please enter location!");
                    loc.requestFocus();
                }
               else if (designation.isEmpty())
                {
                    desig.setError("Please enter designation!");
                    desig.requestFocus();
                }
                else {
                    DbHandler dbHandler = new DbHandler(MainActivity.this);
                    dbHandler.insertUserDetails(username, location, designation);
                    intent = new Intent(MainActivity.this, DetailsActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Details Inserted Successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}