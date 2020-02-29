package com.example.infiniterecharge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class DataCollectionActivity extends AppCompatActivity {
    public static final String TAG = "ClientSendInfo";
    DbHelper myDb;
    // Whether the Log Fragment is currently shown
    private boolean mLogShown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_collection);

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            BluetoothServerFragment fragment = new BluetoothServerFragment();
            transaction.replace(R.id.sample_server_fragment, fragment);
            transaction.commit();
        }
    }
}
