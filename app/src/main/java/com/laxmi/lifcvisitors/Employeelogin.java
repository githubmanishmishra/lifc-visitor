package com.laxmi.lifcvisitors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Employeelogin extends AppCompatActivity {
    Intent intent;
    TextView forgetpwd;
    TextView login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employeelogin);
        forgetpwd = findViewById(R.id.forgot_pwd_emp);
        login = findViewById(R.id.tv_login);
        forgetpwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(Employeelogin.this,Forgetpsw.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(Employeelogin.this,EmployeeDashboard.class);
                startActivity(intent);
            }
        });

        TextView   tv = (TextView) this.findViewById(R.id.mywidget);
        tv.setSelected(true);
    }
}