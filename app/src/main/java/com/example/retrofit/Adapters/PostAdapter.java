package com.example.retrofit.Adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;
import com.example.retrofit.Entities.Post;
import com.example.retrofit.R;


import java.io.Serializable;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.Holder> implements Serializable {

    private final Context getcontext;
    private final recycler_view_post_listener recycler_view_post;
    private final List<Post> elements;

    public PostAdapter(Context getcontext, List<Post> elements, recycler_view_post_listener recycler_view_post)
    {

        this.getcontext = getcontext;
        this.elements = elements;
        this.recycler_view_post = recycler_view_post;
    }



    public interface recycler_view_post_listener {
        void ClickOnRow(int adapterPosition);
    }

    public static class Holder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView id;
        TextView title;
        TextView body;
        recycler_view_post_listener recycler_view_post;

        public Holder(@NonNull View itemView, recycler_view_post_listener recycler_view_post) {
            super(itemView);
            id = itemView.findViewById(R.id._id);
            title = itemView.findViewById(R.id.title);
            body = itemView.findViewById(R.id.body);
            this.recycler_view_post = recycler_view_post;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            recycler_view_post.ClickOnRow(getAdapterPosition());
        }

    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item, null, false);
        return new Holder(view, recycler_view_post);
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