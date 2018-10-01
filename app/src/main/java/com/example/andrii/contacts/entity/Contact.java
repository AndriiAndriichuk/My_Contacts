
package com.example.andrii.contacts.entity;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class Contact {
    private Bitmap photo;
    private String name;
    private String number;
    private String email;
    private ArrayList<Call> calls;
    private ArrayList<Sms> sms;

    public Contact() {}

    public Contact(Bitmap photo,String name,String number) {
        this.photo = photo;
        this.name = name;
        this.number = number;
        this.email = "email";
        this.calls = new ArrayList<>();
        this.sms = new ArrayList<>();
    }


    public Contact(Bitmap photo,String name,String number,String email,ArrayList<Call> calls, ArrayList<Sms> sms) {
        this.photo = photo;
        this.name = name;
        this.number = number;
        this.email = email;
        this.calls = calls;
        this.sms = sms;
    }

    public ArrayList<Call> getCalls() {
        return calls;
    }

    public void setCalls(ArrayList<Call> calls) {
        this.calls = calls;
    }

    public ArrayList<Sms> getSms() {
        return sms;
    }

    public void setSms(ArrayList<Sms> sms) {
        this.sms = sms;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

}
