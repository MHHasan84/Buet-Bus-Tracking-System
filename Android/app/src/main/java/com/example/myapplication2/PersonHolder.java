package com.example.myapplication2;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PersonHolder extends RecyclerView.ViewHolder {
    TextView personNameTv;
    TextView personTypeTv;
    View view;
    public PersonHolder(@NonNull View itemView) {
        super(itemView);
        personNameTv=itemView.findViewById(R.id.person_name_tv);
        personTypeTv=itemView.findViewById(R.id.person_type_tv);
        view=itemView;
    }
}
