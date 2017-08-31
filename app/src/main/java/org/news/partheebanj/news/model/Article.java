package org.news.partheebanj.news.model;

/**
 * Created by partheebanj on 8/30/17.
 */

public class Article {

    private String Title = "";
    private String Author = "";
    private String Description = "";
    private String URL = "";
    private String imageToURL = "";
    private String publishedAt = "";

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getImageToURL() {
        return imageToURL;
    }

    public void setImageToURL(String imageToURL) {
        this.imageToURL = imageToURL;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }
}
