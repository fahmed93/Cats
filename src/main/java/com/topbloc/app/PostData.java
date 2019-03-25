package com.topbloc.app;

public class PostData {
    public String[] studentIds;
    public String name;
    public String id;
    public int average;

    public PostData(String id, String name, String[] studentIds, int average) {
        this.id = id;
        this.name = name;
        this.studentIds = studentIds;
        this.average = average;
    }
}
