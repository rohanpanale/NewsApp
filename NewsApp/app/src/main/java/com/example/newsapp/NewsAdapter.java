package com.example.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.BaseAdapter;
import com.squareup.picasso.Picasso;
import java.util.List;

public class NewsAdapter extends BaseAdapter {
    private Context context;
    private List<NewsModel.Article> articles;

    public NewsAdapter(Context context, List<NewsModel.Article> articles) {
        this.context = context;
        this.articles = articles;
    }

    @Override
    public int getCount() {
        return articles.size();
    }

    @Override
    public Object getItem(int position) {
        return articles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_news, parent, false);
        }

        NewsModel.Article article = articles.get(position);

        TextView titleTextView = convertView.findViewById(R.id.titleTextView);
        TextView descriptionTextView = convertView.findViewById(R.id.descriptionTextView);
        ImageView imageView = convertView.findViewById(R.id.imageView);

        titleTextView.setText(article.getTitle());
        descriptionTextView.setText(article.getDescription());

        // Load image with Picasso
        Picasso.get().load(article.getUrlToImage()).into(imageView);

        return convertView;
    }
}
