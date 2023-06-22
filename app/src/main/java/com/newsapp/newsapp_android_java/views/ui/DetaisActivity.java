package com.newsapp.newsapp_android_java.views.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.newsapp.newsapp_android_java.R;
import com.newsapp.newsapp_android_java.data.model.Article;
import com.newsapp.newsapp_android_java.databinding.ActivityDetaisBinding;
import com.newsapp.newsapp_android_java.databinding.ActivityMainBinding;
import com.squareup.picasso.Picasso;

public class DetaisActivity extends AppCompatActivity {

    ActivityDetaisBinding binding;

    Article article;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetaisBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        article = (Article) getIntent().getSerializableExtra("news");

        binding.tvTitle.setText(article.getTitle());
        binding.tvDate.setText(article.getPublishedAt());
        binding.tvDescription.setText(article.getDescription());

        if (article.getAuthor() != null){
            binding.tvAuthor.setText(article.getAuthor().toString());
        }else {
            binding.tvAuthor.setText(article.getSource().getName());
        }

        Picasso.get().load(article.getUrlToImage()).into(binding.ivArticleImage);
    }
}