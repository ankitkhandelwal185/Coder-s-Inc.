package app.the.iway.codersinc.recommendation;

import java.util.List;

/**
 * Created by Shashank on 03-Dec-16.
 */
public class TodolistModel {
    private String name, tags, url;


    public TodolistModel(List<TodolistModel> problemsList) {
    }

    public TodolistModel(String title, String tag) {
        this.name = title;
        this.tags = tag;

    }
    public TodolistModel(String name, String tags, String url ){
        this.name = name;
        this.tags = tags;
        this.url = url;

    }


    public String getTitleName() {
        return name;
    }

    public void setTitleName(String name) {
        this.tags = name;
    }

    public String getTags() {
        return tags;
    }

    public void setTag(String tag) {
        this.tags = tag;
    }
}

