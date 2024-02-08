package com.example.myapplication2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication2.Model.ModelBusPerson;

import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonHolder> {
    List<ModelBusPerson> personList;
    Context context;

    public PersonAdapter(List<ModelBusPerson> personList, Context context) {
        this.personList = personList;
        this.context = context;
    }

    @NonNull
    @Override
    public PersonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);

        View personView=inflater.inflate(R.layout.person_view,parent,false);
        PersonHolder personHolder=new PersonHolder(personView);
        return personHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PersonHolder holder, int position) {
//        int index=holder.getAdapterPosition();
        int personId=personList.get(position).getBusPersonId();
        holder.personNameTv.setText(personList.get(position).getName());
        holder.personTypeTv.setText(personList.get(position).getBusPersonType());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context.getApplicationContext(),PersonDetails.class);
                intent.putExtra("personId",personId);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return personList.size();
    }
}
