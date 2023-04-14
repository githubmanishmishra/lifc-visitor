package com.laxmi.lifcvisitors.activity.employee;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.laxmi.lifcvisitors.R;

public class EmployeeLogin extends AppCompatActivity {
    TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employeelogin);
        login = findViewById(R.id.tv_login);

        TextView tv = findViewById(R.id.mywidget);
        tv.setSelected(true);
    }
}