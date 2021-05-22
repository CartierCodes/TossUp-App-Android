package com.carterburzlaff.TossUp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity {
    private static final String TAG = "VideoActivity";

    private VideoView videoView;
    private Uri uri;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        if (getSupportActionBar() != null) this.getSupportActionBar().hide();

        videoView = findViewById(R.id.video_view);

        setPrefs();

        String url = getString(R.string.googleVideoURL) + getIntent().getStringExtra("url");
        Log.d(TAG, url);
        uri = Uri.parse(url);

        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (videoView.isPlaying()) {
                    videoView.pause();
                    Log.d(TAG, "pausing...");
                }
                else {
                    videoView.resume();
                    Log.d(TAG, "resuming...");
                }
            }
        });
        videoView.start();
    }

    private void setPrefs() {
        preferences = getSharedPreferences("PREFS", 0);

        if (preferences.getBoolean("landscapeOnly", true)) {
            if (preferences.getBoolean("landscapeLeft", true))
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            else
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
        }
        else {
            if (preferences.getBoolean("landscapeLeft", true))
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }

        if (!preferences.getBoolean("autoplayVideo", true)) {
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    videoView.pause();
                    Log.d(TAG, "Video paused because autoplay is disabled");
                }
            });
        }


    }
}