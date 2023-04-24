package com.laxmi.lifcvisitors.floor_confrence;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.laxmi.lifcvisitors.R;
import com.laxmi.lifcvisitors.model.VisitorsByGuard;

import java.util.List;
import java.util.Random;

public class MailAdapter extends RecyclerView.Adapter<MailViewHolder> {

    private List<VisitorsByGuard.Data> mEmailData;
    private Context mContext;

    public MailAdapter(Context mContext, List<VisitorsByGuard.Data> mEmailData) {
        this.mEmailData = mEmailData;
        this.mContext = mContext;
    }

    @Override
    public MailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyelerview_floor_item,
                parent, false);
        return new MailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MailViewHolder holder, int position) {
        holder.mIcon.setText(mEmailData.get(position).getName().substring(0, 1));
        holder.mSender.setText(mEmailData.get(position).getMobileNumber());
        holder.mEmailTitle.setText(mEmailData.get(position).getState());
        holder.mEmailDetails.setText(mEmailData.get(position).getCity());
        holder.mEmailTime.setText(mEmailData.get(position).getPincode());
        Random mRandom = new Random();
        final int color = Color.argb(255, mRandom.nextInt(256), mRandom.nextInt(256), mRandom.nextInt(256));
        ((GradientDrawable) holder.mIcon.getBackground()).setColor(color);
        holder.mFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.mFavorite.getColorFilter() != null) {
                    holder.mFavorite.clearColorFilter();
                } else {
                    holder.mFavorite.setColorFilter(ContextCompat.getColor(mContext,
                            R.color.purple_200));
                }
            }
        });

        holder.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(mContext, DetailActivity.class);
                mIntent.putExtra("sender", holder.mSender.getText().toString());
                mIntent.putExtra("title", holder.mEmailTitle.getText().toString());
                mIntent.putExtra("details", holder.mEmailDetails.getText().toString());
                mIntent.putExtra("time", holder.mEmailTime.getText().toString());
                mIntent.putExtra("icon", holder.mIcon.getText().toString());
                mIntent.putExtra("colorIcon", color);
                mContext.startActivity(mIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mEmailData.size();
    }
}

class MailViewHolder extends RecyclerView.ViewHolder {

    TextView mIcon;
    TextView mSender;
    TextView mEmailTitle;
    TextView mEmailDetails;
    TextView mEmailTime;
    ImageView mFavorite;
    RelativeLayout mLayout;

    MailViewHolder(View itemView) {
        super(itemView);

        mIcon = itemView.findViewById(R.id.tvIcon);
        mSender = itemView.findViewById(R.id.tvEmailSender);
        mEmailTitle = itemView.findViewById(R.id.tvEmailTitle);
        mEmailDetails = itemView.findViewById(R.id.tvEmailDetails);
        mEmailTime = itemView.findViewById(R.id.tvEmailTime);
        mFavorite = itemView.findViewById(R.id.ivFavorite);
        mLayout = itemView.findViewById(R.id.layout);
    }
}
