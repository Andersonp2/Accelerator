package com.br.mhubaccelerator;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  implements SensorEventListener {

    private TextView tvX, tvY, tvZ;
    private Sensor sensor;
    private SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);

        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        sensorManager.registerListener(this, sensor,SensorManager.SENSOR_DELAY_NORMAL);

        tvX = (TextView) findViewById(R.id.tv_x);
        tvY = (TextView) findViewById(R.id.tv_y);
        tvZ = (TextView) findViewById(R.id.tv_z);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        tvX.setText("X :" + event.values[0]);
        tvY.setText("Y :" + event.values[1]);
        tvZ.setText("Z :" + event.values[2]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
