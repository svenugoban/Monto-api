package com.monto.api.demo.model.Function;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document("News feeds Data")
public class NewsFeedUpload {

    @Id
    private String id;
    private String ImageUrl;
    private String androidurl;
    private String description;
    private String date;
    private String name;


    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAndroidurl() {
        return androidurl;
    }

    public void setAndroidurl(String androidurl) {
        this.androidurl = androidurl;
    }

    public NewsFeedUpload() {
    }

    public NewsFeedUpload(String name,String description,String imageUrl ,String androidurlurl ) {
        ImageUrl = imageUrl;
        this.androidurl = androidurlurl;
        this.name= name;
        this.description = description;

    }
}
