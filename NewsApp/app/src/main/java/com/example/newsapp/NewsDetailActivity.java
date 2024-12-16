package com.example.newsapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.squareup.picasso.Picasso;  // For loading images from URL
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NewsDetailActivity extends AppCompatActivity {

    // Views to display the news details
    private ImageView newsImage;
    private TextView newsTitle, newsDate, newsDescription, newsUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        // Initialize views
        newsImage = findViewById(R.id.newsImage);
        newsTitle = findViewById(R.id.newsTitle);
        newsDate = findViewById(R.id.newsDate);
        newsDescription = findViewById(R.id.newsDescription);
        newsUrl = findViewById(R.id.newsUrl);

        // Get the data passed from the MainActivity
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String description = intent.getStringExtra("description");
        String url = intent.getStringExtra("url");
        String imageUrl = intent.getStringExtra("imageUrl");
        String publishedAt = intent.getStringExtra("publishedAt");

        // Set the data to the views
        newsTitle.setText(title);
        newsDescription.setText(description);
        newsUrl.setText(url);

        // Format and display the published date
        String formattedDate = formatPublishedDate(publishedAt);
        newsDate.setText(formattedDate);

        // Load the image (Picasso handles image loading from URL)
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Picasso.get().load(imageUrl).into(newsImage);
        } else {
            // Default image if no image URL is provided
            Picasso.get().load(R.drawable.default_image).into(newsImage);
        }
    }

    // Method to format the publishedAt date
    private String formatPublishedDate(String publishedAt) {
        // The API usually provides date in format "yyyy-MM-dd'T'HH:mm:ss'Z'"
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());
        try {
            Date date = sdf.parse(publishedAt);
            // Format it in a more readable way: "MMM dd, yyyy HH:mm"
            SimpleDateFormat outputFormat = new SimpleDateFormat("MMM dd, yyyy HH:mm", Locale.getDefault());
            return outputFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return publishedAt;  // In case of parsing failure, return the raw date
        }
    }
}
