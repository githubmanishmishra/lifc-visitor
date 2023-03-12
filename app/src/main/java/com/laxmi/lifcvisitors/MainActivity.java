package com.laxmi.lifcvisitors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
Button btn_employee;
Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_employee = findViewById(R.id.btn_emp_first);
      //  btn_guard = findViewById(R.id.btn_Gaurd_first);
        btn_employee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this, EmployeeRegistratinActivity.class);
                startActivity(intent);
            }
        });
    //    btn_guard = findViewById(R.id.btn_Gaurd_first);
     //   btn_guard.setOnClickListener(new View.OnClickListener() {
          //  @Override
       //     public void onClick(View view) {
         //       intent = new Intent(MainActivity.this,Gaurdreg.class);
           //     startActivity(intent);
            //}
        //});
    }
}