package com.laxmi.lifcvisitors.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.laxmi.lifcvisitors.R;

import java.util.ArrayList;

public class Visitorrequestcome_to_emplpyee extends AppCompatActivity {
    Spinner Floor;
    Spinner spinner_Conference;
    Button btn_approve,btn_disapprove;
    ArrayList<String> arrFlor = new ArrayList<>();
    ArrayList<String> arrConference = new ArrayList<>();
    boolean isVisible = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitorrequestcome_to_emplpyee);
        Floor = findViewById(R.id.spinner_Floor);
        spinner_Conference = findViewById(R.id.spinner_conference);
        arrFlor.add("Basement");
        arrFlor.add("First Floor");
        arrFlor.add("Second Floor");
        arrFlor.add("Third Floor");
        arrFlor.add("Third Floor New Site1");
        arrFlor.add("Fourth Floor");
        arrFlor.add("Roshan Tower Office Unit-3");
        ArrayAdapter<String> springAdapter_Floor = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, arrFlor);
        Floor.setAdapter(springAdapter_Floor);
        arrConference.add("Albert Hall");
        arrConference.add("Jai Garh");
        arrConference.add("City Place");
        arrConference.add("Red Fort");
        arrConference.add("Nahar Garh");
        arrConference.add("Albert Hall");
        arrConference.add("Amer Fort");
        arrConference.add("Hawamahal");
        arrConference.add("Cabin");
        ArrayAdapter<String> springAdapter_Conference = new ArrayAdapter<>(this , android.R.layout.simple_dropdown_item_1line,arrConference);
spinner_Conference.setAdapter(springAdapter_Conference);
btn_disapprove = findViewById(R.id.btn_disapprove);
        EditText ev_disapprove = findViewById(R.id.ev_disapprove);
btn_disapprove.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
if (v.getId() == R.id.btn_disapprove){
    if(!isVisible)
    {
        ev_disapprove.setVisibility(View.VISIBLE);
        isVisible = true;
    }
    else
    {
        ev_disapprove.setVisibility(View.GONE);
        isVisible =false;
    }
       }
    }
});
    }
}