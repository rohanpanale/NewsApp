/*package com.example.newsapp;

import android.os.Bundle;
import android.util.Log; // Import Log class for logging
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ListView newsListView;
    private NewsAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newsListView = findViewById(R.id.newsListView);

        NewsService newsService = NewsApiClient.getClient().create(NewsService.class);
        String apiKey = "b4fd6116f4a3433a9f2757bb3bb454de"; // Use your actual API key here
        Call<NewsModel> call = newsService.getTopHeadlines("us", apiKey);

        call.enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Log the response data for debugging
                    Log.d("API Response", "Status: " + response.body().getStatus());
                    Log.d("API Response", "Articles size: " + response.body().getArticles().size());

                    NewsModel newsModel = response.body();
                    newsAdapter = new NewsAdapter(MainActivity.this, newsModel.getArticles());
                    newsListView.setAdapter(newsAdapter);
                } else {
                    Toast.makeText(MainActivity.this, "Failed to fetch news", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}*/


package com.example.newsapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ListView newsListView;
    private NewsAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newsListView = findViewById(R.id.newsListView);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);

        // Set up navigation for categories
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nav_general) {
                // Fetch US general news as the main category
                fetchNews("us", null);
                return true;
            } else if (item.getItemId() == R.id.nav_sports) {
                // Fetch sports news under US category
                fetchNews("us", "sports");
                return true;
            } else if (item.getItemId() == R.id.nav_technology) {
                // Fetch technology news under US category
                fetchNews("us", "technology");
                return true;
            } else {
                return false;
            }
        });

        // Default category: Fetch general news for the US on app launch
        fetchNews("us", null);

        // Handle news item click to navigate to detailed view
        newsListView.setOnItemClickListener((parent, view, position, id) -> {
            NewsModel.Article selectedArticle = (NewsModel.Article) newsAdapter.getItem(position);
            Intent intent = new Intent(MainActivity.this, NewsDetailActivity.class);
            intent.putExtra("title", selectedArticle.getTitle());
            intent.putExtra("description", selectedArticle.getDescription());
            intent.putExtra("url", selectedArticle.getUrl());
            intent.putExtra("publishedAt", selectedArticle.getPublishedAt());  // Passing the publish date
            intent.putExtra("imageUrl", selectedArticle.getUrlToImage());  // Passing the image URL
            startActivity(intent);
        });

    }

    // Method to fetch news articles by category (General, Sports, Technology)
    private void fetchNews(String country, String category) {
        NewsService newsService = NewsApiClient.getClient().create(NewsService.class);
        String apiKey = "b4fd6116f4a3433a9f2757bb3bb454de";  // Use your API key here

        // Make sure the category is not null before making the API call
        Call<NewsModel> call;
        if (category != null) {
            // If category is provided, fetch news with category
            call = newsService.getTopHeadlinesWithCategory(country, category, apiKey);
        } else {
            // If category is null, fetch general news
            call = newsService.getTopHeadlines(country, apiKey);
        }

        // Make the API call asynchronously
        call.enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    NewsModel newsModel = response.body();
                    Log.d("API Response", "Number of articles: " + newsModel.getArticles().size());  // Log to check if data is fetched
                    newsAdapter = new NewsAdapter(MainActivity.this, newsModel.getArticles());
                    newsListView.setAdapter(newsAdapter);
                } else {
                    Toast.makeText(MainActivity.this, "Failed to fetch news", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
