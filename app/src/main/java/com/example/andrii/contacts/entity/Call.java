package com.example.andrii.contacts.entity;

import android.graphics.Bitmap;

import java.util.Date;

public class Call {
    private Bitmap contact_photo;
    private String name;
    private String number;
    private Date call_time;
    private String call_type;
    private String call_duration;

    public Call() {}

    public Call(Bitmap contact_photo, String name, String number, Date call_time, String call_type, String call_duration) {
        this.contact_photo = contact_photo;
        this.name = name;
        this.number = number;
        this.call_time = call_time;
        this.call_type = call_type;
        this.call_duration = call_duration;
    }

    public Bitmap getContact_photo() {
        return contact_photo;
    }

    public Date getCall_time() {
        return call_time;
    }

    public void setCall_time(Date call_time) {
        this.call_time = call_time;
    }

    public void setContact_photo(Bitmap contact_photo) {
        this.contact_photo = contact_photo;
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


    public String getCall_type() {
        return call_type;
    }

    public void setCall_type(String call_type) {
        this.call_type = call_type;
    }

    public String getCall_duration() {
        return call_duration;
    }

    public void setCall_duration(String call_duration) {
        this.call_duration = call_duration;
    }
}
