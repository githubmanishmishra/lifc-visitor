package com.laxmi.lifcvisitors.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.laxmi.lifcvisitors.R;
import com.laxmi.lifcvisitors.activity.employee.EmployeeDashboard;
import com.laxmi.lifcvisitors.activity.guard.GuardDashboard;
import com.laxmi.lifcvisitors.savedata.PrefConfig;

@SuppressLint("CustomSplashScreen")
public class SplashScreen extends AppCompatActivity {
    private static final String TAG = "AnimationStarter";
    public static PrefConfig prefConfig;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        prefConfig = new PrefConfig(this);
        LottieAnimationView lottie = findViewById(R.id.lottie);
        //  final ImageView bounceBallImage = findViewById(R.id.bounceBallImage);
        lottie.clearAnimation();
        TranslateAnimation transAnim = new TranslateAnimation(0, 0, 0, 0);
        transAnim.setStartOffset(1000);
        transAnim.setDuration(5000);
        transAnim.setFillAfter(true);
        transAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.i(TAG, "Starting button dropdown animation");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                if (prefConfig.readLoginStatus()) {
                    if (prefConfig.readType().equalsIgnoreCase("Guard")) {
                        startActivity(new Intent(SplashScreen.this, GuardDashboard.class));
                        finish();
                    }else {
                        startActivity(new Intent(SplashScreen.this, EmployeeDashboard.class));
                        finish();
                    }
                } else {
                    startActivity(new Intent(SplashScreen.this, MainActivity.class));
                    finish();
                }

            }
        });
        lottie.startAnimation(transAnim);

    }

    private int getDisplayHeight() {
        return this.getResources().getDisplayMetrics().heightPixels;

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}