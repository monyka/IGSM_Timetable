package com.example.monika.igsm_timetable;

import java.io.Serializable;

//klasa użytkownika
public class DayActivityData implements Serializable {
    public  String activityDetails;
    public String activity_name;
    public String address;
    public String hours;
    public String place;
    public String placeDetails;
    public String lat;
    public String lng;


    public DayActivityData(String name, String hometown, String place, String activityDetails, String address, String placeDetails, String lat, String lng) {
        this.activityDetails = activityDetails;
        this.activity_name = name;
        this.address = address;
        this.hours = hometown;
        this.place = place;
        this.placeDetails = placeDetails;
        this.lat = lat;
        this.lng = lng;
    }
}