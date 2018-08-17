package com.ironghui.mydrawlayoutdemo;

public class ItemB {
    private int img;
    private String name;
    private int type;

    public ItemB(int img, String name, int type) {
        super();
        this.img = img;
        this.name = name;
        this.type = type;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}