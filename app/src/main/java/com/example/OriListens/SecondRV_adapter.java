package com.example.OriListens;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.OriListens.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SecondRV_adapter extends RecyclerView.Adapter<SecondRV_adapter.ViewHolder> {

    private ArrayList<SecondRV_item> secondRV_items;
    private Context context;

    public SecondRV_adapter(ArrayList<SecondRV_item> secondRV_items, Context context) {
        this.secondRV_items = secondRV_items;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.second_rv_items,parent,false);
        return new SecondRV_adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.get().load(secondRV_items.get(position).getImage()).into(holder.imageView);
        holder.textView.setText(secondRV_items.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return secondRV_items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.videoThumbnailIV);
            textView = itemView.findViewById(R.id.videoTitleTV);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();

            // Retrieve the video link for the clicked item
            String videoLink = secondRV_items.get(position).getLink();

            // Launch the YouTube video using an Intent
            watchYoutubeVideo(view.getContext(), videoLink);

        }
    }
    private void watchYoutubeVideo(Context context, String videoLink) {
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(videoLink));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse(videoLink));
        try {
            context.startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            context.startActivity(webIntent);
        }
    }

}
