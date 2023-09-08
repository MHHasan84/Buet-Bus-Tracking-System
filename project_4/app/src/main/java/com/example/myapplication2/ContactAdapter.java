package com.example.myapplication2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactHolder> {
    private Context context;
    private List<Contact> contactList;

    public ContactAdapter(Context context, List<Contact> contactList) {
        this.context = context;
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public ContactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_card_view, parent, false);
        return new ContactHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactHolder holder, int position) {
        Contact contact=contactList.get(position);
        holder.contactName.setText(contact.getName());
        holder.contactNumber.setText(contact.getNumber());
        holder.contactType.setText(contact.getType());
        holder.contactValue.setText(contact.getValue());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+contact.getNumber()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }
}
