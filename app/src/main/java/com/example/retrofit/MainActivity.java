package com.example.retrofit;

import android.content.Intent;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.retrofit.SecondaryActivities.AllCommentsActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;

public class MainActivity extends AppCompatActivity implements  PostAdapter.WhenCLickOnRecyclerRow {

    private RecyclerView recyclerView;
    private List<Post> elements;
    private static final String GET_CURRENT_ELEMENT_POSITION = "get_position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycle_view);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/").addConverterFactory(GsonConverterFactory.create())
                .build();

        Call<List<Post>> listCall = retrofit.create(APIService.class).getPosts();

        listCall.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(@NonNull Call<List<Post>> call, @NonNull Response<List<Post>> response) {

                elements = response.body();

                PostAdapter adapter = new PostAdapter(getApplicationContext(), elements, MainActivity.this);

                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                recyclerView.setHasFixedSize(true);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(adapter);


            }

            @Override
            public void onFailure(@NonNull Call<List<Post>> call, @NonNull  Throwable t) {
                Log.e("onFailure: ", t.getMessage());
            }
        });
    }
    @Override
    public void ClickOnRow(int position) {
        Intent intent = new Intent(MainActivity.this, AllCommentsActivity.class);
        intent.putExtra(GET_CURRENT_ELEMENT_POSITION, position);
        startActivity(intent);
    }

}