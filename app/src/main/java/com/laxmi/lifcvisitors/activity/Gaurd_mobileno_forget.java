package com.laxmi.lifcvisitors.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.laxmi.lifcvisitors.R;

public class Gaurd_mobileno_forget extends AppCompatActivity {
    TextView mobileNo;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaurd_mobileno_forget);
    intent = new Intent(Gaurd_mobileno_forget.this,Gaurdforgetotp.class);
    startActivity(intent);
    }
}