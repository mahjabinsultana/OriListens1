package com.example.OriListens;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ContactRV_adapter extends RecyclerView.Adapter<ContactRV_adapter.ViewHolder> {

    private ArrayList<ContactRV_item> contactRV_items;
    private Context context;
    public ContactRV_adapter(ArrayList<ContactRV_item> contact_rv_items, Context context) {
        this.contactRV_items = contact_rv_items;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_rv_items,parent,false);
        return new ContactRV_adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.get().load(contactRV_items.get(position).getImage()).into(holder.imageView);
        holder.name.setText(contactRV_items.get(position).getName());
        holder.location.setText(contactRV_items.get(position).getLocation());
        holder.contact.setText(contactRV_items.get(position).getContact());
    }



    @Override
    public int getItemCount() {
        return contactRV_items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name;
        TextView location;
        TextView contact;
        public ViewHolder(View view) {
            super(view);
            imageView = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
            location = itemView.findViewById(R.id.location);
            contact = itemView.findViewById(R.id.contact);
            
        }
    }
}
