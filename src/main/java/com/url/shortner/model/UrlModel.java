package com.url.shortner.model;

import org.springframework.stereotype.Component;

@Component
public class UrlModel{
    private String shortCode;
    private String longUrl;
    private int clickCount;


    //Constructors

    public UrlModel(String shortCode, String longUrl, int clickCount){
        this.shortCode=shortCode;
        this.longUrl=longUrl;
        this.clickCount=clickCount;
    }
    public UrlModel(){

    }


    //Getters and Setters

    public String getShortCode() {
        return shortCode;
    }
    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }
    public String getLongUrl() {
        return longUrl;
    }
    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }
    public int getClickCount() {
        return clickCount;
    }
    public void setClickCount(int clickCount) {
        this.clickCount = clickCount;
    }
    

    
    //Methods to Use

    public void incrementCount(){
        clickCount++;
    }
}