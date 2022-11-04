package com.rhmn.movies.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResponceGeners {

    @SerializedName("id")
    private int id = 0;

    @SerializedName("name")
    private String name = "";

    private boolean isClick = false;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isClick() {
        return isClick;
    }

    public void setClick(boolean click) {
        isClick = click;
    }
}
