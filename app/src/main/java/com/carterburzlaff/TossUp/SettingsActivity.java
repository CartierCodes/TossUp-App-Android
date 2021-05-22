package com.carterburzlaff.TossUp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.preference.PreferenceManager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class SettingsActivity extends AppCompatActivity {
    private static final String TAG = "SettingsActivity";

    private SharedPreferences preferences;
    private SwitchCompat landscapeOnly;
    private SwitchCompat landscapeLeft;
    private SwitchCompat autoplayVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        preferences = getSharedPreferences("PREFS", 0);

        landscapeOnly = findViewById(R.id.landscape_only);
        landscapeLeft = findViewById(R.id.landscape_left_default);
        autoplayVideo = findViewById(R.id.autoplay_video);

        landscapeOnly.setChecked(preferences.getBoolean("landscapeOnly", true));
        landscapeLeft.setChecked(preferences.getBoolean("landscapeLeft", true));
        autoplayVideo.setChecked(preferences.getBoolean("autoplayVideo", true));

        landscapeOnly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean newVal = !landscapeOnly.isChecked();
                landscapeOnly.setChecked(newVal);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("landscapeOnly", newVal);
                editor.apply();
            }
        });

        landscapeLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean newVal = !landscapeLeft.isChecked();
                landscapeLeft.setChecked(newVal);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("landscapeLeft", newVal);
                editor.apply();
            }
        });

        autoplayVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean newVal = !autoplayVideo.isChecked();
                autoplayVideo.setChecked(newVal);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("autoplayVideo", newVal);
                editor.apply();
            }
        });
    }
}