package com.example.dbinsertonly;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ConsumerDescAndEdit extends AppCompatActivity {

    Button updateButton;

    EditText nameEditTextVar;
    EditText phoneEditTextVar;
    EditText accountEditTextVar;

    //      Variables to store user inputted data.
    String nameEdit;
    String phoneEdit;
    String accountEdit;

    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer_desc_and_edit);

        dbHelper = new DatabaseHelper(this);

        updateButton = findViewById(R.id.updateButton);

        nameEditTextVar = findViewById(R.id.nameEditScreen);
        phoneEditTextVar = findViewById(R.id.phoneNumberEditScreen);
        accountEditTextVar = findViewById(R.id.accountNumberEditScreen);

        nameEditTextVar.setText(getIntent().getStringExtra("NAME_TAG"));
        phoneEditTextVar.setText(getIntent().getStringExtra("PHONE_TAG"));
        accountEditTextVar.setText(getIntent().getStringExtra("ACCOUNT_TAG"));

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                nameEdit = nameEditTextVar.getText().toString();
                phoneEdit = phoneEditTextVar.getText().toString();
                accountEdit = accountEditTextVar.getText().toString();

                if (nameEdit.isEmpty() || phoneEdit.isEmpty() || accountEdit.isEmpty()) {
                    Toast.makeText(ConsumerDescAndEdit.this, "Data Insufficient", Toast.LENGTH_SHORT).show();
                } else {
                    boolean b = dbHelper.updateData(nameEdit, phoneEdit, accountEdit);
                    if (b)
                        Toast.makeText(ConsumerDescAndEdit.this, "Data updated", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(ConsumerDescAndEdit.this, "Could not update data", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

}
