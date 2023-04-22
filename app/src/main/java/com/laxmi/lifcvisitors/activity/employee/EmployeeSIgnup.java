package com.laxmi.lifcvisitors.activity.employee;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.laxmi.lifcvisitors.R;
import com.laxmi.lifcvisitors.model.Branches;
import com.laxmi.lifcvisitors.model.Departments;
import com.laxmi.lifcvisitors.retrofitservices.APIService;
import com.laxmi.lifcvisitors.retrofitservices.ApiClient;
import com.laxmi.lifcvisitors.savedata.PrefConfig;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class EmployeeSIgnup extends AppCompatActivity {
    List<String> listDepartment = new ArrayList<>();
    List<String> listBranches = new ArrayList<>();
    Spinner  spinner_department, spinner_branches;
    PrefConfig prefConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_signup);
        prefConfig = new PrefConfig(this);
        Log.d("token>>>>>>>M", prefConfig.readToken());
        spinner_department = findViewById(R.id.spinner_department);
        spinner_branches = findViewById(R.id.spinner_branches);
        TextView tv = this.findViewById(R.id.mywidget);
        tv.setSelected(true);
        getDepartments();
        getBranches();


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

                        List<Departments.Data> dataList = response.body().getData();
                        for (int i = 0; i < dataList.size(); i++) {

                            Log.d("kjxngksjnkjsdn", dataList.toString());
                            HashSet<String> hashSet = new HashSet<String>();
                            hashSet.addAll(listDepartment);
                            listDepartment.clear();
                            listDepartment.addAll(hashSet);
                            listDepartment.add(dataList.get(i).getDepartmentName());
                        }
                        //Creating the ArrayAdapter instance having the country list
                        ArrayAdapter aa = new ArrayAdapter(EmployeeSIgnup.this, android.R.layout.simple_spinner_item, listDepartment);
                        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Setting the ArrayAdapter data on the Spinner
                        spinner_department.setAdapter(aa);
                    }
                }
                else {
                    Toast.makeText(EmployeeSIgnup.this, "Wrong Credentials", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(@NonNull Call<Departments> call, @NonNull Throwable t) {

                // pDialog.dismiss();
                Log.d("Errorerrrr", t.getMessage());
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
                        List<Branches.Data> dataList = response.body().getData();
                        for (int i = 0; i < dataList.size(); i++) {
                            Log.d("kjxngksjnkjsdn", dataList.toString());
                            HashSet<String> hashSet = new HashSet<String>();
                            hashSet.addAll(listBranches);
                            listBranches.clear();
                            listBranches.addAll(hashSet);
                            listBranches.add(dataList.get(i).getBranchName());

                        }
                        //Creating the ArrayAdapter instance having the country list
                        ArrayAdapter aa = new ArrayAdapter(EmployeeSIgnup.this, android.R.layout.simple_spinner_item, listBranches);
                        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Setting the ArrayAdapter data on the Spinner
                        spinner_branches.setAdapter(aa);
                    }
                }
                else
                {
                    Toast.makeText(EmployeeSIgnup.this, "Wrong Credentials", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(@NonNull Call<Branches> call, @NonNull Throwable t) {

                // pDialog.dismiss();
                Log.d("Error", t.getMessage());
            }
        });
    }
}

