package com.example.ggs.gpsview;

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
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private LocationManager mLocManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        simulateGps();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void simulateGps() {


        mLocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        mLocManager.addTestProvider(LocationManager.GPS_PROVIDER,
                "requiresNetwork" == "",
                "requiresSatellite" == "",
                "requiresCell" == "",
                "hasMonetaryCost" == "",
                "supportsAltitude" == "",
                "supportsSpeed" == "",
                "supportsBearing" == "",
                Criteria.NO_REQUIREMENT,
                Criteria.ACCURACY_COARSE);

        // 创建新的Location对象，并设定必要的属性值
        Location newLocation = new Location(LocationManager.GPS_PROVIDER);
        newLocation.setLatitude(116.457854);
        newLocation.setLongitude(39.92867);
        newLocation.setAccuracy(500);
        newLocation.setTime(System.currentTimeMillis());
        // 这里一定要设置nonasecond单位的值，否则是没法持续收到监听的，原因见下文
        newLocation.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());


        // 开启测试Provider
        mLocManager.setTestProviderEnabled(LocationManager.GPS_PROVIDER, true);

        mLocManager.setTestProviderStatus(LocationManager.GPS_PROVIDER,
                LocationProvider.AVAILABLE,
                null,
                System.currentTimeMillis());

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Log.d("fjdada", "premission denied");
            return;
        }
        mLocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                3000,
                10.0f,
                new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        Log.d("fjdada", "onLocationChanged : " + location.getLongitude() + " ," + location.getLatitude());
                    }

                    @Override
                    public void onStatusChanged(String provider, int status, Bundle extras) {
                        Log.d("fjdada", "onStatusChanged");
                    }

                    @Override
                    public void onProviderEnabled(String provider) {
                        Log.d("fjdada", "onProviderEnabled");
                    }

                    @Override
                    public void onProviderDisabled(String provider) {
                        Log.d("fjdada", "onProviderDisabled");
                    }
                });

//需要注册监听完成后再进行模拟定位点的设置，否则接受不到回调
//可以用handler postDelay方法

// 设置最新位置，一定要在requestLocationUpdate完成后进行，才能收到监听
        mLocManager.setTestProviderLocation(LocationManager.GPS_PROVIDER, newLocation);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = mLocManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        Log.d("fjdada", "lastKnownLocation : " + location.getLongitude() + " ," + location.getLatitude());
    }
}
