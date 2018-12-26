package com.blogspot.officialceo.justin.POJO;

import java.util.List;

public class Website {
    public  String status;
    public List<Source> source;

    public Website(){

    }

    public Website(String status, List<Source> source){

        this.status = status;
        this.source = source;

    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public List<Source> getSource() {
        return source;
    }

    public void setSource(List<Source> source) {
        this.source = source;
    }
}
