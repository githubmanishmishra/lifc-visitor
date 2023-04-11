package com.laxmi.lifcvisitors.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.laxmi.lifcvisitors.R;

public class Forgetcreatepswemployee extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetcreatepswemployee);
        TextView tv = (TextView) this.findViewById(R.id.mywidget);
        tv.setSelected(true);
    }
}