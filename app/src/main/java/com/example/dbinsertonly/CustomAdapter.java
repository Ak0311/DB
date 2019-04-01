package com.example.dbinsertonly;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class CustomAdapter extends BaseAdapter {

    Context mContext;

    TextView nameView;
    TextView phoneNumberView;
    TextView accountView;

    String name;
    String phoneNumber;
    String accountNumber;

    ArrayList<Consumer> objects;


    public CustomAdapter(Context context, int resource, ArrayList<Consumer> objects){

        this.objects = objects;
        this.mContext = context;

    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Consumer getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Consumer consumer = (Consumer) getItem(position);

        // Values to be displayed.

        name = consumer.getName();
        phoneNumber = consumer.getPhoneNumber();
        accountNumber = consumer.getAccountNumber();

        LayoutInflater inflater = LayoutInflater.from(mContext); // generate an inflater using context.
        convertView = inflater.inflate(R.layout.details_layout,null); // inflate details_layout and store it in convertView.

        nameView = convertView.findViewById(R.id.nameView);
        phoneNumberView = convertView.findViewById(R.id.phoneNumberView);
        accountView = convertView.findViewById(R.id.accountView);

        nameView.setText(name);
        phoneNumberView.setText(phoneNumber);
        accountView.setText(accountNumber);

        return convertView;
    }

}
