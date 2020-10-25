package com.example.phonebook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class Adapter extends BaseAdapter {
    private Context context;
    private List<Contact> listes;



    private LayoutInflater inflater;



    public Adapter(Context context,List<Contact> liste){
        this.context = context;
        this.listes = liste;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listes.size();
    }

    @Override
    public Contact getItem(int position) {
        return listes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listes.get(position).getId();
    }
    //pour personnalisé chaque element/*
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.adapter_layout,null);
        // get infos about item
        Contact currentContact = getItem(position);
        Integer itemId = currentContact.getId();
        String itemFirstName = currentContact.getFirstName();
        String itemLastName = currentContact.getLastName();
        String itemEmail = currentContact.getEmail();
        String itemTel = currentContact.getPhone();
        String itemProf = currentContact.getJob();

        // get item name view
        TextView itemIdView = convertView.findViewById(R.id.id);
        itemIdView.setText(String.valueOf(itemId));
        TextView itemNameView = convertView.findViewById(R.id.nom);
        itemNameView.setText(itemLastName);
        TextView itemFirstNameView = convertView.findViewById(R.id.prenom);
        itemFirstNameView.setText(itemFirstName);


        TextView itemEmailView = convertView.findViewById(R.id.email);
        itemEmailView.setText(itemEmail);

        TextView itemTelView = convertView.findViewById(R.id.tel);
        itemTelView.setText(itemTel);

        TextView itemProfView = convertView.findViewById(R.id.prof);
        itemProfView.setText(itemProf);

        return convertView;
    }



}
