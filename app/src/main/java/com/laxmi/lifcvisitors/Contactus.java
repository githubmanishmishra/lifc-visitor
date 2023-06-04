package com.laxmi.lifcvisitors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Contactus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);
     ImageView iv_back = findViewById(R.id.iv_back);
     iv_back.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             finish();
         }
     });
     TextView tollfreeno = findViewById(R.id.toll_free);
     tollfreeno.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent intent = new Intent(Intent.ACTION_DIAL);
             intent.setData(Uri.parse("tel:"+"7503196856"));
             startActivity(intent);
         }
     });
     TextView Contact_email = findViewById(R.id.contact_email);
     Contact_email.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent intent = new Intent(Intent.ACTION_SEND);
             intent.setType("plain/text");
             intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "ithelpdesk@lifc.in" });
             intent.putExtra(Intent.EXTRA_SUBJECT, "subject");
             intent.putExtra(Intent.EXTRA_TEXT, "mail body");
             startActivity(Intent.createChooser(intent, ""));

         }
     });
        TextView tv = (TextView) this.findViewById(R.id.mywidget);
        tv.setSelected(true);

    }
}