package com.example.testapp;


public class Information {
    private String Name;
    private String Phone;
    private String Email;
    private  String image;


    public Information(String name, String phone, String email,String image) {
        Name = name;
        Phone = phone;
        Email = email;
        this.image=image;
    }

    public Information() {



    }

    public String getName() { return Name; }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
