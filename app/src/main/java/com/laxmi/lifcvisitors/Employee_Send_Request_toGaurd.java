package com.laxmi.lifcvisitors;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.laxmi.lifcvisitors.floor_confrence.EmailData;
import com.laxmi.lifcvisitors.floor_confrence.MailAdapter;

import java.util.ArrayList;
import java.util.List;

public class Employee_Send_Request_toGaurd extends AppCompatActivity {

    RecyclerView mRecyclerView;
    List<EmailData> mEmailData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_send_request_to_gaurd);
        mRecyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(Employee_Send_Request_toGaurd.this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(Employee_Send_Request_toGaurd.this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        EmailData mEmail = new EmailData("Sam", "Weekend adventure", "Let's go fishing with John and others. We will do some barbecue and have soo much fun", "10:42am");
        mEmailData.add(mEmail);
        mEmail = new EmailData("Facebook", "James, you have 1 new notification", "A lot has happened on Facebook since", "16:04pm");
        mEmailData.add(mEmail);
        mEmail = new EmailData("Google+", "Top suggested Google+ pages for you", "Top suggested Google+ pages for you", "18:44pm");
        mEmailData.add(mEmail);
        mEmail = new EmailData("Twitter", "Follow T-Mobile, Samsung Mobile U", "James, some people you may know", "20:04pm");
        mEmailData.add(mEmail);
        mEmail = new EmailData("Pinterest Weekly", "Pins you’ll love!", "Have you seen these Pins yet? Pinterest", "09:04am");
        mEmailData.add(mEmail);
        mEmail = new EmailData("Josh", "Going lunch", "Don't forget our lunch at 3PM in Pizza hut", "01:04am");
        mEmailData.add(mEmail);

        MailAdapter mMailAdapter = new MailAdapter(Employee_Send_Request_toGaurd.this, mEmailData);
        mRecyclerView.setAdapter(mMailAdapter);
        TextView tv = (TextView) this.findViewById(R.id.mywidget);
        tv.setSelected(true);

    }
}
