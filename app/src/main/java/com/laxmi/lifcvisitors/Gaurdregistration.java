package com.laxmi.lifcvisitors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Gaurdregistration extends AppCompatActivity {
    Intent intent;
    TextView tv_gaurd_getotp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaurdregistration);
        tv_gaurd_getotp = findViewById(R.id.tv_gaurdgetotp);
        tv_gaurd_getotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(Gaurdregistration.this,Gaurdotp_verification.class);
                startActivity(intent);
            }
        });

      TextView  tv = (TextView) this.findViewById(R.id.mywidget);
        tv.setSelected(true);

    }
}