package com.example.OriListens;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.OriListens.R;

import java.util.ArrayList;

public class MainRV_adapter extends RecyclerView.Adapter<MainRV_adapter.ViewHolder> {


    private ArrayList<MainRV_item> mainRV_items;
    private Context context;
    private MainListener listener;
    public MainRV_adapter(ArrayList<MainRV_item> mainRV_items, Context context, MainListener listener) {
        this.mainRV_items = mainRV_items;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MainRV_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_mainrv,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    holder.imageView.setImageResource(mainRV_items.get(position).getImage());
    holder.textView.setText(mainRV_items.get(position).getText());
    holder.cardView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            listener.onItemClicked(mainRV_items.get(position));
        }
    });
    }

    @Override
    public int getItemCount() {
        return mainRV_items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.cardimage);
            textView = itemView.findViewById(R.id.cardName);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
