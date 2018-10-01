package com.example.andrii.contacts.entity;

import android.graphics.Bitmap;

import java.util.Date;

public class Sms{
    private Bitmap contact_photo;
    private String name;
    private String number;
    private Date sms_time;
    private String sms_state;
    private String sms_massage;

    public Sms(Sms objSms) {}

    public Sms(Bitmap contact_photo, String name, String number, Date sms_time, String sms_state, String sms_massage) {
        this.contact_photo = contact_photo;
        this.name = name;
        this.number = number;
        this.sms_time = sms_time;
        this.sms_state = sms_state;
        this.sms_massage = sms_massage;
    }

    public String getSms_massage() {
        return sms_massage;
    }

    public void setSms_massage(String sms_massage) {
        this.sms_massage = sms_massage;
    }

    public Bitmap getContact_photo() {
        return contact_photo;
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

    public Date getSms_time() {
        return sms_time;
    }

    public void setSms_time(Date sms_time) {
        this.sms_time = sms_time;
    }

    public String getSms_state() {
        return sms_state;
    }

    public void setSms_state(String sms_state) {
        this.sms_state = sms_state;
    }
}