package com.example.retrofit.Entities;


import com.google.gson.annotations.SerializedName;

public class Post {

    private int userId;
    private int id;
    @SerializedName("title")
    private String post_title;
    @SerializedName("body")
    private String post_body;

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getPost_title() {
        return post_title;
    }

    public String getPost_body() {
        return post_body;
    }
}
