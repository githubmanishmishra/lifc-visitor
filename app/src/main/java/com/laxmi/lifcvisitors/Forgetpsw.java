package com.laxmi.lifcvisitors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Forgetpsw extends AppCompatActivity {
    Intent intent;
    TextView tv_getotp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpsw);
tv_getotp = findViewById(R.id.tv_getotp);
tv_getotp.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        intent = new Intent(Forgetpsw.this,Employeeotpverification.class);
        startActivity(intent);
    }
});
    }
}