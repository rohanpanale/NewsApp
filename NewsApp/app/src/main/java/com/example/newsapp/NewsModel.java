package com.example.newsapp;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class NewsModel {

    @SerializedName("status")
    private String status;

    @SerializedName("totalResults")
    private int totalResults;

    @SerializedName("articles")
    private List<Article> articles;

    // Getter and Setter for status
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Getter and Setter for totalResults
    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    // Getter and Setter for articles
    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    // Article inner class to map individual article objects
    public class Article {
        private String title;
        private String description;
        private String url;
        private String urlToImage; // Add the urlToImage field
        private String publishedAt;

        // Getter and Setter for title
        public String getTitle() {
            return title;
        }
        public void setTitle(String title) {
            this.title = title;
        }

        // Getter and Setter for description
        public String getDescription() {
            return description;
        }
        public void setDescription(String description) {
            this.description = description;
        }

        // Getter and Setter for url
        public String getUrl() {
            return url;
        }
        public void setUrl(String url) {
            this.url = url;
        }

        // Getter and Setter for urlToImage (image URL)
        public String getUrlToImage() {
            return urlToImage;
        }
        public void setUrlToImage(String urlToImage) {
            this.urlToImage = urlToImage;
        }

        // Getter and Setter for publishedAt (publication date)
        public String getPublishedAt() {
            return publishedAt;
        }
        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }
    }
}
