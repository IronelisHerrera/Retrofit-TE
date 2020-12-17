package com.example.retrofit.SecondaryActivities;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.retrofit.APIService;
import com.example.retrofit.Adapters.CommentsAdapter;
import com.example.retrofit.Entities.Comment;
import com.example.retrofit.Entities.Post;
import com.example.retrofit.MainActivity;
import com.example.retrofit.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;

public class AllCommentsActivity extends AppCompatActivity {

    private List<Comment> comments;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_comments);


        /*
          Here I capture the Id when I click in one post. Now I can filter the comments of that post Id.
         */
        Intent intent = getIntent();
        int get_position =  intent.getIntExtra(MainActivity.EXTRA_MESSAGE_ELEMENT_POSITION, 1);

        /*End block - calling Id Value*/

         recyclerView = findViewById(R.id.recycle_view_comments);


        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        /*Filter comments by post id*/
        Call<List<Comment>> list_call = retrofit.create(APIService.class).getCommentsByPost((get_position)+1);
        list_call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {

                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "An error has occur!"+response.code(),
                            Toast.LENGTH_LONG).show();
                }

                comments = response.body();

                CommentsAdapter adapter = new CommentsAdapter(comments, getApplicationContext());

                recyclerView.setLayoutManager(new LinearLayoutManager(AllCommentsActivity.this));
                recyclerView.setHasFixedSize(true);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(@NonNull Call<List<Comment>> call, @NonNull Throwable t) {
                Log.e("onFailure: ", t.getMessage());
            }
        });

    }
}