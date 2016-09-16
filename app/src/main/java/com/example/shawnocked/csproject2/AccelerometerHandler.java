package com.example.shawnocked.csproject2;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import java.util.Observable;

/**
 * Created by shawnocked on 9/15/16.
 */
public class AccelerometerHandler extends Observable
        implements SensorEventListener {

    private int THRESHOLD;
    private SensorManager sensorManager = null;
    private Sensor accel = null;
    private long prev_time = 0;
    public final static long ONE_SEC_IN_MILLIS = 1000;

    public AccelerometerHandler(int threshold, Activity act) {
        THRESHOLD = threshold;
        this.prev_time = System.currentTimeMillis();
        this.sensorManager = (SensorManager)
                act.getSystemService(Activity.SENSOR_SERVICE);
        this.accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(
                this,
                this.accel,
                SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        long curr_time = System.currentTimeMillis();

        if (curr_time - this.prev_time > ONE_SEC_IN_MILLIS) {
            this.prev_time = curr_time;
            setChanged();
            notifyObservers(sensorEvent.values);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) { }

}
