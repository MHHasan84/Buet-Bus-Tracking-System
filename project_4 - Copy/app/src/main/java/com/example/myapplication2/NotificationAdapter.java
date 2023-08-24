package com.example.myapplication2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationHolder> {
    private List<NotificationModel> notificationList;
    private Context context;

    public NotificationAdapter(List<NotificationModel> notificationList, Context context) {
        this.notificationList = notificationList;
        this.context = context;
    }

    @NonNull
    @Override
    public NotificationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_cardview, parent, false);
        return new NotificationHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationHolder holder, int position) {
        NotificationModel notification=notificationList.get(position);
        holder.nfDate.setText(notification.getDate());
        holder.nfTime.setText(notification.getTime());
        holder.nfMsg.setText(notification.getMessage());
    }

    @Override
    public int getItemCount() {
        return notificationList.size();
    }
}
