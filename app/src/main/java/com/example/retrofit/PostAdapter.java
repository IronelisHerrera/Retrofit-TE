package com.example.retrofit;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;


import java.io.Serializable;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.Holder> implements Serializable {

    private final Context getcontext;
    private final WhenCLickOnRecyclerRow clickOnRowListener;
    private final List<Post> elements;

    PostAdapter(Context getcontext, List<Post> elements, WhenCLickOnRecyclerRow clickOnRowListener)
    {

        this.getcontext = getcontext;
        this.elements = elements;
        this.clickOnRowListener = clickOnRowListener;
    }



    public interface WhenCLickOnRecyclerRow {
        void ClickOnRow(int adapterPosition);
    }

    public static class Holder extends RecyclerView.ViewHolder implements View.OnClickListener{ //implements View.OnClickListener
        TextView id;
        TextView title;
        TextView body;
        WhenCLickOnRecyclerRow clickOnRowListener;

        public Holder(@NonNull View itemView, WhenCLickOnRecyclerRow clickOnRowListener) {
            super(itemView);
            id = itemView.findViewById(R.id._id);
            title = itemView.findViewById(R.id.title);
            body = itemView.findViewById(R.id.body);
            this.clickOnRowListener = clickOnRowListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickOnRowListener.ClickOnRow(getAdapterPosition());
        }

    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item, null, false);
        return new Holder(view, clickOnRowListener); //, clickOnRowListener
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Post element = elements.get(position);
        holder.id.setText(String.valueOf(element.getId()));
        holder.title.setText(element.getPost_title());
        holder.body.setText(element.getPost_body());
    }

    @Override
    public int getItemCount() {
        return elements.size();
    }



}