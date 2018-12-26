package com.blogspot.officialceo.justin.Listener;

import com.blogspot.officialceo.justin.POJO.Website;

public interface NewsListener {

    void showSuccess(Website website);
    void showProgress();
    void hidProgress();
    void showErrorMessage(String message);
    void hideErrorMessage(String message);
    void setRefreshing(boolean b);


}
