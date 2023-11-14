package com.example.OriListens;

public class SecondRV_item {
    private String Image;
    private String text;
    private String link;

    public SecondRV_item(String image, String text, String link) {
        this.Image = image;
        this.text = text;
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
