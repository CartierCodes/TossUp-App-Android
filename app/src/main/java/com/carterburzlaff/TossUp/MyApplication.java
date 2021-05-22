package com.carterburzlaff.TossUp;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MyApplication extends Application {
    private static final String TAG = "MyApplication";
    private static ArrayList<ArrayList<GrenadeData>> grenadeData;

    @Override
    public void onCreate() {
        super.onCreate();

        setGrenadeButtons();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    private void setGrenadeButtons() {
        grenadeData = new ArrayList<ArrayList<GrenadeData>>();
        String[] mapStrings = getResources().getStringArray(R.array.maps_lowercase);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("/maps");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterable<DataSnapshot> maps = snapshot.getChildren();
                //for (DataSnapshot map : maps) {    use this line if I want the order that the db is in
                for (String s : mapStrings) {
                    DataSnapshot map = snapshot.child(s);
                    Iterable<DataSnapshot> nadeTypes = map.getChildren();
                    ArrayList<GrenadeData> mapNades = new ArrayList<GrenadeData>();
                    for (DataSnapshot nadeType : nadeTypes) {
                        Iterable<DataSnapshot> nades = nadeType.getChildren();
                        for (DataSnapshot nade : nades) {
                            Iterable<DataSnapshot> throwNades = nade.child("/throws").getChildren();
                            ArrayList<GrenadeData> grenadeList = new ArrayList<GrenadeData>();
                            for (DataSnapshot g : throwNades) {
                                grenadeList.add(new GrenadeData(g.child("location").getValue().toString(), g.child("type").getValue().toString(), g.child("map").getValue().toString(), (double) g.child("x").getValue(), (double) g.child("y").getValue()));
                            }
                            mapNades.add(new GrenadeData(nade.child("location").getValue().toString(), nade.child("type").getValue().toString(), nade.child("map").getValue().toString(), (double) nade.child("x").getValue(), (double) nade.child("y").getValue(), grenadeList));
                        }
                    }
                    grenadeData.add(mapNades);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d(TAG, "Error getting data from Firebase Realtime Database: " + error.getCode());
            }
        });
    }

    public static ArrayList<ArrayList<GrenadeData>> getGrenadeData() {
        return grenadeData;
    }

    public static ArrayList<GrenadeData> getGrenadeData(int map) {
        return grenadeData.get(map);
    }
}
