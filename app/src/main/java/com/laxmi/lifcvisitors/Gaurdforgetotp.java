package com.laxmi.lifcvisitors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Gaurdforgetotp extends AppCompatActivity {
Intent intent;
TextView tvgforgetotp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaurdforgetotp);
        tvgforgetotp = findViewById(R.id.tv_gaurd_verify);
        tvgforgetotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(Gaurdforgetotp.this,Gaurdchangepassword.class);
                startActivity(intent);
            }
        });
    }
}