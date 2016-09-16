package com.example.shawnocked.csproject2;

import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer {

    TextView z_accel_view = null;
    TextView y_accel_view = null;
    TextView x_accel_view = null;
    TextView lon_location_view = null;
    TextView lat_location_view = null;
    private AccelerometerHandler ah;
    private LocationHandler lh;

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
        this.ah = new AccelerometerHandler(10000, this);
        this.ah.addObserver(this);
        this.lh = new LocationHandler(this);
        this.lh.addObserver(this);
    }

    @Override
    public void update(Observable observable, Object o) {
        if(observable instanceof AccelerometerHandler) {
            float[] xyzValues = (float[]) o;
            x_accel_view.setText(Float.toString(xyzValues[0]));
            y_accel_view.setText(Float.toString(xyzValues[1]));
            z_accel_view.setText(Float.toString(xyzValues[2]));
        }

        else {
            Location location = (Location) o;
            lat_location_view.setText(Double.toString(location.getLatitude()));
            lon_location_view.setText(Double.toString(location.getLongitude()));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
