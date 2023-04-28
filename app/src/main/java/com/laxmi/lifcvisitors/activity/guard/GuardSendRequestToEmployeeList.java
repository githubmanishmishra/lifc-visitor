package com.laxmi.lifcvisitors.activity.guard;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.laxmi.lifcvisitors.Employee_Send_Request_toGaurd;
import com.laxmi.lifcvisitors.R;
import com.laxmi.lifcvisitors.RecyclerTouchListener;
import com.laxmi.lifcvisitors.activity.visitors.Visitorrequestcome_to_emplpyee;
import com.laxmi.lifcvisitors.floor_confrence.DetailActivity;
import com.laxmi.lifcvisitors.floor_confrence.MailAdapter;
import com.laxmi.lifcvisitors.floor_confrence.MailAdapterGuard;
import com.laxmi.lifcvisitors.model.VisitorsByGuard;
import com.laxmi.lifcvisitors.retrofitservices.APIService;
import com.laxmi.lifcvisitors.retrofitservices.ApiClient;
import com.laxmi.lifcvisitors.retrofitservices.VisitorsByEmployee;
import com.laxmi.lifcvisitors.savedata.PrefConfig;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GuardSendRequestToEmployeeList extends AppCompatActivity {

    RecyclerView mRecyclerView;
    List<VisitorsByGuard.Data> VisitorsByGuardList = new ArrayList<>();
    PrefConfig prefConfig;
    ProgressDialog pDialog;

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

        mRecyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(GuardSendRequestToEmployeeList.this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(GuardSendRequestToEmployeeList.this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        //  progressDialogInitialisaton();
        getVisitorsByGuardList();

        TextView tv = (TextView) this.findViewById(R.id.mywidget);
        tv.setSelected(true);

    }

    private void getVisitorsByGuardList() {

        APIService service = ApiClient.getClient().create(APIService.class);

        Call<VisitorsByGuard> call = service.getVisitorsByGuard("Bearer " + prefConfig.readToken());
        call.enqueue(new Callback<VisitorsByGuard>() {
            @Override
            public void onResponse(Call<VisitorsByGuard> call, Response<VisitorsByGuard> response) {

                final VisitorsByGuard allEvent = response.body();

                if (allEvent != null) {
                    for (int i = 0; i < allEvent.getData().size(); i++) {
                        VisitorsByGuardList = allEvent.getData();
                    }
                }


                if (VisitorsByGuardList != null) {

//                    pDialog.dismiss();

                }

                MailAdapterGuard mMailAdapter = new MailAdapterGuard(GuardSendRequestToEmployeeList.this, VisitorsByGuardList);
                mRecyclerView.setAdapter(mMailAdapter);

                mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(),
                        mRecyclerView, new RecyclerTouchListener.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {

                        VisitorsByGuard.Data newArrival = VisitorsByGuardList.get(position);

                       /* userId = String.valueOf(visitorsByEmployeeList.get(i).getId());
                        nameOne = visitorsByEmployeeList.get(i).getNameOne();
                        nameTwo = visitorsByEmployeeList.get(i).getNameTwo();
                        nameThree = visitorsByEmployeeList.get(i).getNameThree();
                        name = visitorsByEmployeeList.get(i).getName();
                        purposeOfMetting = visitorsByEmployeeList.get(i).getPurposeOfComing();
                        image = visitorsByEmployeeList.get(i).getImage();
                        mobileNo = visitorsByEmployeeList.get(i).getMobileNumber();
                        Status = visitorsByEmployeeList.get(i).getStatus();*/

                      /*  Intent mIntent = new Intent(GuardSendRequestToEmployeeList.this, DetailActivity.class);
                        mIntent.putExtra("visitorId", newArrival.getId());
                        mIntent.putExtra("VisitorName", newArrival.getName());
                        mIntent.putExtra("VisitorOne", newArrival.getNameOne());
                        mIntent.putExtra("VisitorTwo", newArrival.getNameTwo());
                        mIntent.putExtra("VisitorThree", newArrival.getNameThree());
                        mIntent.putExtra("Purpose", newArrival.getPurposeOfComing());
                        mIntent.putExtra("MobileNo", newArrival.getMobileNumber());
                        mIntent.putExtra("UserImage", newArrival.getImage());
                        mIntent.putExtra("Status", newArrival.getStatus());
                        startActivity(mIntent);*/




                    }

                    @Override
                    public void onLongClick(View view, int position) {

                    }
                }));

            }

            @Override
            public void onFailure(Call<VisitorsByGuard> call, Throwable t) {

                //  pDialog.dismiss();
            }
        });


    }

    private void progressDialogInitialisaton() {

//        pDialog = new ProgressDialog(getApplicationContext());
//        pDialog.setMessage("Loading Data Please wait...");
//        pDialog.setIndeterminate(false);
//        pDialog.setCancelable(false);
//        pDialog.show();
    }
}
