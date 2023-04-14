package com.laxmi.lifcvisitors.activity.visitors;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.laxmi.lifcvisitors.Employee_Send_Request_toGaurd;
import com.laxmi.lifcvisitors.R;
import com.laxmi.lifcvisitors.model.PostCodalStateAndCity;
import com.laxmi.lifcvisitors.model.PostOffice;
import com.laxmi.lifcvisitors.retrofitservices.APIService;
import com.laxmi.lifcvisitors.retrofitservices.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class New_visitordetail extends AppCompatActivity {
    Button btn_uploadvisitor_photo;
    ImageView visitorPhoto;
    AppCompatButton fbDialog;

    TextView rv_log;
    LinearLayout linearLayout;
    public boolean checkHide = false;
    List<PostOffice> postOfficeArrayList = new ArrayList<>();

    public static final int CAMERA_REEQUEST_CODE = 100;

    AutoCompleteTextView spinner_City;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_visitordetail);
        btn_uploadvisitor_photo = findViewById(R.id.btn_uploadphoto);

        fbDialog = findViewById(R.id.floating_btn);
        visitorPhoto = findViewById(R.id.visitor_photo);
        linearLayout = findViewById(R.id.editTextContainer);
        EditText pin_code = findViewById(R.id.pin_code);
        spinner_City = findViewById(R.id.spinner_City);

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

        APIService service = ApiClient.getClient().create(APIService.class);

        Call<PostCodalStateAndCity> call = service.getPincode("842002");
        call.enqueue(new Callback<PostCodalStateAndCity>() {
            @Override
            public void onResponse(@NonNull Call<PostCodalStateAndCity> call,
                                   @NonNull Response<PostCodalStateAndCity> response) {

                if (response.body() != null) {
                    if (response.body().getStatus().equalsIgnoreCase("Success")) {

                        PostCodalStateAndCity postCodalStateAndCity = response.body();

                      postOfficeArrayList =  postCodalStateAndCity.getPostOffice();

                        Log.d("kjxngksjnkjsdn", postOfficeArrayList.get(0).getBranchType());

                        List<String> listSpinner = new ArrayList<>();
    //                    for (int i = 0; i < postCodalStateAndCity.size(); i++) {
    //                        List<PostCodalStateAndCity.PostOffice> postOfficeList = postOfficeArrayList.get(i).getPostOffice();
    //
    //                        for (int j = 0; j < postOfficeList.size(); j++) {
    //                            listSpinner.add(postOfficeList.get(j).getName());
    //
    //                        }
    //
    //                        Log.d("kjxngksjnkjsdn", listSpinner.toString());
    //                    }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                                New_visitordetail.this, R.layout.dropdown_menu_popup, listSpinner);

                        AutoCompleteTextView editTextFilledExposedDropdown = findViewById(R.id.spinner_City);
                        editTextFilledExposedDropdown.setAdapter(adapter);


                    } else {
                        Toast.makeText(New_visitordetail.this, "Wrong Credentials", Toast.LENGTH_SHORT).show();

                    }
                }

               /* final PostCodalStateAndCity allEvent = response.body();

                postOfficeArrayList = allEvent.getPostOffice();

                List<String> listSpinner = new ArrayList<>();
                for (int i = 0; i < postOfficeArrayList.size(); i++) {
                    listSpinner.add(postOfficeArrayList.get(i).getName());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                        New_visitordetail.this, R.layout.dropdown_menu_popup, listSpinner);

                AutoCompleteTextView editTextFilledExposedDropdown = findViewById(R.id.spinner_City);
                editTextFilledExposedDropdown.setAdapter(adapter);
*/
            }

            @Override
            public void onFailure(Call<PostCodalStateAndCity> call, Throwable t) {

                // pDialog.dismiss();
                //  Log.d("Error", t.getMessage());
            }
        });
    }

}