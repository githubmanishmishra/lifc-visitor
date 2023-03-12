package com.laxmi.lifcvisitors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Employeelogin extends AppCompatActivity {
    Intent intent;
    TextView forgetpwd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employeelogin);
        forgetpwd = findViewById(R.id.forgot_pwd_emp);
        forgetpwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(Employeelogin.this,Forgetpsw.class);
                startActivity(intent);
            }
        });
    }
}