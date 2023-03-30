package com.laxmi.lifcvisitors;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
public class New_visitordetail extends AppCompatActivity {
    Button btn_uploadvisitor_photo;
    ImageView visitorPhoto;
public static final int CAMERA_REEQUEST_CODE =100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_visitordetail);
    btn_uploadvisitor_photo = findViewById(R.id.btn_uploadphoto);
    visitorPhoto = findViewById(R.id.visitor_photo);
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
}