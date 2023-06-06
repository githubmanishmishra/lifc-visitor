package com.laxmi.lifcvisitors.fragments;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.laxmi.lifcvisitors.Employee_Send_Request_toGaurd;
import com.laxmi.lifcvisitors.R;
import com.laxmi.lifcvisitors.activity.employee.Employee_profile_update;
import com.laxmi.lifcvisitors.activity.visitors.FeedbackEmployee;
import com.laxmi.lifcvisitors.activity.visitors.Visitorrequestcome_to_emplpyee;

import java.util.Objects;

public class DashboardFragment extends Fragment {
    TextView tv_newvisitors;
    Intent intent;

   private TextView total_visitor_status,emp_profile,emp_Feedback;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dashboard_fragment, container, false);
        tv_newvisitors = view.findViewById(R.id.new_Visitor_employee);
        total_visitor_status = view.findViewById(R.id.total_visitor_status);
        emp_profile = view.findViewById(R.id.emp_myprofile);
        emp_Feedback =view.findViewById(R.id.emp_feedback);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        long[] back_pressed = {System.currentTimeMillis()};// Backpressed initialized //
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    if (back_pressed[0] >     System.currentTimeMillis()) {
                        //   Log.d("Time backpressed..if", "" + back_pressed[0]);
                        //   Log.d("Time system..if", "" + System.currentTimeMillis());
                        getActivity().finish();
                    }
          else
          {
              Toast.makeText(getActivity(), "Press once again to exit!", Toast.LENGTH_SHORT).show();
              back_pressed[0] = (System.currentTimeMillis() + 2000);
              Log.d("Time backpressed..else", "" + back_pressed[0]);
              Log.d("Time system..else", "" + System.currentTimeMillis());
          }
          return true;
                    }
                return false;
                }
            });


        emp_Feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FeedbackEmployee feedbacks = new FeedbackEmployee(requireContext());
                feedbacks.setCancelable(true);
                feedbacks.show();
                feedbacks.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
            }
        });
        emp_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getContext(), Employee_profile_update.class);
                startActivity(intent);

            }
        });

        tv_newvisitors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getContext(), Visitorrequestcome_to_emplpyee.class);
                startActivity(intent);
                requireActivity().finish();
            }
        });

        total_visitor_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getContext(), Employee_Send_Request_toGaurd.class);
                startActivity(intent);
            }
        });

        TextView tv = (TextView) view.findViewById(R.id.mywidget);
        tv.setSelected(true);
        return view;

    }
}
