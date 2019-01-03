package com.blogspot.officialceo.justin.POJO;

import java.util.List;

public class News {

    private String source;
    private String totalResults;
    private List<Articles> articles;

    public News() {
    }

    public News(String source, String totalResults, List<Articles> article) {
        this.source = source;
        this.totalResults = totalResults;
        this.articles = article;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public List<Articles> getArticles() {
        return articles;
    }

    public void setArticles(List<Articles> article) {
        this.articles = article;
    }
}
