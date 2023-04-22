package com.laxmi.lifcvisitors.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.laxmi.lifcvisitors.R;
import com.laxmi.lifcvisitors.activity.guard.GuardDashboard;
import com.laxmi.lifcvisitors.activity.guard.GuardProfileActivity;
import com.laxmi.lifcvisitors.activity.visitors.New_visitordetail;
import com.laxmi.lifcvisitors.languageconvert.LocaleManager;
import static com.laxmi.lifcvisitors.languageconvert.LocaleManager.setNewLocale;

import java.util.Objects;

public class GuardDashboardFragment extends Fragment {
    TextView tv_newvisitor, tv_my_profile;
    Intent intent;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_gaurd_dashboard, container, false);
        tv_newvisitor = view.findViewById(R.id.new_visitor);
        tv_my_profile = view.findViewById(R.id.tv_my_profile);
        tv_newvisitor.setOnClickListener(view1 -> {
            intent = new Intent(getContext(), New_visitordetail.class);
            startActivity(intent);
        });

        tv_my_profile.setOnClickListener(view1 -> {
            intent = new Intent(getContext(), GuardProfileActivity.class);
            startActivity(intent);
        });
        TextView tv = (TextView) view.findViewById(R.id.mywidget);
        tv.setSelected(true);

        RelativeLayout relSelectLanguage = view.findViewById(R.id.rel_select_language);

        relSelectLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  startActivity(new Intent(getContext(), LanguageActivity.class));

                final Dialog dialogLanguages =
                        new Dialog(requireContext(), android.R.style.Theme_DeviceDefault);
                dialogLanguages.requestWindowFeature(Window.FEATURE_NO_TITLE);

                dialogLanguages.setCancelable(true);
                dialogLanguages.setContentView(R.layout.dialog_languages);

                dialogLanguages.show();

                final RadioButton rbEnglish = dialogLanguages.findViewById(R.id.rb_english);
                final RadioButton rbHindi = dialogLanguages.findViewById(R.id.rb_hindi);
                final RadioButton rbGujarati = dialogLanguages.findViewById(R.id.rb_gujarati);

                rbEnglish.setOnClickListener(view1 -> {
                    dialogLanguages.dismiss();
                    setNewLocale(getContext(), LocaleManager.ENGLISH);

                    //Finish All Activity and return to main Screen or wherever you want
                    Intent intent1 = new Intent(getActivity(), GuardDashboard.class);
                    requireActivity().startActivity(intent1);
                    requireActivity().finishAffinity();
                });

                rbHindi.setOnClickListener(view12 -> {
                    dialogLanguages.dismiss();
                    setNewLocale(getContext(), LocaleManager.HINDI);

                    Intent intent2 = new Intent(getActivity(), GuardDashboard.class);
                    requireActivity().startActivity(intent2);
                    requireActivity().finishAffinity();
                });
                rbGujarati.setOnClickListener(view13 -> {
                    dialogLanguages.dismiss();
                    setNewLocale(getContext(), LocaleManager.GUJARATI);

                    Intent intent3 = new Intent(getActivity(), GuardDashboard.class);
                    requireActivity().startActivity(intent3);
                    requireActivity().finishAffinity();
                });

            }
        });

        return view;


    }
}
