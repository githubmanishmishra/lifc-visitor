package com.laxmi.lifcvisitors.activity.visitors;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.laxmi.lifcvisitors.R;
import com.laxmi.lifcvisitors.retrofitservices.VisitorsByEmployee;
import java.util.List;
import java.util.Random;

public class MailAdapterFeedbackEmployee extends RecyclerView.Adapter<MailViewHolderEmployee> {
    private List<VisitorsByEmployee.Data> mEmailData;
    private Context mContext;

    public MailAdapterFeedbackEmployee(Context mContext, List<VisitorsByEmployee.Data> mEmailData) {
        this.mEmailData = mEmailData;
        this.mContext = mContext;
    }
    @Override
    public MailViewHolderEmployee onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_floor_item_employee,
                parent, false);
        return new MailViewHolderEmployee(view);
    }

    @Override
    public void onBindViewHolder(final MailViewHolderEmployee holder, int position)
    {
        holder.mIcon.setText(mEmailData.get(position).getName().substring(0, 1));
        holder.tv_visitor_name.setText(mEmailData.get(position).getName());
        holder.tv_visitor_mobile.setText(mEmailData.get(position).getEmployeeId());
        holder.tv_status.setText(mEmailData.get(position).getStatus());
        holder.tv_visitor_address.setText(mEmailData.get(position).getPurposeOfComing());
//        holder.tv_visitor_address.setText(mEmailData.get(position).getCity()+ ", "+mEmailData.get(position).getState());
        holder.tvTimeIn.setText("Check In " + mEmailData.get(position).getCheckIn());
        holder.tvTimeOut.setText("Check Out " + mEmailData.get(position).getCheckOut());

        Random mRandom = new Random();
        final int color = Color.argb(255, mRandom.nextInt(256), mRandom.nextInt(256), mRandom.nextInt(256));
        ((GradientDrawable) holder.mIcon.getBackground()).setColor(color);
        holder.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if (!mEmailData.get(position).getStatus().equalsIgnoreCase("Disapprove")) {
                    SendFeedbackEmployee feedbacks = new SendFeedbackEmployee(view.getContext(), holder.tv_visitor_name.getText().toString(),
                            mEmailData.get(position).getEmployeeId(),mEmailData.get(position).getGuardId(),
                            mEmailData.get(position).getId());
                    feedbacks.setCancelable(true);
                    feedbacks.show();
                    feedbacks.getWindow().setBackgroundDrawable(new ColorDrawable(view.getContext().getResources().getColor(android.R.color.transparent)));

                }

            }
        });

/*
        if (mEmailData.get(position).getStatus().equalsIgnoreCase("Pending")) {
            new CountDownTimer(120000, 1000) {

                public void onTick(long millisUntilFinished) {
                    holder._tv.setText("seconds remaining: " + millisUntilFinished / 1000);
                    // logic to set the EditText could go here

                }

                public void onFinish() {
                    holder._tv.setText("done!");
                    holder._tv.setVisibility(View.GONE);
                }

            }.start();

        }
*/
    }

    @Override
    public int getItemCount() {
        return mEmailData.size();
    }
}

class MailViewHolderEmployee extends RecyclerView.ViewHolder {

    TextView mIcon;
    TextView tv_visitor_name;
    TextView tv_visitor_mobile;
    TextView tv_visitor_address;
    TextView tvTimeIn;
    TextView tv_status;
    TextView _tv,tvTimeOut;
    RelativeLayout mLayout;

    MailViewHolderEmployee(View itemView) {
        super(itemView);
        mIcon = itemView.findViewById(R.id.tvIcon);
        tv_visitor_name = itemView.findViewById(R.id.tv_visitor_name);
        tv_visitor_mobile = itemView.findViewById(R.id.tv_visitor_mobile);
        tv_visitor_address = itemView.findViewById(R.id.tv_visitor_address);
        tvTimeIn = itemView.findViewById(R.id.tvTimeIn);
        tv_status = itemView.findViewById(R.id.tv_status);
        mLayout = itemView.findViewById(R.id.layout);
        _tv = itemView.findViewById(R.id._tv);
        tvTimeOut = itemView.findViewById(R.id.tvTimeOut);

    }
}