package com.carterburzlaff.TossUp;

import android.content.Context;
import android.widget.Button;

import androidx.appcompat.widget.AppCompatButton;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class GrenadeData {
    private String location;
    private String type;
    private String map;
    private double xPos;
    private double yPos;
    private ArrayList<GrenadeData> throwArray;

    public GrenadeData(String location, String type, String map, double xPos, double yPos, ArrayList<GrenadeData> throwArray) {
        this.location = location;
        this.type = type;
        this.map = map;
        this.xPos = xPos;
        this.yPos = yPos;
        this.throwArray = throwArray;
    }

    public GrenadeData(String location, String type, String map, double xPos, double yPos) {
        this.location = location;
        this.type = type;
        this.map = map;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public double getXPos() {
        return xPos;
    }

    public void setXPos(double xPos) {
        this.xPos = xPos;
    }

    public double getYPos() {
        return yPos;
    }

    public void setYPos(double yPos) {
        this.yPos = yPos;
    }

    public ArrayList<GrenadeData> getThrowArray() {
        return throwArray;
    }

    public void setThrowArray(ArrayList<GrenadeData> throwArray) {
        this.throwArray = throwArray;
    }

    @Override
    public String toString() {
        return "GrenadeData{" +
                "location='" + location + '\'' +
                ", type='" + type + '\'' +
                ", map='" + map + '\'' +
                ", xPos=" + xPos +
                ", yPos=" + yPos +
                ", throwArray=" + throwArray +
                '}';
    }
}
