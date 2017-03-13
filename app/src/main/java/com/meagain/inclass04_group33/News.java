package com.meagain.inclass04_group33;

import org.json.JSONException;
import org.json.JSONObject;

/*
* Megan Reiffer, Molly-Marie Frye
*/
public class News {
    String urlToImage;
    String title;
    String author;
    String description;
    String publishedAt;
    String url;

    @Override
    public String toString() {
        return "News{" +
                "author='" + author + '\'' +
                ", urlToImage='" + urlToImage + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public static News createNews(JSONObject js) throws JSONException {
        News news = new News();
        news.setAuthor(js.getString("author"));
        news.setDescription(js.getString("description"));
        news.setUrl(js.getString("url"));
        news.setPublishedAt(js.getString("publishedAt"));
        news.setTitle(js.getString("title"));
        news.setUrlToImage(js.getString("urlToImage"));
        return news;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }
}
