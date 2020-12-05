package com.example.retrofit.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.retrofit.Entities.Comment;
import com.example.retrofit.R;
import java.util.List;


public class  CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.Holder>{

    private final static String TAG = "CommentsAdapter";
    List<Comment> comments;
    Context context;

    public CommentsAdapter(List<Comment> comments, Context context) {
        this.comments = comments;
        this.context = context;
    }

    public static class Holder extends RecyclerView.ViewHolder {
        TextView id;
        TextView comment_title;
        TextView comment_body;


        public Holder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id._id);
            comment_title = itemView.findViewById(R.id.title);
            comment_body = itemView.findViewById(R.id.body);
        }
    }


    @NonNull
    @Override
    public CommentsAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate the view that is going to hold the items
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentsAdapter.Holder holder, int position) {
        Comment element = comments.get(position);
        holder.id.setText(String.valueOf(element.getId()));
        holder.comment_title.setText(element.getComment_name()+"\n\n"+element.getComment_email());
        holder.comment_body.setText(element.getComment_body());

    }

    @Override
    public int getItemCount() {
        return comments.size();
    }


}
