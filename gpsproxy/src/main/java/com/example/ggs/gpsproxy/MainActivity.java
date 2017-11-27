package com.example.ggs.gpsproxy;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.net.URLEncoder;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {

    private LocationTracker locationTracker;
    private TextView content;
    private LocationManager mLocManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        content = (TextView) findViewById(R.id.tv_contet);
        getLocation();
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Log.d("fjdada", "primission denied");
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION) && ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION)) {
                // TODO: show explanation
                Log.d("fjdada", "shouldShowRequestPermissionRationale");

            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                        1000);
                Log.d("fjdada", "requestPermissions");
            }

        } else {
            Log.d("fjdada", "Permissions genered");
            locationTracker = new LocationTracker(MainActivity.this) {
                @Override
                protected void onLocationFound(@NonNull double longitude, @NonNull double latitude, @NonNull double altitude, @NonNull String s) {
                    Log.d("fjdada", "onLocationFound");
                    if (locationTracker != null) {
                        locationTracker.stopListening();
                        locationTracker = null;
                        String mLongitude = String.valueOf(longitude);
                        String mLatitude = String.valueOf(latitude);
                        content.append(mLongitude);
                        content.append(" | ");
                        content.append(mLatitude);
                    }
                }

                @Override
                public void onTimeout() {
                    Log.d("fjdada", "onTimeout");
                    if (locationTracker != null) {
                        locationTracker.stopListening();
                        locationTracker = null;
                    }
                }
            };
            locationTracker.startListening();
            Log.d("fjdada", "location start tracker");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 1000: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the task you need to do.
                    int a = 1;
                } else {
                    // permission denied, boo! Disable the functionality that depends on this permission.
                    int b = 1;
                }
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (locationTracker != null) {
            Location lastLocation = locationTracker.getLastLocation();
            if (lastLocation != null) {
                content.setText("");
                content.append(lastLocation.getLongitude() + "");
                content.append(" | ");
                content.append(lastLocation.getLatitude() + "");
            }
        }
    }
}
