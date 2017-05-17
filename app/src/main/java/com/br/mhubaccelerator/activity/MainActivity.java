package com.br.mhubaccelerator.activity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.br.mhubaccelerator.domain.Acelerometro;
import com.br.mhubaccelerator.widget.AppUtils;
import com.br.mhubaccelerator.R;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.List;
import java.util.UUID;

import lac.cnclib.net.NodeConnection;
import lac.cnclib.net.NodeConnectionListener;
import lac.cnclib.net.mrudp.MrUdpNodeConnection;
import lac.cnclib.sddl.message.ApplicationMessage;
import lac.cnclib.sddl.message.Message;

public class MainActivity extends AppCompatActivity implements SensorEventListener, NodeConnectionListener {

    private TextView tvX, tvY, tvZ;
    private Sensor sensor;
    private SensorManager sensorManager;

    //private static String gatewayIP = "192.168.10.112";
    private static String gatewayIP = "200.137.134.98";
    private static int gatewayPort = 5501;
    private MrUdpNodeConnection connection;
    private AppUtils appUtils;
    private UUID myUUID = UUID.fromString(appUtils.generateUuid().toString());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InetSocketAddress address = new InetSocketAddress(gatewayIP, gatewayPort);
        try {
            connection = new MrUdpNodeConnection(myUUID);
            connection.addNodeConnectionListener(this);
            connection.connect(address);
        } catch (IOException e) {
            e.printStackTrace();
        }

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        tvX = (TextView) findViewById(R.id.tv_x);
        tvY = (TextView) findViewById(R.id.tv_y);
        tvZ = (TextView) findViewById(R.id.tv_z);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Acelerometro acelerometro = new Acelerometro();
        acelerometro.setEixoX(event.values[0]);
        acelerometro.setEixoY(event.values[1]);
        acelerometro.setEixoZ(event.values[2]);

        tvX.setText("X :" + acelerometro.getEixoX());
        tvY.setText("Y :" + acelerometro.getEixoY());
        tvZ.setText("Z :" + acelerometro.getEixoZ());
        if (acelerometro != null) {
            sendMsg(acelerometro);
        }

    }

    public void sendMsg(Acelerometro acelerometro) {
        ApplicationMessage message = new ApplicationMessage();
        message.setContentObject(acelerometro);
        try {
            connection.sendMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    @Override
    public void connected(NodeConnection nodeConnection) {

    }

    @Override
    public void reconnected(NodeConnection nodeConnection, SocketAddress socketAddress, boolean b, boolean b1) {

    }

    @Override
    public void disconnected(NodeConnection nodeConnection) {

    }

    @Override
    public void newMessageReceived(NodeConnection nodeConnection, Message message) {

    }

    @Override
    public void unsentMessages(NodeConnection nodeConnection, List<Message> list) {

    }

    @Override
    public void internalException(NodeConnection nodeConnection, Exception e) {

    }
}
