package com.laxmi.lifcvisitors.activity.employee;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.laxmi.lifcvisitors.R;

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
                intent = new Intent(EmpcreatepswActivity.this, EmployeeLogin.class);
                startActivity(intent);
            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView   tv = (TextView) this.findViewById(R.id.mywidget);
        tv.setSelected(true);
    }
}