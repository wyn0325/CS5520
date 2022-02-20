package com.example.numad22sp_yinanwang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationListener;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;

public class Locator extends AppCompatActivity {
    private LocationManager locationManager;
    private String locationProvider = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.locator);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            //request
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION}, 0);
        }


        //location manager
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        //provider
        List<String> providers = locationManager.getProviders(true);

        if (providers.contains(LocationManager.GPS_PROVIDER)) {
            locationProvider = LocationManager.GPS_PROVIDER;
        } else if (providers.contains(LocationManager.NETWORK_PROVIDER)) {
            locationProvider = LocationManager.NETWORK_PROVIDER;
        } else {
            Toast.makeText(this, "No Avaliable Locator", Toast.LENGTH_SHORT).show();
            return;
        }

        //get location
        locationManager.requestLocationUpdates(locationProvider, 100, 1, locationListener);
        Location location = locationManager.getLastKnownLocation(locationProvider);
        if (location != null) {
            changeLocation(location);
        }
    }

    public void changeLocation(Location location){
        //new Longitude
        TextView loshow=(TextView)findViewById(R.id.loshow);
        loshow.setText(String.valueOf(location.getLongitude()));
        //new Latitude
        TextView lashow=(TextView)findViewById(R.id.lashow);
        lashow.setText(String.valueOf(location.getLatitude()));
    }

    public LocationListener locationListener = new LocationListener() {
        //status change
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
        // enable
        @Override
        public void onProviderEnabled(String provider) {
        }
        // disable
        @Override
        public void onProviderDisabled(String provider) {
        }
        //location change
        @Override
        public void onLocationChanged(Location location) {
            if (location != null) {
                changeLocation(location);
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        locationManager.removeUpdates(locationListener);
    }


}