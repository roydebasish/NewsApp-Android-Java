package com.newsapp.newsapp_android_java.views.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.newsapp.newsapp_android_java.R;
import com.newsapp.newsapp_android_java.listeners.ItemClickListener;
import com.newsapp.newsapp_android_java.views.adapter.NewsAdapter;
import com.newsapp.newsapp_android_java.data.model.Article;
import com.newsapp.newsapp_android_java.data.model.TopHeadlineResponse;
import com.newsapp.newsapp_android_java.data.network.ApiInterface;
import com.newsapp.newsapp_android_java.data.network.RetrofitClient;
import com.newsapp.newsapp_android_java.databinding.ActivityMainBinding;
import com.newsapp.newsapp_android_java.utils.Constants;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements ItemClickListener , View.OnClickListener {
    ActivityMainBinding binding;

    String country = "us";
    String category = "general";
    String query = null;
    NewsAdapter newsAdapter;
    LinearLayoutManager linearLayoutManager;
    List<Article> articleList;

    ApiInterface apiInterface;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Fetching News");
        progressDialog.show();


        apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);

        initialize();
        getNews(country,category,query);


        binding.btnBusiness.setOnClickListener(this);
        binding.btnEntertainment.setOnClickListener(this);
        binding.btnGeneral.setOnClickListener(this);
        binding.btnHealth.setOnClickListener(this);
        binding.btnScience.setOnClickListener(this);
        binding.btnSports.setOnClickListener(this);
        binding.btnTechnology.setOnClickListener(this);


        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                progressDialog.setTitle("Fetching the News of "+query);
                progressDialog.show();

                getNews(country,category,query);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }

    private void initialize() {

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.recyclerview.setHasFixedSize(true);
        binding.recyclerview.setLayoutManager(linearLayoutManager);


    }

    private void getNews(String country,String category,String query) {

        apiInterface.getHeadlines(country,category,query, Constants.API_KEY).enqueue(new Callback<TopHeadlineResponse>() {
            @Override
            public void onResponse(Call<TopHeadlineResponse> call, Response<TopHeadlineResponse> response) {

                progressDialog.dismiss();

                if (response.isSuccessful()){

                    TopHeadlineResponse topHeadlineResponse = response.body();

                    articleList = topHeadlineResponse.getArticles();
                    if (articleList.size() > 0) {
                        showNews(articleList);
                    }

                }
            }

            @Override
            public void onFailure(Call<TopHeadlineResponse> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    private void showNews(List<Article> articleList) {
        newsAdapter = new NewsAdapter(this, articleList);
//        newsAdapter.setData(articleList);
        binding.recyclerview.setAdapter(newsAdapter);
        newsAdapter.setClickListener(this);
    }


    @Override
    public void onItemClick(View view, int position) {

        Article article = articleList.get(position);

        startActivity(new Intent(getApplicationContext(),DetaisActivity.class)
                .putExtra("news", article));
    }

    @Override
    public void onClick(View v) {

        Button button = (Button) v;
        category = button.getText().toString().trim();

        progressDialog.setTitle("Fetching "+category+" News");
        progressDialog.show();


        getNews(country,category,query);
    }
}