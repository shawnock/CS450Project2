package com.example.shawnocked.csproject2;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import java.util.Observable;

/**
 * Created by shawnocked on 9/15/16.
 */
public class LocationHandler extends Observable
        implements LocationListener {

    private static final int MY_PERMISSIONS_REQUEST_FINE_LOCATION = 1;
    private static final int MY_PERMISSIONS_REQUEST_COARSE_LOCATION = 2;
    private LocationManager locationManager = null;
    private Location location = null;
    private double longitude;
    private double latitude;
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 0;
    private static final long MIN_TIME_BETWEEN_UPDATES = 1;


    public LocationHandler(Activity act) {
        this.locationManager = (LocationManager) act.getSystemService(Activity.LOCATION_SERVICE);

        // if we do have a permission, do what we need to do.
        // if we don't have a permission, we request a permission and then do what we need to do.
        if(ActivityCompat.checkSelfPermission(act, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(act, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(act, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_FINE_LOCATION);
            ActivityCompat.requestPermissions(act, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, MY_PERMISSIONS_REQUEST_COARSE_LOCATION);}

        this.location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        this.location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        this.longitude = this.location.getLongitude();
        this.latitude = this.location.getLatitude();
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BETWEEN_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,MIN_TIME_BETWEEN_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

    }

    @Override
    public void onLocationChanged(Location location) {

        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
        setChanged();
        notifyObservers(location);

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

}