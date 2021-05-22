package com.carterburzlaff.TossUp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

public class MapSelectionActivity extends AppCompatActivity {
    private static final String TAG = "MapSelectionActivity";
    private GridView mGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] maps = getResources().getStringArray(R.array.maps);

        mGridView = findViewById(R.id.logoGridView);
        mGridView.setAdapter(new LogoAdapter(getApplicationContext(), maps));
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, String.valueOf(position));

                Intent intent = new Intent(getApplicationContext(), MinimapActivity.class);
                intent.putExtra("map", position);
                startActivity(intent);
            }
        });


    }
}