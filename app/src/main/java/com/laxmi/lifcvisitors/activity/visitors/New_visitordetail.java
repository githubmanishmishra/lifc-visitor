package com.laxmi.lifcvisitors.activity.visitors;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.laxmi.lifcvisitors.Employee_Send_Request_toGaurd;
import com.laxmi.lifcvisitors.R;
import com.laxmi.lifcvisitors.model.PostCodalStateAndCity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class New_visitordetail extends AppCompatActivity {
    Button btn_uploadvisitor_photo;
    ImageView visitorPhoto;
    AppCompatButton fbDialog;

    TextView rv_log;
    LinearLayout linearLayout;
    public boolean checkHide = false;
    List<PostCodalStateAndCity> postOfficeArrayList = new ArrayList<>();

    public static final int CAMERA_REEQUEST_CODE = 100;

    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private String url = "https://api.postalpincode.in/pincode/721429";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_visitordetail);
        btn_uploadvisitor_photo = findViewById(R.id.btn_uploadphoto);

        fbDialog = findViewById(R.id.floating_btn);
        visitorPhoto = findViewById(R.id.visitor_photo);
        linearLayout = findViewById(R.id.editTextContainer);
        EditText pin_code = findViewById(R.id.pin_code);

        if (pin_code.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please Enter Pin code", Toast.LENGTH_SHORT).show();
            getState(pin_code.getText().toString());
        } else {
            getState(pin_code.getText().toString());
        }


        fbDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (checkHide) {
                    checkHide = false;
                    linearLayout.setVisibility(View.GONE);

                } else {
                    checkHide = true;
                    linearLayout.setVisibility(View.VISIBLE);
                }

            }
        });

        btn_uploadvisitor_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(iCamera, CAMERA_REEQUEST_CODE);
            }
        });

        rv_log = findViewById(R.id.sendrequest);
        rv_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(New_visitordetail.this, Employee_Send_Request_toGaurd.class);
                startActivity(intent);
            }
        });
        TextView tv = this.findViewById(R.id.mywidget);
        tv.setSelected(true);

    }

    private void getState(String pinCode) {

        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this);

        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, url, response -> {

            Toast.makeText(getApplicationContext(), "Response :" + response, Toast.LENGTH_LONG).show();//display the response on screen
            Log.i("Manish Mishra", response);

            if (response != null) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject returnFlightChild = null;
                    for (int j = 0; j < jsonArray.length(); j++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(j);
                        JSONArray onwardflights = jsonObject.getJSONArray("PostOffice");
                        for (int i = 0; i < onwardflights.length(); i++) {
                            returnFlightChild = onwardflights.getJSONObject(i);
                            //returnFlights objects
                            Log.d("kjxngksjnkjsdn", returnFlightChild.getString("Name"));

                        }

                        List<String> listSpinner = new ArrayList<>();

                        listSpinner.add(returnFlightChild.getString("Name"));

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                                New_visitordetail.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listSpinner);

                        Spinner editTextFilledExposedDropdown = findViewById(R.id.spinner_City);
                        editTextFilledExposedDropdown.setAdapter(adapter);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } else {
                Toast.makeText(New_visitordetail.this, "Wrong Credentials", Toast.LENGTH_SHORT).show();

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.i("Manish", "Error :" + error.toString());
            }
        });

        mRequestQueue.add(mStringRequest);

    }

}