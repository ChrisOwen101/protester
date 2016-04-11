package com.protester.pojo;

public class Protest {

    private String name;
    private String host;
    private String location;
    private String imageUrl;
    private long startTime;
    private long endTime;
    private long timeStamp;

    @SuppressWarnings("unused")
    private Protest() {
    }

    public Protest(String name, String host, String location, String imageUrl, long startTime, long endTime) {
        this.name = name;
        this.host = host;
        this.location = location;
        this.startTime = startTime;
        this.endTime = endTime;
        this.timeStamp = System.currentTimeMillis()/1000;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getHost() {
        return host;
    }

    public String getImageUrl(){
        return imageUrl;
    }

    public String getLocation(){
        return location;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

}
