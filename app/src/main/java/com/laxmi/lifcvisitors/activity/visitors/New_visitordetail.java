package com.laxmi.lifcvisitors.activity.visitors;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
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
import com.laxmi.lifcvisitors.model.Branches;
import com.laxmi.lifcvisitors.model.Departments;
import com.laxmi.lifcvisitors.retrofitservices.APIService;
import com.laxmi.lifcvisitors.retrofitservices.ApiClient;
import com.laxmi.lifcvisitors.savedata.PrefConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class New_visitordetail extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Button btn_uploadvisitor_photo;
    ImageView visitorPhoto;
    AppCompatButton fbDialog;

    TextView rv_log;
    LinearLayout linearLayout;
    public boolean checkHide = false;

    public static final int CAMERA_REEQUEST_CODE = 100;

    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private final String url = "https://api.postalpincode.in/pincode";
    Spinner spinner, spinner_department,spinner_branches;
    TextView tv_spinner_state;
    List<String> listSpinner = new ArrayList<>();
    List<String> listDepartment = new ArrayList<>();
    List<String> listBranches = new ArrayList<>();
    AppCompatButton btnSubmit;
    PrefConfig prefConfig;

    List<Departments> departmentsList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_visitordetail);

        prefConfig = new PrefConfig(this);

        Log.d("token>>>>>>>M", prefConfig.readToken());


        btn_uploadvisitor_photo = findViewById(R.id.btn_uploadphoto);

        fbDialog = findViewById(R.id.floating_btn);
        visitorPhoto = findViewById(R.id.visitor_photo);
        linearLayout = findViewById(R.id.editTextContainer);
        EditText pin_code = findViewById(R.id.pin_code);
        tv_spinner_state = findViewById(R.id.tv_spinner_state);
        spinner = findViewById(R.id.spinner_city);
        spinner_department = findViewById(R.id.spinner_department);
        spinner_branches = findViewById(R.id.spinner_branches);
        btnSubmit = findViewById(R.id.btn_submiit);
        spinner.setOnItemSelectedListener(this);
        btnSubmit.setOnClickListener(view -> {
            if (pin_code.getText().toString().isEmpty() &&
                    pin_code.getText().toString().length() != 6) {
                Toast.makeText(New_visitordetail.this, "Enter Six Digit Pin code", Toast.LENGTH_SHORT).show();
                if (tv_spinner_state.getText().toString().isEmpty()) {
                    Toast.makeText(New_visitordetail.this, "Enter Six Digit Pin code", Toast.LENGTH_SHORT).show();
                }
            } else {
                getState(pin_code.getText().toString());
            }
        });

        fbDialog.setOnClickListener(view -> {

            if (checkHide) {
                checkHide = false;
                linearLayout.setVisibility(View.GONE);

            } else {
                checkHide = true;
                linearLayout.setVisibility(View.VISIBLE);
            }

        });

        btn_uploadvisitor_photo.setOnClickListener(view -> {
            Intent iCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(iCamera, CAMERA_REEQUEST_CODE);
        });

        rv_log = findViewById(R.id.sendrequest);
        rv_log.setOnClickListener(view -> {
            Intent intent = new Intent(New_visitordetail.this, Employee_Send_Request_toGaurd.class);
            startActivity(intent);
        });
        TextView tv = this.findViewById(R.id.mywidget);
        tv.setSelected(true);

        getDepartments();
        getBranches();

    }

    private void getState(String pinCode) {

        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this);

        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, url + "/" + pinCode, response -> {

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
                            Log.d("kjxngksjnkjsdn", returnFlightChild.getString("Circle"));
                            listSpinner.add(returnFlightChild.getString("Name"));
                            tv_spinner_state.setText(returnFlightChild.getString("Circle"));

                        }
                        //Creating the ArrayAdapter instance having the country list  
                        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listSpinner);
                        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Setting the ArrayAdapter data on the Spinner  
                        spinner.setAdapter(aa);
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

    private void getDepartments() {
        APIService service = ApiClient.getClient().create(APIService.class);
        Call<Departments> call = service.getDepartments("Bearer " + prefConfig.readToken());
        call.enqueue(new Callback<Departments>() {
            @Override
            public void onResponse(@NonNull Call<Departments> call, @NonNull retrofit2.Response<Departments> response) {

                if (response.body() != null) {
                    if (response.body().getMessage().equalsIgnoreCase("Departments List")) {

                        Log.d("Departmentsssss", "" + response.body().getData());

                        List<Departments.Data> dataList  = response.body().getData();

                        for (int i = 0; i < dataList.size(); i++) {

                            Log.d("kjxngksjnkjsdn", dataList.toString());

                            HashSet<String> hashSet = new HashSet<String>();
                            hashSet.addAll(listDepartment);
                            listDepartment.clear();
                            listDepartment.addAll(hashSet);

                            listDepartment.add(dataList.get(i).getDepartmentName());

                        }
                        //Creating the ArrayAdapter instance having the country list
                        ArrayAdapter aa = new ArrayAdapter(New_visitordetail.this, android.R.layout.simple_spinner_item, listDepartment);
                        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Setting the ArrayAdapter data on the Spinner
                        spinner_department.setAdapter(aa);


                    }
                } else {
                    Toast.makeText(New_visitordetail.this, "Wrong Credentials", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(@NonNull Call<Departments> call, @NonNull Throwable t) {

                // pDialog.dismiss();
                Log.d("Error", t.getMessage());
            }
        });
    }

    private void getBranches() {
        APIService service = ApiClient.getClient().create(APIService.class);
        Call<Branches> call = service.getBranches("Bearer " + prefConfig.readToken());
        call.enqueue(new Callback<Branches>() {
            @Override
            public void onResponse(@NonNull Call<Branches> call, @NonNull retrofit2.Response<Branches> response) {

                if (response.body() != null) {
                    if (response.body().getMessage().equalsIgnoreCase("Branches List")) {

                        Log.d("Branchessssss", "" + response.body().getData());

                        List<Branches.Data> dataList  = response.body().getData();

                        for (int i = 0; i < dataList.size(); i++) {

                            Log.d("kjxngksjnkjsdn", dataList.toString());

                            HashSet<String> hashSet = new HashSet<String>();
                            hashSet.addAll(listBranches);
                            listBranches.clear();
                            listBranches.addAll(hashSet);

                            listBranches.add(dataList.get(i).getBranchName());

                        }
                        //Creating the ArrayAdapter instance having the country list
                        ArrayAdapter aa = new ArrayAdapter(New_visitordetail.this, android.R.layout.simple_spinner_item, listBranches);
                        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Setting the ArrayAdapter data on the Spinner
                        spinner_branches.setAdapter(aa);


                    }
                } else {
                    Toast.makeText(New_visitordetail.this, "Wrong Credentials", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(@NonNull Call<Branches> call, @NonNull Throwable t) {

                // pDialog.dismiss();
                Log.d("Error", t.getMessage());
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}