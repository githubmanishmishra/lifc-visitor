package com.laxmi.lifcvisitors;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Gaurdforgetpswd extends AppCompatActivity {
    Intent intent;
    TextView tv_getotp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaurdforgetpswd);
        tv_getotp = findViewById(R.id.tv_gaurdsgetotp);
        tv_getotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder deletedialog = new AlertDialog.Builder(Gaurdforgetpswd.this);
                deletedialog.setTitle("Alert?");
                deletedialog.setIcon(R.drawable.baseline_notifications_24);
                deletedialog.setMessage("Are you sure want to Proceeds");
                deletedialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Toast.makeText(Gaurdforgetpswd.this, "Item Deleted", Toast.LENGTH_SHORT).show();
                    }
                });
                deletedialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(Gaurdforgetpswd.this, "Item Not Deleted", Toast.LENGTH_SHORT).show();
                    }
                });
                deletedialog.show();
                intent = new Intent(Gaurdforgetpswd.this,Gaurdforgetotp.class);
                startActivity(intent);
            }
        });
    }

}