package com.laxmi.lifcvisitors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Gaurdforgetpswd extends AppCompatActivity {
    Intent intent;
    TextView tv_getotp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaurdforgetpswd);
        tv_getotp = findViewById(R.id.tv_gaurdsgetotp);
        tv_getotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(Gaurdforgetpswd.this,Gaurdforgetotp.class);
                startActivity(intent);
            }
        });
    }

}