package com.laxmi.lifcvisitors.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.laxmi.lifcvisitors.R;

public class Employeeotpverification extends AppCompatActivity {
    Intent intent;
    TextView getotp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpverification_forget);
        getotp = findViewById(R.id.tv_getotp);
        getotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(Employeeotpverification.this,Forgetcreatepswemployee.class);
          startActivity(intent);
            }
        });
        TextView   tv = (TextView) this.findViewById(R.id.mywidget);
        tv.setSelected(true);

    }
}