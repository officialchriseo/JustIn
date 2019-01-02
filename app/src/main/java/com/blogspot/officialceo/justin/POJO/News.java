package com.blogspot.officialceo.justin.POJO;

import java.util.List;

public class News {

    private String source;
    private String totalResults;
    private List<Articles> article;

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
        return article;
    }

    public void setArticles(List<Articles> article) {
        this.article = article;
    }
}
