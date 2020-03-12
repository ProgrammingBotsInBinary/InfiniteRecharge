package com.example.infiniterecharge;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.wifi.WpsInfo;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.se.omapi.Channel;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static com.example.infiniterecharge.Variables.*;

public class SendActivity extends AppCompatActivity {

    DbHelper myDb;
    WifiP2pManager manager;
    WifiP2pManager.Channel channel;
    BroadcastReceiver receiver;
    IntentFilter intentFilter;
    WifiP2pDevice device;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
        myDb = new DbHelper(this);

        intentFilter = new IntentFilter();
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);

        manager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
        channel = manager.initialize(this, getMainLooper(), null);
        //receiver = new WifiDirectBroadcastReceiver(manager, channel, this);

        Button deleteData = (Button) findViewById(R.id.deleteact_button);
        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDb.deleteDb(getApplicationContext());

                Cursor res = myDb.getData();
                if(res.getCount() == 0){
                    Toast.makeText(SendActivity.this, "Deleted", Toast.LENGTH_LONG).show();
                }
            }
        });

        Button sendbutton = (Button) findViewById(R.id.sendactivity_button);
        sendbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean check = myDb.insertData(team_number, match_number, auton_lvl1, auton_lvl2, auton_lvl3, auton_line, auton_position,
                        teleop_success_lvl1, teleop_success_lvl2, teleop_success_lvl3, teleop_fail_lvl1, teleop_fail_lvl2, teleop_fail_lvl3,
                        rotation_control, position_control, endgame_int,rotation_time, position_time, cycles, defense_played, defense_played_on,
                        level, notes);
                if(check){
                    Toast.makeText(SendActivity.this, "Data inserted", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(SendActivity.this, "Data not inserted", Toast.LENGTH_LONG).show();
                }
            }
        });
        Button showbutton = (Button) findViewById(R.id.showdb_button);
        showbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDb.getData();
                if(res.getCount() == 0){
                    showMessage("Error","Nothing found.");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Id: " + res.getInt(0) + "\n");
                    buffer.append("Match Number: " + res.getInt(1) + "\n");
                    buffer.append("Team Number: " + res.getInt(2) + "\n");
                    buffer.append("Auton Lvl1: " + res.getInt(3) + "\n");
                    buffer.append("Auton Lvl2: " + res.getInt(4) + "\n");
                    buffer.append("Auton Lvl3: " + res.getInt(5) + "\n");
                    buffer.append("Auton Line: " + res.getInt(6) + "\n");
                    buffer.append("Auton Pos.: " + res.getInt(7) + "\n");
                    buffer.append("Teleop S Lvl1: " + res.getInt(8) + "\n");
                    buffer.append("Teleop S Lvl2: " + res.getInt(9) + "\n");
                    buffer.append("Teleop S Lvl3: " + res.getInt(10) + "\n");
                    buffer.append("Teleop F Lvl1: " + res.getInt(11) + "\n");
                    buffer.append("Teleop F Lvl2: " + res.getInt(12) + "\n");
                    buffer.append("Teleop F Lvl3: " + res.getInt(13) + "\n");
                    buffer.append("Rot. Control: " + res.getInt(14) + "\n");
                    buffer.append("Pos. Control: " + res.getInt(15) + "\n");
                    buffer.append("Endgame: " + res.getInt(16) + "\n");
                    buffer.append("Rot. Time: " + res.getInt(17) + "\n");
                    buffer.append("Pos. Time: " + res.getInt(18) + "\n");
                    buffer.append("Cycles: " + res.getInt(19) + "\n");
                    buffer.append("Level: " + res.getInt(20) + "\n");
                    buffer.append("Notes: " + res.getInt(21) + "\n");
                }
                showMessage("Data", buffer.toString());
            }
        });
        manager.discoverPeers(channel, new WifiP2pManager.ActionListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(SendActivity.this, "Success", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(int reason) {
                Toast.makeText(SendActivity.this, "Failed", Toast.LENGTH_LONG).show();
            }
        });
    }
    public void showMessage(String title, String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.show();
    }
    public void setIsWifiP2pEnabled(boolean state){
        if(state){
            Toast.makeText(SendActivity.this, "Enabled", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(SendActivity.this, "Not Enabled", Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        //receiver = new WifiDirectBroadcastReceiver(manager, channel, this);
        registerReceiver(receiver, intentFilter);
    }

    @Override
    public void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }
    public void connect() {
        // Picking the first device found on the network.
        WifiP2pConfig config = new WifiP2pConfig();
        config.deviceAddress = device.deviceAddress;
        manager.connect(channel, config, new WifiP2pManager.ActionListener() {

            @Override
            public void onSuccess() {
                //success logic
            }

            @Override
            public void onFailure(int reason) {
                //failure logic
            }
        });
    }
}
