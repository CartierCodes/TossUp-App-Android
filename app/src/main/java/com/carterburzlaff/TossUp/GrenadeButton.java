package com.carterburzlaff.TossUp;

import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;

import java.util.ArrayList;

public class GrenadeButton extends AppCompatImageButton {
    private String location;
    private String type;
    private String map;
    private double xPos;
    private double yPos;
    private ArrayList<GrenadeButton> throwArray;

    public GrenadeButton(@NonNull Context context, String location, String type, String map, double xPos, double yPos, ArrayList<GrenadeButton> throwArray) {
        super(context);
        this.location = location;
        this.type = type;
        this.map = map;
        this.xPos = xPos;
        this.yPos = yPos;
        this.throwArray = throwArray;
        setAttributes();
    }

    public GrenadeButton(@NonNull Context context, String location, String type, String map, double xPos, double yPos) {
        super(context);
        this.location = location;
        this.type = type;
        this.map = map;
        this.xPos = xPos;
        this.yPos = yPos;
        this.throwArray = new ArrayList<GrenadeButton>();
        setAttributes();
    }

    private void setAttributes() {
        setImage();
        setScaleType(ImageView.ScaleType.FIT_XY);
        setPadding(0,0,0,0);
        setBackground(null);
    }

    private void setImage() {
        if (type.equals("tSmoke")) setImageResource(R.drawable.tsmoke_icon);
        else if (type.equals("ctSmoke")) setImageResource(R.drawable.ctsmoke_icon);
        else if (type.equals("fire")) setImageResource(R.drawable.fire_icon);
        else if (type.equals("throw")) setImageResource(R.drawable.target_icon);
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

    public ArrayList<GrenadeButton> getThrowArray() {
        return throwArray;
    }

    public void setThrowArray(ArrayList<GrenadeButton> throwArray) {
        this.throwArray = throwArray;
    }

    @Override
    public String toString() {
        return "GrenadeButton{" +
                "location='" + location + '\'' +
                ", type='" + type + '\'' +
                ", map='" + map + '\'' +
                ", xPos=" + xPos +
                ", yPos=" + yPos +
                ", throwArray=" + throwArray +
                '}';
    }
}
