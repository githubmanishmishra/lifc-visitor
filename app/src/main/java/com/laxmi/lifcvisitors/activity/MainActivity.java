package com.laxmi.lifcvisitors.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.laxmi.lifcvisitors.R;
import com.laxmi.lifcvisitors.activity.employee.EmployeeLogin;
import com.laxmi.lifcvisitors.activity.guard.GaurdLogin;


public class MainActivity extends AppCompatActivity {
Button btn_employee;
Intent intent;
TextView tv;
Button btn_guard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_employee = findViewById(R.id.btn_emp_first);
      //  btn_guard = findViewById(R.id.btn_Gaurd_first);
        btn_employee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this, EmployeeLogin.class);
                startActivity(intent);
            }
        });
       btn_guard = findViewById(R.id.btn_Gaurd_first);
        btn_guard.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View view) {
                intent = new Intent(MainActivity.this, GaurdLogin.class);
                startActivity(intent);
            }
        });
        tv = (TextView) this.findViewById(R.id.mywidget);
        tv.setSelected(true);

    }
}