package com.laxmi.lifcvisitors.activity.officeboy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.laxmi.lifcvisitors.R;
import com.laxmi.lifcvisitors.activity.guard.GuardDashboard;

public class Office_boyassign extends AppCompatActivity {

    TextInputEditText ev_officeBoy_name;
    TextInputEditText mobile_no;
    
    TextView tv_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_office_boyassign);

        ev_officeBoy_name = findViewById(R.id.ev_officeBoy_name);
        mobile_no = findViewById(R.id.mobile_no);
        
        tv_submit = findViewById(R.id.tv_submit);

        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  getOfficeBoyAssignApi();
                Toast.makeText(Office_boyassign.this, "Office Boy Assigned", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Office_boyassign.this, GuardDashboard.class));
                finish();
            }
        });
    }

    private void getOfficeBoyAssignApi() {
    }
}