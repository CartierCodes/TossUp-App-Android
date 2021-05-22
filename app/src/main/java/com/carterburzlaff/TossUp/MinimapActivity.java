package com.carterburzlaff.TossUp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONObject;

import java.lang.reflect.Array;
import java.net.URI;
import java.util.ArrayList;

public class MinimapActivity extends AppCompatActivity {
    private static final String TAG = "MinimapActivity";

    private ArrayList<GrenadeButton> grenadeButtons;
    private ArrayList<GrenadeButton> shownGrenades;
    private ImageView minimap;
    private int mapID;
    private RelativeLayout layout;
    private double lWidth;
    private double lHeight;
    private int iconSize;
    private Boolean buttonSelected = false;
    private GrenadeButton currentButton;
    private String currentType = "smoke";

    private final Integer[] minimaps = {
            R.drawable.dust2_minimap,
            R.drawable.mirage_minimap,
            R.drawable.inferno_minimap,
            R.drawable.cache_minimap,
            R.drawable.overpass_minimap,
            R.drawable.train_minimap,
            R.drawable.nuke_minimap,
            R.drawable.vertigo_minimap
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minimap);
        layout = findViewById(R.id.minimapLayout);

        mapID = getIntent().getIntExtra("map", 0);

        minimap = findViewById(R.id.minimap);
        minimap.setImageResource(minimaps[mapID]);
        minimap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonSelected) {
                    showGrenades();
                    buttonSelected = false;
                }
            }
        });

        grenadeButtons = new ArrayList<GrenadeButton>();
        shownGrenades = new ArrayList<GrenadeButton>();

        layout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Log.d(TAG, "Global Layout Changed");
                lWidth = layout.getWidth();
                lHeight = layout.getHeight();
                iconSize = (int) (lWidth * 0.05);
                convertDataToButtons();
                showGrenades();
                layout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    private void showGrenades() {
        clearScreen();
        shownGrenades = new ArrayList<GrenadeButton>();

        for (GrenadeButton nade : grenadeButtons) {
            if (nade.getType().toLowerCase().contains(currentType)) {
                shownGrenades.add(nade);
                layout.addView(nade);
            }
        }
    }

    private void convertDataToButtons() {
        for (GrenadeData nade : MyApplication.getGrenadeData(mapID)) {
            ArrayList<GrenadeButton> throwButtons = new ArrayList<GrenadeButton>();
            for (GrenadeData target : nade.getThrowArray()) {
                throwButtons.add(genGrenade(target, null));
            }
            grenadeButtons.add(genGrenade(nade, throwButtons));
        }
    }

    private GrenadeButton genGrenade(GrenadeData data, ArrayList<GrenadeButton> throwArray) {
        GrenadeButton nade = new GrenadeButton(this, data.getLocation(), data.getType(), data.getMap(), data.getXPos(), data.getYPos(), throwArray);
        RelativeLayout.LayoutParams parameters = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);

        parameters.leftMargin = (int) ((lWidth * data.getXPos()) - (iconSize / 2));
        parameters.topMargin = (int) ((lWidth * data.getYPos() + ((lHeight - lWidth) / 2)) - (iconSize / 2));
        parameters.width = iconSize;
        parameters.height = iconSize;
        nade.setLayoutParams(parameters);
        nade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSelected = true;
                if (nade.getType().equals("throw")) {
                    String type;
                    if (currentButton.getType().toLowerCase().contains("smoke")) type = "smoke";
                    else type = currentButton.getType();
                    String url = currentButton.getMap() + "/" + currentButton.getMap() + "_" + type + "_" + currentButton.getLocation() + "_from_" + nade.getLocation() + ".mp4";
                    Intent intent = new Intent(getApplicationContext(), VideoActivity.class);
                    intent.putExtra("url", url);
                    startActivity(intent);
                }
                else {
                    currentButton = nade;
                    clearScreen();
                    shownGrenades.add(nade);
                    layout.addView(nade);
                    for (GrenadeButton target : nade.getThrowArray()) {
                        shownGrenades.add(target);
                        layout.addView(target);
                    }
                }
            }
        });
        return nade;
    }

    private void clearScreen() {
        for (GrenadeButton nade : shownGrenades) {
            layout.removeView(nade);
        }
        shownGrenades = new ArrayList<GrenadeButton>();
    }

}