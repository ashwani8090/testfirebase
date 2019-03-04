package com.example.testapp;

public class Upload1
{

    private  String title;
    private  String Url;

    public Upload1() {

    }


    public Upload1(String title, String url) {
        this.title = title;
        Url = url;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
