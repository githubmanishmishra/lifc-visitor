package com.laxmi.lifcvisitors;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.laxmi.lifcvisitors.floor_confrence.EmailData;
import com.laxmi.lifcvisitors.floor_confrence.MailAdapter;
import com.laxmi.lifcvisitors.model.VisitorsByGuard;
import com.laxmi.lifcvisitors.retrofitservices.APIService;
import com.laxmi.lifcvisitors.retrofitservices.ApiClient;
import com.laxmi.lifcvisitors.savedata.PrefConfig;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Employee_Send_Request_toGaurd extends AppCompatActivity {

    RecyclerView mRecyclerView;
    List<VisitorsByGuard.Data> visitorsByGuardList = new ArrayList<>();
    PrefConfig prefConfig;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_send_request_to_gaurd);

        prefConfig = new PrefConfig(this);

        mRecyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(Employee_Send_Request_toGaurd.this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(Employee_Send_Request_toGaurd.this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

      //  progressDialogInitialisaton();
        getVisitorsByGuardList();

        TextView tv = (TextView) this.findViewById(R.id.mywidget);
        tv.setSelected(true);

    }

    private void getVisitorsByGuardList() {

        APIService service = ApiClient.getClient().create(APIService.class);

        Call<VisitorsByGuard> call = service.getVisitorsByGuard(prefConfig.readToken());
        call.enqueue(new Callback<VisitorsByGuard>() {
            @Override
            public void onResponse(Call<VisitorsByGuard> call, Response<VisitorsByGuard> response) {

                final VisitorsByGuard allEvent = response.body();

                if (allEvent != null) {
                    for(int i = 0;i<allEvent.getData().size();i++){
                        visitorsByGuardList = allEvent.getData();
                    }
                }


                if (visitorsByGuardList != null) {

//                    pDialog.dismiss();

                }

                MailAdapter mMailAdapter = new MailAdapter(Employee_Send_Request_toGaurd.this, visitorsByGuardList);
                mRecyclerView.setAdapter(mMailAdapter);

                mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(),
                        mRecyclerView, new RecyclerTouchListener.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {


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
