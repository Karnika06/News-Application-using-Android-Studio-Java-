package com.example.thenewz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements CategoryRVAdapter.CategoryClickInterface{

    //886b5cae94ce4698aa5a2f266c9cdc65

    private RecyclerView newsRV, categoryRV;
    private ProgressBar loadingPB;
    private ArrayList<Articles> articlesArrayList;
    private ArrayList<CategoryRVModal> categoryRVModalArrayList;
    private CategoryRVAdapter categoryRVAdapter;
    private NewsRVAdapter newsRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        categoryRV = findViewById(R.id.idRVCategories);
        newsRV = findViewById(R.id.idRVNews);
        loadingPB = findViewById(R.id.idPBLoading);
        articlesArrayList = new ArrayList<>();
        categoryRVModalArrayList = new ArrayList<>();
        newsRVAdapter = new NewsRVAdapter(articlesArrayList, this);
        categoryRVAdapter = new CategoryRVAdapter(categoryRVModalArrayList,
                this, this::onCategoryClick);

        categoryRV.setAdapter(categoryRVAdapter);
        newsRV.setAdapter(newsRVAdapter);
        getCategories();
        getNews("All");
        newsRVAdapter.notifyDataSetChanged();
    }

    private void getCategories()
    {
        categoryRVModalArrayList.add(new CategoryRVModal("All", "https://images.unsplash.com/photo-1585829365295-ab7cd400c167?ixid=MnwxMjA3fDB8MHxzZWFyY2h8Nnx8bmV3c3xlbnwwfHwwfHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60"));
        categoryRVModalArrayList.add(new CategoryRVModal("Technology", "https://images.unsplash.com/photo-1605810230434-7631ac76ec81?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTl8fHRlY2hub2xvZ3l8ZW58MHx8MHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60"));
        categoryRVModalArrayList.add(new CategoryRVModal("Science", "https://images.unsplash.com/uploads/14124337748608b55570a/c40bbff6?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MzQ2fHxzY2llbmNlfGVufDB8fDB8fA%3D%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60"));
        categoryRVModalArrayList.add(new CategoryRVModal("Entertainment", "https://images.unsplash.com/photo-1603190287605-e6ade32fa852?ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8ZW50ZXJ0YWlubWVudHxlbnwwfHwwfHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60"));
        categoryRVModalArrayList.add(new CategoryRVModal("Sports", "https://images.unsplash.com/photo-1622629797619-c100e3e67e2e?ixid=MnwxMjA3fDB8MHxzZWFyY2h8Njl8fHNwb3J0c3xlbnwwfHwwfHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60"));
        categoryRVModalArrayList.add(new CategoryRVModal("General", "https://images.unsplash.com/photo-1483058712412-4245e9b90334?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTV8fGdlbmVyYWx8ZW58MHx8MHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60"));
        categoryRVModalArrayList.add(new CategoryRVModal("Health", "https://images.unsplash.com/photo-1494597564530-871f2b93ac55?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MzZ8fGhlYWx0aHxlbnwwfHwwfHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60"));
        categoryRVAdapter.notifyDataSetChanged();
    }

    private void getNews(String category){
        loadingPB.setVisibility(View.VISIBLE);
        articlesArrayList.clear();
        String categoryURL = "https://newsapi.org/v2/top-headlines?country=in&category="+category+"&apiKey=886b5cae94ce4698aa5a2f266c9cdc65";
        String url = "https://newsapi.org/v2/top-headlines?country=in&excludeDomains=stackoverflow.com&sortBy=publishedAt&language=en&apiKey=886b5cae94ce4698aa5a2f266c9cdc65";
        String BASE_URL = "https://newsapi.org/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        Call<NewsModal> call;
        if(category.equals("All")){
            call = retrofitAPI.getAllNews(url);
        }else{
            call = retrofitAPI.getNewsByCategory(categoryURL);
        }
        call.enqueue(new Callback<NewsModal>() {
            @Override
            public void onResponse(Call<NewsModal> call, Response<NewsModal> response) {
                NewsModal newsmodal = response.body();
                loadingPB.setVisibility(View.GONE);
                ArrayList<Articles> articles = newsmodal.getArticles();
                for(int i =0 ; i< articles.size(); i++){
                    articlesArrayList.add(new Articles(articles.get(i).getTitle(),articles.get(i).getDescription(), articles.get(i).getUrlToImage(),articles.get(i).getUrl(), articles.get(i).getContent()));
                }
                newsRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<NewsModal> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Fail to get News", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onCategoryClick(int position)
    {
        String category = categoryRVModalArrayList.get(position).getCategory();
        getNews(category);
    }
}