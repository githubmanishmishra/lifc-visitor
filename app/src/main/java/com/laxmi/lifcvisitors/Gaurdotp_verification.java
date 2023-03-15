package com.laxmi.lifcvisitors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Gaurdotp_verification extends AppCompatActivity {
    Intent intent;
    TextView tv_getotp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaurdotp_verification);
        tv_getotp = findViewById(R.id.tv_gaurd_get_otp);
        tv_getotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(Gaurdotp_verification.this,Gaurd_createpswd.class);
                startActivity(intent);

            }
        });

        TextView  tv = (TextView) this.findViewById(R.id.mywidget);
        tv.setSelected(true);}
}