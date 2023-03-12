package com.laxmi.lifcvisitors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class EmpcreatepswActivity extends AppCompatActivity {
    Intent intent;
    TextView confirmpsw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empcreatepsw);
        confirmpsw = findViewById(R.id.tv_confirmpsw);
        confirmpsw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(EmpcreatepswActivity.this,Employeelogin.class);
                startActivity(intent);
            }
        });

    }
}