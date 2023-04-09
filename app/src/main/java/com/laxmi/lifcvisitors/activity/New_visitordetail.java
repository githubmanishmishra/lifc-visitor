package com.laxmi.lifcvisitors.activity;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ActionMenuView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.laxmi.lifcvisitors.R;

public class New_visitordetail extends AppCompatActivity {
    Button btn_uploadvisitor_photo;
    ImageView visitorPhoto;
    FloatingActionButton fbDialog;

    RelativeLayout rel_lay;

public static final int CAMERA_REEQUEST_CODE =100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_visitordetail);
    btn_uploadvisitor_photo = findViewById(R.id.btn_uploadphoto);

        fbDialog = findViewById(R.id.floating_btn);
    visitorPhoto = findViewById(R.id.visitor_photo);

        fbDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomEdittext();
            }
        });

    btn_uploadvisitor_photo.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent iCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(iCamera,CAMERA_REEQUEST_CODE);
        }
    });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==RESULT_OK)
        {
            if(requestCode==CAMERA_REEQUEST_CODE)
            //for camera
            {
                Bitmap img = (Bitmap)data.getExtras().get("data");
                visitorPhoto.setImageBitmap(img);
            }
        }
    }

    public void CustomEdittext(){

        RelativeLayout mRlayout = (RelativeLayout) findViewById(R.id.rel_lay);


        RelativeLayout.LayoutParams mRparams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        EditText myEditText = new EditText(this);
        myEditText.setLayoutParams(mRparams);
        mRlayout.addView(myEditText);
    }
}