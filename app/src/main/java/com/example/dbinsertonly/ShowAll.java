package com.example.dbinsertonly;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class ShowAll extends AppCompatActivity {

    ListView listShow;
    DatabaseHelper dbHelper;
    Button deleteAll;
    String s;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all);

        dbHelper = new DatabaseHelper(ShowAll.this);

        listShow = findViewById(R.id.listShow);
        deleteAll = findViewById(R.id.deleteAll);

        final CustomAdapter adapter = new CustomAdapter(ShowAll.this,R.layout.details_layout,dbHelper.showData());
        listShow.setAdapter(adapter);

        listShow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Consumer obj = adapter.getItem(position);


                Intent intent = new Intent(ShowAll.this,ConsumerDescAndEdit.class);
                intent.putExtra("NAME_TAG",obj.getName());
                intent.putExtra("PHONE_TAG",obj.getPhoneNumber());
                intent.putExtra("ACCOUNT_TAG",obj.getAccountNumber());
                startActivity(intent);
            }
        });

        deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(ShowAll.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                dbHelper.removeAll();

                finish();
                startActivity(getIntent());
            }
        });

    }
}
