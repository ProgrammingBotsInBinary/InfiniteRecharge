package com.example.infiniterecharge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class DirectWifi extends AppCompatActivity {

    Button onoffButton, discoverButton, sendButton;
    ListView listView;
    TextView read_msg_box, connectionStatus;
    EditText writeMsg;
    private IntentFilter intentFilter = new IntentFilter();
    List<WifiP2pDevice> peers = new ArrayList<WifiP2pDevice>();
    String[] deviceNameArray;
    WifiP2pDevice[] deviceArray;
    BroadcastReceiver receiver;
    WifiP2pManager.Channel channel;
    WifiManager wifiManager;
    WifiP2pManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direct_wifi);

        onoffButton= (Button) findViewById(R.id.wifi_on_button);
        discoverButton= (Button) findViewById(R.id.discover_button);
        sendButton = (Button) findViewById(R.id.sendwifi_button);
        listView = (ListView) findViewById(R.id.list_view);
        read_msg_box = (TextView) findViewById(R.id.textview_message);
        connectionStatus = (TextView) findViewById(R.id.textview_connection_status);
        writeMsg = (EditText) findViewById(R.id.edittext_writemsg);

        manager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
        channel = manager.initialize(this, this.getMainLooper(), null);
        receiver = new WifiDirectBroadcastReceiver(manager, channel, this);

        // Indicates a change in the Wi-Fi P2P status.
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        // Indicates a change in the list of available peers.
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        // Indicates the state of Wi-Fi P2P connectivity has changed.
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        // Indicates this device's details have changed.
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);

        onoffButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(wifiManager.isWifiEnabled()){
                    wifiManager.setWifiEnabled(false);
                    //On
                }
                else {
                    wifiManager.setWifiEnabled(true);
                    //Off
                }
            }
        });
        discoverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.discoverPeers(channel, new WifiP2pManager.ActionListener() {
                    @Override
                    public void onSuccess() {
                        connectionStatus.setText("Discovery Started");
                    }

                    @Override
                    public void onFailure(int reason) {
                        connectionStatus.setText("Discovery Failed");
                    }
                });
            }
        });

        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);



    }
    WifiP2pManager.PeerListListener peerListListener = new WifiP2pManager.PeerListListener() {
        @Override
        public void onPeersAvailable(WifiP2pDeviceList peerList) {
//            Log.d("WIFI", "It makes it into the method");
//            Log.d("WIFI", "contains: " + Integer.toString(peerList.getDeviceList().size()));
//            if (!peerList.getDeviceList().equals(peers)) {
//                Log.d("WIFI", "It makes it into if statement");
//                peers.clear();
//                peers.addAll(peerList.getDeviceList());
//                String blah = Integer.toString(peerList.getDeviceList().size());
//                Log.d("WIFI", blah);
//
//
//            }
//
//            if (peers.size() == 0) {
//                Log.d("WIFI", "No devices found");
//                return;
//            }
            Log.d("WIFI", "Make it into Peers available method");
            //String amount = Integer.toString(peers.size());
            Log.d("WIFI", "It contains: " + Integer.toString(peerList.getDeviceList().size()));
            if(peerList == null){
                Log.d("WIFI", "Shits null");
            }
            if(!peerList.getDeviceList().equals(peers)){
                Log.d("WIFI", "Makes it into part to add to peers");
                //Log.d("WIFI", "It has this many: " + amount);
                peers.clear();
                peers.addAll(peerList.getDeviceList());
//                if(peerList.getDeviceList().isEmpty()){
//                    Log.d("WIFI", "WHY IS IT EMPTY");
//                }
                deviceNameArray = new String[peerList.getDeviceList().size()];
                deviceArray = new WifiP2pDevice[peerList.getDeviceList().size()];

               // Log.d("WIFI", "Now it has this many " + amount);
                int index = 0;
                for(WifiP2pDevice device : peerList.getDeviceList()){
                    deviceNameArray[index] = device.deviceName;
                    deviceArray[index] = device;
                    index++;
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, deviceNameArray);
                listView.setAdapter(adapter);
            }
            if(peers.size() == 0){
                Toast.makeText(getApplicationContext(), "No devices found", Toast.LENGTH_LONG).show();
                return;
            }
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        receiver = new WifiDirectBroadcastReceiver(manager, channel, this);
        registerReceiver(receiver, intentFilter);
    }

    @Override
    public void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }
    public void setIsWifiP2pEnabled(boolean state){
        if(state){
            Toast.makeText(DirectWifi.this, "Enabled", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(DirectWifi.this, "Not Enabled", Toast.LENGTH_LONG).show();
        }
    }

}
