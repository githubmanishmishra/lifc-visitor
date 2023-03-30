package com.laxmi.lifcvisitors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class GaurdLogin extends AppCompatActivity {
    TextView tv_forget;
    Intent intent;
    TextView tv_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaurd_login);
        tv_forget = findViewById(R.id.forgot_pwd_gaurd);
        tv_forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(GaurdLogin.this,EmployeeDashboard.class);
                startActivity(intent);
            }
        });
        tv_login = findViewById(R.id.tv_login);
        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             Intent intents = new Intent(GaurdLogin.this, GuardDashboard.class);
                    startActivity(intents);
            }
        });
    }
}