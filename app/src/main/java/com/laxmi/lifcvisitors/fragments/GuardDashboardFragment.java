package com.laxmi.lifcvisitors.fragments;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.laxmi.lifcvisitors.R;
import com.laxmi.lifcvisitors.activity.guard.GuardProfileActivity;
import com.laxmi.lifcvisitors.activity.guard.GuardSendRequestToEmployeeList;
import com.laxmi.lifcvisitors.activity.visitors.Feedback;
import com.laxmi.lifcvisitors.activity.visitors.New_visitordetail;

public class GuardDashboardFragment extends Fragment {
    TextView tv_newvisitor, tv_my_profile,total_visitor_count;
    Intent intent;

    TextView tvfeedback;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_gaurd_dashboard, container, false);
        tv_newvisitor = view.findViewById(R.id.new_visitor);
        tv_my_profile = view.findViewById(R.id.tv_my_profile);

        tvfeedback = view.findViewById(R.id.feedback);

        tvfeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Feedback feedbacks = new Feedback(requireActivity());
                feedbacks.setCancelable(false);
                feedbacks.show();
                feedbacks.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
            }
        });
        tv_newvisitor.setOnClickListener(view1 -> {
            intent = new Intent(getContext(), New_visitordetail.class);
            startActivity(intent);
        });

        total_visitor_count = view.findViewById(R.id.total_visitor_count);

        total_visitor_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), GuardSendRequestToEmployeeList.class));
            }
        });

        tv_my_profile.setOnClickListener(view1 -> {
            intent = new Intent(getContext(), GuardProfileActivity.class);
            startActivity(intent);
        });
        TextView tv = (TextView) view.findViewById(R.id.mywidget);
        tv.setSelected(true);


        return view;


    }
}
