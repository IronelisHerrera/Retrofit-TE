package com.example.retrofit;

import com.google.gson.annotations.SerializedName;

public class Comment {
    private int postId;
    private int id;
    @SerializedName("name")
    private String comment_name;
    @SerializedName("email")
    private String comment_email;
    @SerializedName("body")
    private String comment_body;

    public int getPostId() {
        return postId;
    }

    public int getId() {
        return id;
    }

    public String getComment_name() {
        return comment_name;
    }

    public String getComment_email() {
        return comment_email;
    }

    public String getComment_body() {
        return comment_body;
    }
}
