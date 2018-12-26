package com.blogspot.officialceo.justin.POJO;



class UrlToImage{
    private String image;

    public UrlToImage() {
    }

    public UrlToImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}



public class Source {

    private String id;
    private String description;
    private String name;
    private String author;
    private String title;
    private String url;
    private String publishedAt;
    private String content;
    private UrlToImage urlToImage;


    public Source() {
    }

    public Source(String id, String description, String name, String author,
                  String title, String url, String publishedAt, String content,
                  UrlToImage urlToImage) {


        this.id = id;
        this.description = description;
        this.name = name;
        this.author = author;
        this.title = title;
        this.url = url;
        this.publishedAt = publishedAt;
        this.content = content;
        this.urlToImage = urlToImage;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UrlToImage getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(UrlToImage urlToImage) {
        this.urlToImage = urlToImage;
    }
}
