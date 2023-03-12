package com.laxmi.lifcvisitors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Otpverification extends AppCompatActivity {
Intent intents;
TextView tv_getotp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpverification);
        tv_getotp = findViewById(R.id.get_otp);
        tv_getotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
          intents = new Intent(Otpverification.this,EmpcreatepswActivity.class);
          startActivity(intents);
            }
        });
    }
}