package com.example.andrii.contacts.entity;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import java.util.Date;

public class Notification implements Comparable {
    private Bitmap contact_photo;
    private String contact_name;
    private String contact_number;
    private String nostif_type;
    private String nostif_state;
    private String sms_massage;
    private Date date;

    public Notification() {}

    public Notification(Call call) {
        this.contact_photo = call.getContact_photo();
        this.contact_name = call.getName();
        this.contact_number = call.getNumber();
        this.nostif_type = "call";
        this.nostif_state = call.getCall_type();
        this.date = call.getCall_time();
        this.sms_massage = "massage";
    }

    public Notification(Sms sms) {
        this.contact_photo = sms.getContact_photo();
        this.contact_name = sms.getName();
        this.contact_number = sms.getNumber();
        this.nostif_type = "sms";
        this.nostif_state = sms.getSms_state();
        this.date = sms.getSms_time();
        this.sms_massage = sms.getSms_massage();
    }

    public Notification(Bitmap contact_photo, String contact_name, String contact_number, String nostif_type, String nostif_state, String sms_massage, Date date) {
        this.contact_photo = contact_photo;
        this.contact_name = contact_name;
        this.contact_number = contact_number;
        this.nostif_type = nostif_type;
        this.nostif_state = nostif_state;
        this.sms_massage = sms_massage;
        this.date = date;
    }

    public String getSms_massage() {
        return sms_massage;
    }

    public void setSms_massage(String sms_massage) {
        this.sms_massage = sms_massage;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Bitmap getContact_photo() {
        return contact_photo;
    }

    public void setContact_photo(Bitmap contact_photo) {
        this.contact_photo = contact_photo;
    }

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public String getNostif_type() {
        return nostif_type;
    }

    public void setNostif_type(String nostif_type) {
        this.nostif_type = nostif_type;
    }

    public String getNostif_state() {
        return nostif_state;
    }

    public void setNostif_state(String nostif_state) {
        this.nostif_state = nostif_state;
    }

    @Override
    public int compareTo(@NonNull Object o) {
        if (o instanceof Notification) {
            Notification notification_temp = (Notification) o;
            return this.getDate().compareTo(((Notification) o).getDate());
        }else{
            return 1;
        }
    }
}
