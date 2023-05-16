package com.laxmi.lifcvisitors.activity.visitors;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.material.textfield.TextInputEditText;
import com.laxmi.lifcvisitors.R;
import com.laxmi.lifcvisitors.model.MSG;
import com.laxmi.lifcvisitors.retrofitservices.APIService;
import com.laxmi.lifcvisitors.retrofitservices.ApiClient;
import com.laxmi.lifcvisitors.savedata.PrefConfig;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;


public class SendFeedbackEmployee extends Dialog {
    private float userRate = 0;
    PrefConfig prefConfig;
    String visitorNameValue;
    int visitorId;
    String employeeId;
    String guardId;

    TextInputEditText ev_feedback;

    public SendFeedbackEmployee(@NonNull Context context, String visitorNameValue, String employeeId, String guardId, Integer visitorId) {
        super(context);
        this.visitorNameValue = visitorNameValue;
        this.employeeId = employeeId;
        this.guardId = guardId;
        this.visitorId = visitorId;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((R.layout.feedback_send));
        prefConfig = new PrefConfig(getContext());
        final AppCompatButton rateNowBtn = findViewById(R.id.rateNowBtn);
        final AppCompatButton laterBtn = findViewById(R.id.mayBeLaterBtn);
         ev_feedback = findViewById(R.id.ev_feedback);
        final RatingBar ratingBar = findViewById(R.id.ratingBar);
        final ImageView ratingImage = findViewById(R.id.ratingImage);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (rating <= 1) {
                    ratingImage.setImageResource(R.drawable.angry);
                } else if (rating <= 2) {
                    ratingImage.setImageResource(R.drawable.lowsad);

                } else if (rating <= 3) {
                    ratingImage.setImageResource(R.drawable.happy);
                } else if (rating <= 4) {
                    ratingImage.setImageResource(R.drawable.love);

                } else {
                    ratingImage.setImageResource(R.drawable.love);
                }
                //animate emoji image
                animateImage(ratingImage);
                //selected rating by user
                userRate = rating;
            }
        });

//        final TextView visitorName = findViewById(R.id.visitorName);
//
//        visitorName.setText("Hello "+visitorNameValue);

        rateNowBtn.setOnClickListener(v -> {//your code goes here
            getFeedBack();
        });
        laterBtn.setOnClickListener(v -> {
            //hide rating dialog
            dismiss();
        });
    }

    private void getFeedBack() {
        APIService service = ApiClient.getClient().create(APIService.class);
        Call<MSG> call = service.getFeedbackUpdate("Bearer " + prefConfig.readToken(), visitorId, employeeId,
                guardId, ""+ Objects.requireNonNull(ev_feedback.getText()), "");
        call.enqueue(new Callback<MSG>() {
            @Override
            public void onResponse(@NonNull Call<MSG> call, @NonNull retrofit2.Response<MSG> response) {

                if (response.body() != null) {
                    if (response.body().getMessage().equalsIgnoreCase("Feedback Update Successfully")) {

                        Toast.makeText(getContext(), "Feedback Submitted", Toast.LENGTH_SHORT).show();

                    }
                }
                else {
//                    Toast.makeText(getContext(), "Wrong Credentials", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getContext(), "Feedback Submitted", Toast.LENGTH_SHORT).show();

                    dismiss();
                }

            }

            @Override
            public void onFailure(@NonNull Call<MSG> call, @NonNull Throwable t) {

                Log.d("Error", t.getMessage());
            }
        });


    }

    private void animateImage(ImageView ratingImage) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1f, 0, 1f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setDuration(200);
        ratingImage.startAnimation(scaleAnimation);
    }


}
