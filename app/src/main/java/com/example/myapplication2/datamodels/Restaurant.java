package com.example.myapplication2.datamodels;

public class Restaurant {
    private String name, address, phoneNumber;
    private int image, minOrder;


    public Restaurant(String name, String address, String phoneNumber, int image, int minOrder){
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.image = image;
        this.minOrder= minOrder;
    }

    public int getMinOrder() {
        return minOrder;
    }

    public void setMinOrder(int minOrder) {
        this.minOrder = minOrder;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}

