package com.laxmi.lifcvisitors;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.laxmi.lifcvisitors.activity.visitors.Visitorrequestcome_to_emplpyee;
import com.laxmi.lifcvisitors.floor_confrence.MailAdapter;
import com.laxmi.lifcvisitors.retrofitservices.APIService;
import com.laxmi.lifcvisitors.retrofitservices.ApiClient;
import com.laxmi.lifcvisitors.retrofitservices.VisitorsByEmployee;
import com.laxmi.lifcvisitors.savedata.PrefConfig;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Employee_Send_Request_toGaurd extends AppCompatActivity
        implements SwipeRefreshLayout.OnRefreshListener {

    RecyclerView mRecyclerView;
    List<VisitorsByEmployee.Data> visitorsByEmployeeList = new ArrayList<>();
    PrefConfig prefConfig;
    ProgressDialog pDialog;
    String userId, nameOne, nameTwo, nameThree, name, purposeOfMetting, image, mobileNo, Status;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_send_request_to_gaurd);
        ImageView iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        prefConfig = new PrefConfig(this);

        progressDialogInitialisaton();

        //SwipeRefrshLayout
        swipeRefreshLayout = findViewById(R.id.swipeContainer);

        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.pink,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        mRecyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(Employee_Send_Request_toGaurd.this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(Employee_Send_Request_toGaurd.this, DividerItemDecoration.VERTICAL));
        mLinearLayoutManager.setReverseLayout(true);
        mLinearLayoutManager.setStackFromEnd(true);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        getVisitorsByGuardList();
        TextView tv = (TextView) this.findViewById(R.id.mywidget);
        tv.setSelected(true);

        swipeRefreshLayout.post(new Runnable() {

            @Override
            public void run() {

                swipeRefreshLayout.setRefreshing(true);

                getVisitorsByGuardList();


            }
        });
    }

    private void getVisitorsByGuardList() {

        swipeRefreshLayout.setRefreshing(false);
        APIService service = ApiClient.getClient().create(APIService.class);
        Log.d("tokensdfdf", prefConfig.readToken());
        Call<VisitorsByEmployee> call = service.getVisitorsByEmployee("Bearer " + prefConfig.readToken());
        call.enqueue(new Callback<VisitorsByEmployee>() {
            @Override
            public void onResponse(Call<VisitorsByEmployee> call, Response<VisitorsByEmployee> response) {
                final VisitorsByEmployee allEvent = response.body();
                if (allEvent != null) {
                    for (int i = 0; i < allEvent.getData().size(); i++) {
                        visitorsByEmployeeList = allEvent.getData();
                    }
                }

                MailAdapter mMailAdapter = new MailAdapter(Employee_Send_Request_toGaurd.this, visitorsByEmployeeList);
                mRecyclerView.setAdapter(mMailAdapter);
                pDialog.dismiss();
                mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(),
                        mRecyclerView, new RecyclerTouchListener.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {

                        VisitorsByEmployee.Data newArrival = visitorsByEmployeeList.get(position);

                       /* userId = String.valueOf(visitorsByEmployeeList.get(i).getId());
                        nameOne = visitorsByEmployeeList.get(i).getNameOne();
                        nameTwo = visitorsByEmployeeList.get(i).getNameTwo();
                        nameThree = visitorsByEmployeeList.get(i).getNameThree();
                        name = visitorsByEmployeeList.get(i).getName();
                        purposeOfMetting = visitorsByEmployeeList.get(i).getPurposeOfComing();
                        image = visitorsByEmployeeList.get(i).getImage();
                        mobileNo = visitorsByEmployeeList.get(i).getMobileNumber();
                        Status = visitorsByEmployeeList.get(i).getStatus();*/

                        Intent mIntent = new Intent(Employee_Send_Request_toGaurd.this, Visitorrequestcome_to_emplpyee.class);
                        mIntent.putExtra("visitorId", newArrival.getId());
                        mIntent.putExtra("VisitorName", newArrival.getName());
                        mIntent.putExtra("VisitorOne", newArrival.getNameOne());
                        mIntent.putExtra("VisitorTwo", newArrival.getNameTwo());
                        mIntent.putExtra("VisitorThree", newArrival.getNameThree());
                        mIntent.putExtra("Purpose", newArrival.getPurposeOfComing());
                        mIntent.putExtra("MobileNo", newArrival.getMobileNumber());
                        mIntent.putExtra("UserImage", newArrival.getImage());
                        mIntent.putExtra("Status", newArrival.getStatus());
                        startActivity(mIntent);
                    }

                    @Override
                    public void onLongClick(View view, int position) {

                    }
                }));

            }

            @Override
            public void onFailure(Call<VisitorsByEmployee> call, Throwable t) {

                pDialog.dismiss();
            }
        });

    }

    private void progressDialogInitialisaton() {

        pDialog = new ProgressDialog(Employee_Send_Request_toGaurd.this);
        pDialog.setMessage("Loading Data Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
    }

    @Override
    public void onRefresh() {
        getVisitorsByGuardList();

    }
}
