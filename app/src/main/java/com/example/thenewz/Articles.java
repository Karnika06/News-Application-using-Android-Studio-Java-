package com.example.thenewz;

public class Articles {
    private String title;
    private String url;
    private String urlToImage;
    private String content;
    private String description;

    public Articles(String title, String url, String urlToImage, String content, String description) {
        this.title = title;
        this.url = url;
        this.urlToImage = urlToImage;
        this.content = content;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
