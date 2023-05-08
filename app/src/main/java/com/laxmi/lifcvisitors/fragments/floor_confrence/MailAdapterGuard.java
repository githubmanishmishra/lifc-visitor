package com.laxmi.lifcvisitors.fragments.floor_confrence;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.laxmi.lifcvisitors.R;
import com.laxmi.lifcvisitors.model.VisitorsByGuard;
import java.util.List;
import java.util.Random;
public class MailAdapterGuard extends RecyclerView.Adapter<MailViewHolderGuard> {
    private List<VisitorsByGuard.Data> mEmailData;
    private Context mContext;
    public MailAdapterGuard(Context mContext, List<VisitorsByGuard.Data> mEmailData) {
        this.mEmailData = mEmailData;
        this.mContext = mContext;
    }
    @Override
    public MailViewHolderGuard onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyelerview_floor_item,
                parent, false);
        return new MailViewHolderGuard(view);
    }
    @Override
    public void onBindViewHolder(final MailViewHolderGuard holder, int position) {
        holder.mIcon.setText(mEmailData.get(position).getName().substring(0, 1));
        holder.tv_visitor_name.setText(mEmailData.get(position).getName());
        holder.tv_visitor_mobile.setText(mEmailData.get(position).getDisapproveReason());
        holder.tv_status.setText(mEmailData.get(position).getStatus());
        holder.tv_visitor_address.setText(mEmailData.get(position).getMeetPlace());
//        holder.tv_visitor_address.setText(mEmailData.get(position).getCity()+ ", "+mEmailData.get(position).getState());
        holder.tvTimeIn.setText("Check In "+mEmailData.get(position).getCheckIn());
        Random mRandom = new Random();
        final int color = Color.argb(255, mRandom.nextInt(256), mRandom.nextInt(256), mRandom.nextInt(256));
        ((GradientDrawable) holder.mIcon.getBackground()).setColor(color);
        holder.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!mEmailData.get(position).getStatus().equalsIgnoreCase("Disapprove")){
                    Intent mIntent = new Intent(mContext, DetailActivity.class);
                    mIntent.putExtra("sender", holder.tv_visitor_name.getText().toString());
                    mIntent.putExtra("title", holder.tv_visitor_mobile.getText().toString());
                    mIntent.putExtra("details", holder.tv_visitor_address.getText().toString());
                    mIntent.putExtra("time", holder.tvTimeIn.getText().toString());
                    mIntent.putExtra("icon", holder.mIcon.getText().toString());
                    mIntent.putExtra("Status", holder.tv_status.getText().toString());
                    mIntent.putExtra("visitorId", mEmailData.get(position).getId());
                    mIntent.putExtra("employeeId", mEmailData.get(position).getEmployeeId());
                    mIntent.putExtra("colorIcon", color);
                    mContext.startActivity(mIntent);
                }

            }
        });

        if(mEmailData.get(position).getStatus().equalsIgnoreCase("Pending")) {
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
    }

    @Override
    public int getItemCount() {
        return mEmailData.size();
    }
}

class MailViewHolderGuard extends RecyclerView.ViewHolder {

    TextView mIcon;
    TextView tv_visitor_name;
    TextView tv_visitor_mobile;
    TextView tv_visitor_address;
    TextView tvTimeIn;
    TextView tv_status;
    TextView _tv;
    RelativeLayout mLayout;

    MailViewHolderGuard(View itemView) {
        super(itemView);
        mIcon = itemView.findViewById(R.id.tvIcon);
        tv_visitor_name = itemView.findViewById(R.id.tv_visitor_name);
        tv_visitor_mobile = itemView.findViewById(R.id.tv_visitor_mobile);
        tv_visitor_address = itemView.findViewById(R.id.tv_visitor_address);
        tvTimeIn = itemView.findViewById(R.id.tvTimeIn);
        tv_status = itemView.findViewById(R.id.tv_status);
        mLayout = itemView.findViewById(R.id.layout);
        _tv = itemView.findViewById(R.id._tv);
    }
}
