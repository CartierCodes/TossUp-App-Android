package com.carterburzlaff.TossUp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class LogoAdapter extends BaseAdapter {

    private Integer[] logos = {
            R.drawable.dust2_logo,
            R.drawable.mirage_logo,
            R.drawable.inferno_logo,
            R.drawable.cache_logo,
            R.drawable.overpass_logo,
            R.drawable.train_logo,
            R.drawable.nuke_logo,
            R.drawable.vertigo_logo
    };

    private String[] mapNames;
    private LayoutInflater logoInflator;

    public LogoAdapter(Context appContext, String[] mapNames) {
        super();

        this.mapNames = mapNames;
        this.logoInflator = LayoutInflater.from(appContext);
    }

    @Override
    public int getCount() {
        return logos.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) convertView = logoInflator.inflate(R.layout.logo_grid_item, null); //change to parent?
        ImageView logoImage = convertView.findViewById(R.id.imageView);
        logoImage.setImageResource(logos[position]);
        return convertView;
    }
}
