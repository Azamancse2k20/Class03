package com.example.class03;

public class MenuModel {

    public String menName;
    public Boolean hasChild, isGroup;

    public MenuModel(String menName,
                     Boolean hasChild, Boolean isGroup) {
        this.menName = menName;
        this.hasChild = hasChild;
        this.isGroup = isGroup;
    }
}
