package com.laxmi.lifcvisitors.activity.visitors;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.laxmi.lifcvisitors.R;
import com.laxmi.lifcvisitors.RecyclerTouchListener;
import com.laxmi.lifcvisitors.model.VisitorsByGuard;
import com.laxmi.lifcvisitors.retrofitservices.APIService;
import com.laxmi.lifcvisitors.retrofitservices.ApiClient;
import com.laxmi.lifcvisitors.savedata.PrefConfig;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Feedback extends Dialog implements SwipeRefreshLayout.OnRefreshListener {

    PrefConfig prefConfig;

    RecyclerView mRecyclerView;
    List<VisitorsByGuard.Data> VisitorsByGuardList = new ArrayList<>();
    ProgressDialog pDialog;
    SwipeRefreshLayout swipeRefreshLayout;

  //  Feedback feedbacks;
    String approveValue = "Approve";

    public Feedback(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((R.layout.feedback));

        prefConfig = new PrefConfig(getContext());

//        feedbacks = new Feedback(getContext());
//        feedbacks.setCancelable(false);
//        feedbacks.getWindow().setBackgroundDrawable(new ColorDrawable(getContext().getResources().getColor(android.R.color.transparent)));

//        final AppCompatButton rateNowBtn = findViewById(R.id.rateNowBtn);
//        final AppCompatButton laterBtn = findViewById(R.id.mayBeLaterBtn);
//        rateNowBtn.setOnClickListener(v -> {//your code goes here
//           getFeedBack();
//        });
//        laterBtn.setOnClickListener(v -> {
//            //hide rating dialog
//            dismiss();
//        });

        swipeRefreshLayout = findViewById(R.id.swipeContainer);

        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.pink,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);


        mRecyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mLinearLayoutManager.setReverseLayout(true);
        mLinearLayoutManager.setStackFromEnd(true);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        progressDialogInitialisaton();
        getVisitorsByGuardList();

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

        Call<VisitorsByGuard> call = service.getVisitorsByGuard("Bearer " + prefConfig.readToken());
        call.enqueue(new Callback<VisitorsByGuard>() {
            @Override
            public void onResponse(Call<VisitorsByGuard> call, Response<VisitorsByGuard> response) {

                final VisitorsByGuard allEvent = response.body();

                if (allEvent != null) {

                    for (int i = 0; i < allEvent.getData().size(); i++) {
                        if(allEvent.getData().get(i).getStatus().equalsIgnoreCase(approveValue)){
                            VisitorsByGuardList = allEvent.getData();
                        }
                    }
                }


                if (VisitorsByGuardList != null) {

                    pDialog.dismiss();

                }

                MailAdapterFeedbackGuard mMailAdapter = new MailAdapterFeedbackGuard(getContext(), VisitorsByGuardList);
                mRecyclerView.setAdapter(mMailAdapter);

                mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(),
                        mRecyclerView, new RecyclerTouchListener.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {

                        VisitorsByGuard.Data newArrival = VisitorsByGuardList.get(position);
//                        feedbacks.show();

                    }

                    @Override
                    public void onLongClick(View view, int position) {

                    }
                }));

            }

            @Override
            public void onFailure(Call<VisitorsByGuard> call, Throwable t) {

                pDialog.dismiss();
            }
        });


    }

    private void progressDialogInitialisaton() {

        pDialog = new ProgressDialog(getContext());
        pDialog.setMessage("Loading Data Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
    }

    @Override
    public void onRefresh() {
        getVisitorsByGuardList();
    }

    @Override
    protected void onStart() {
        super.onStart();
      //  feedbacks.dismiss();
    }

}
