package com.ironghui.mydrawlayoutdemo;

public class ItemType {
    private String name;
    private String type;
    private String number;

    public ItemType(String name, String type, String number) {
        this.name = name;
        this.type = type;
        this.number = number;
    }

    @Override
    public String toString() {
        return "ItemType{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", number='" + number + '\'' +
                '}';
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


}
