package com.laxmi.lifcvisitors.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.laxmi.lifcvisitors.R;
import com.laxmi.lifcvisitors.activity.visitors.Visitorrequestcome_to_emplpyee;

public class DashboardFragment extends Fragment {
    TextView tv_newvisitors;
    Intent intent;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dashboard_fragment, container, false);
        tv_newvisitors = view.findViewById(R.id.new_Visitor_employee);
        tv_newvisitors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getContext(), Visitorrequestcome_to_emplpyee.class);
                startActivity(intent);
            }
        });
        TextView tv = (TextView) view.findViewById(R.id.mywidget);
        tv.setSelected(true);
        return view;
    }
}
