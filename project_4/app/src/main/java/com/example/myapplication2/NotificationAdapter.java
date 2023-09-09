package com.example.myapplication2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication2.Model.ModelNotification;
import com.example.myapplication2.Network.RetrofitInstance;
import com.example.myapplication2.Service.NotificationService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationHolder> {
    private List<ModelNotification> notificationList;
    private Context context;

    public NotificationAdapter(List<ModelNotification> notificationList, Context context) {
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
        ModelNotification notification=notificationList.get(position);
        holder.nfDate.setText(notification.getDate());
        holder.nfTime.setText(notification.getTime());
        holder.nfMsg.setText(notification.getMessage());

        if(notification.isSeenStatus()){
            holder.nfMsg.setAlpha(0.6f);
        }

        holder.nfCv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                notification.setSeenStatus(true);

                Retrofit retrofit= RetrofitInstance.getRetrofitInstance();
                NotificationService notificationService=retrofit.create(NotificationService.class);

                Call<ModelNotification> call=notificationService.updateNotification(notification);

                call.enqueue(new Callback<ModelNotification>() {
                    @Override
                    public void onResponse(Call<ModelNotification> call, Response<ModelNotification> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(context, "successfully", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ModelNotification> call, Throwable t) {

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return notificationList.size();
    }
}
