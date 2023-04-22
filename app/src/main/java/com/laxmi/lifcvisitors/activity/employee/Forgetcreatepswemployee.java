package com.laxmi.lifcvisitors.activity.employee;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.laxmi.lifcvisitors.R;

public class Forgetcreatepswemployee extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetcreatepswemployee);
      ImageView iv_back = findViewById(R.id.iv_back);
      iv_back.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              finish();
          }
      });
        TextView tv = (TextView) this.findViewById(R.id.mywidget);
        tv.setSelected(true);
        TextView changePsw = findViewById(R.id.tv_changepsw);
        changePsw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Forgetcreatepswemployee.this,EmployeeLogin.class);
                startActivity(intent);
            }
        });
    }
}