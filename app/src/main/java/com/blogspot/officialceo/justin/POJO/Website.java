package com.blogspot.officialceo.justin.POJO;

import java.util.List;

public class Website {
    public  String status;
    public List<Sources> sources;

    public Website(){

    }

    public Website(String status, List<Sources> sources){

        this.status = status;
        this.sources = sources;

    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public List<Sources> getSources() {
        return sources;
    }

    public void setSources(List<Sources> sources) {
        this.sources = sources;
    }
}
