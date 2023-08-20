package com.example.myapplication2;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ContactHolder extends RecyclerView.ViewHolder {
    protected TextView contactName;
    protected TextView contactNumber;
    protected TextView contactType;
    protected TextView contactValue;

    public ContactHolder(@NonNull View itemView) {
        super(itemView);

        contactName=itemView.findViewById(R.id.contact_name);
        contactNumber=itemView.findViewById(R.id.contact_number);
        contactType=itemView.findViewById(R.id.contact_type);
        contactValue=itemView.findViewById(R.id.contact_value);
    }
}
