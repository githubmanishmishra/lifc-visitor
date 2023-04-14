package com.laxmi.lifcvisitors.activity.employee;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.laxmi.lifcvisitors.R;

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
                AlertDialog.Builder deletedialog = new AlertDialog.Builder(Forgetpsw.this);
                deletedialog.setTitle("Alert?");
                deletedialog.setIcon(R.drawable.baseline_notifications_24);
                deletedialog.setMessage("Are you sure want to Proceeds");
                deletedialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(Forgetpsw.this, "Item Deleted", Toast.LENGTH_SHORT).show();
                    }
                });
                deletedialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(Forgetpsw.this, "Item Not Deleted", Toast.LENGTH_SHORT).show();
                    }
                });
                deletedialog.show();
                intent = new Intent(Forgetpsw.this, Employeeotpverification.class);
                startActivity(intent);
            }
        });

        TextView tv = (TextView) this.findViewById(R.id.mywidget);
        tv.setSelected(true);
    }
}