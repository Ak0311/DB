package com.example.dbinsertonly;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button addButton;
    Button showAllButton;

    DatabaseHelper dbHelper;

    EditText nameEditText;
    EditText phoneEditText;
    EditText accountEditText;

    //      Variables to store user inputted data.
    String nameMain;
    String phoneMain;
    String accountMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this); //      Instantiating DatabaseHelper Class with context.

        addButton = findViewById(R.id.addButton);
        showAllButton = findViewById(R.id.showAllButton);

        nameEditText = findViewById(R.id.nameEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        accountEditText = findViewById(R.id.accountEditText);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nameMain = nameEditText.getText().toString();
                phoneMain = phoneEditText.getText().toString();
                accountMain = accountEditText.getText().toString();

                if (nameMain.isEmpty() || phoneMain.isEmpty() || accountMain.isEmpty()  ) {
                    Toast.makeText(MainActivity.this, "Data Insufficient", Toast.LENGTH_SHORT).show();
                } else {

                    boolean verify = dbHelper.insertData(nameMain, phoneMain, accountMain);

                    if (verify)
                        Toast.makeText(MainActivity.this, "Data Added", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(MainActivity.this, "Data already exists", Toast.LENGTH_SHORT).show();

                    nameEditText.getText().clear();
                    phoneEditText.getText().clear();
                    accountEditText.getText().clear();

                }
            }
        });

        showAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,ShowAll.class);
                startActivity(intent);

            }
        });



    }
}
