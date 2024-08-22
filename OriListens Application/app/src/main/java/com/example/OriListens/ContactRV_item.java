package com.example.OriListens;

public class ContactRV_item {
    private int Image;
    private String name;
    private String location;
    private String contact;

    public ContactRV_item(int image, String name, String location, String contact) {
        Image = image;
        this.name = name;
        this.location = location;
        this.contact = contact;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
