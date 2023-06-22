package com.newsapp.newsapp_android_java.views.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.newsapp.newsapp_android_java.R;
import com.newsapp.newsapp_android_java.data.model.Article;
import com.newsapp.newsapp_android_java.listeners.ItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private Context context;
    private List<Article> articleList;
    private ItemClickListener mClickListener;

    public NewsAdapter(Context context, List<Article> articleList) {
        this.context = context;
        this.articleList = articleList;
    }

    public void setClickListener(ItemClickListener itemClickListener){
        this.mClickListener = itemClickListener;
    }


    public void setData(List<Article> articleList) {
        this.articleList = articleList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewsViewHolder(LayoutInflater.from(context).inflate(R.layout.news_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {

        holder.tv_title.setText(articleList.get(position).getTitle());
        holder.tv_source.setText(articleList.get(position).getSource().getName());

        if (articleList.get(position).getUrlToImage() != null){
            Picasso.get().load(articleList.get(position).getUrlToImage()).into(holder.iv_articleImage);
        }

    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }


    public class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView iv_articleImage;
        TextView tv_title,tv_source;
        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_articleImage = itemView.findViewById(R.id.iv_articleImage);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_source = itemView.findViewById(R.id.tv_source);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if(mClickListener !=null) mClickListener.onItemClick(v,getAdapterPosition());
        }
    }

}
