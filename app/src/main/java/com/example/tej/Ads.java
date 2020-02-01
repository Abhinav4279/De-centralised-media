package com.example.tej;

public class Ads {
    String Title;
    String desc;
    String url;
    String lid;

    public Ads(){

    }

    public Ads(String title, String desc, String url, String lid) {
        Title = title;
        this.desc = desc;
        this.url = url;
        this.lid = lid;
    }

    public String getTitle() {
        return Title;
    }

    public String getDesc() {
        return desc;
    }

    public String getUrl() {
        return url;
    }

    public String getLid() {
        return lid;
    }
}
