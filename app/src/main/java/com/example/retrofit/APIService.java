package com.example.retrofit;

import com.example.retrofit.Entities.Comment;
import com.example.retrofit.Entities.Post;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface APIService {

    @GET("posts")
    Call<List<Post>> getPosts();

    @GET("posts/{postId}/comments")
    Call<List<Comment>> getCommentsByPost(@Path("postId") int postId);

}
