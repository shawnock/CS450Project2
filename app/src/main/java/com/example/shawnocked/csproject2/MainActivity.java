package com.example.shawnocked.csproject2;

import android.hardware.SensorEventListener;
import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity implements Observer {

    TextView z_accel_view = null;
    TextView y_accel_view = null;
    TextView x_accel_view = null;
    TextView lon_location_view = null;
    TextView lat_location_view = null;
    private AccelerometerHandler ah;
    private LocationHandler lh;
    private float []xyzValues;
    private Location location;
    private String x = "X";
    private String y = "Y";
    private String z = "Z";
    private String lat = "LAT";
    private String lon = "LON";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.z_accel_view =
                (TextView) findViewById(R.id.z_accel_view);
        this.y_accel_view =
                (TextView) findViewById(R.id.y_accel_view);
        this.x_accel_view =
                (TextView) findViewById(R.id.x_accel_view);
        this.lon_location_view =
                (TextView) findViewById(R.id.lon_location_view);
        this.lat_location_view =
                (TextView) findViewById(R.id.lat_location_view);
        this.ah = new AccelerometerHandler(this);
        this.ah.addObserver(this);
        this.lh = new LocationHandler(this);
        this.lh.addObserver(this);
        this.location = lh.getLocation();
    }



    @Override
    protected void onStart() {
        super.onStart();
        String x = getPreferences(MODE_PRIVATE).getString(this.x, null);
        String y = getPreferences(MODE_PRIVATE).getString(this.y, null);
        String z = getPreferences(MODE_PRIVATE).getString(this.z, null);
        String lat = getPreferences(MODE_PRIVATE).getString(this.lat, null);
        String lon = getPreferences(MODE_PRIVATE).getString(this.lon, null);

        x_accel_view.setText(x);
        y_accel_view.setText(y);
        z_accel_view.setText(z);
        lat_location_view.setText(lat);
        lon_location_view.setText(lon);
    }

    @Override
    protected void onPause() {
        super.onPause();

        getPreferences(MODE_PRIVATE).edit().putString(x, Float.toString(this.xyzValues[0])).apply();
        getPreferences(MODE_PRIVATE).edit().putString(y, Float.toString(this.xyzValues[1])).apply();
        getPreferences(MODE_PRIVATE).edit().putString(z, Float.toString(this.xyzValues[2])).apply();
        getPreferences(MODE_PRIVATE).edit().putString(lat, Double.toString(this.location.getLatitude())).apply();
        getPreferences(MODE_PRIVATE).edit().putString(lon, Double.toString(this.location.getLongitude())).apply();

    }
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void update(Observable observable, Object o) {
        if(observable instanceof AccelerometerHandler) {
            this.xyzValues = (float[]) o;
            x_accel_view.setText(Float.toString(xyzValues[0]));
            y_accel_view.setText(Float.toString(xyzValues[1]));
            z_accel_view.setText(Float.toString(xyzValues[2]));
        }

        if(observable instanceof LocationHandler) {
            this.location = (Location) o;
            lat_location_view.setText(Double.toString(location.getLatitude()));
            lon_location_view.setText(Double.toString(location.getLongitude()));
        }
    }
}
