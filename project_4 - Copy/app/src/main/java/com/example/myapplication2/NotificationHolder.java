package com.example.myapplication2;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NotificationHolder extends RecyclerView.ViewHolder {
    protected TextView nfDate;
    protected TextView nfTime;
    protected TextView nfMsg;
    public NotificationHolder(@NonNull View itemView) {
        super(itemView);
        nfDate=itemView.findViewById(R.id.nf_date);
        nfTime=itemView.findViewById(R.id.nf_time);
        nfMsg=itemView.findViewById(R.id.nf_msg);
    }
}
