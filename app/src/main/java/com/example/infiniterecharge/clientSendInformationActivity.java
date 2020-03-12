package com.example.infiniterecharge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import static com.example.infiniterecharge.Variables.*;

public class clientSendInformationActivity extends AppCompatActivity {
    public static final String TAG = "ClientSendInfo";
    DbHelper myDb;
    // Whether the Log Fragment is currently shown
    private boolean mLogShown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_send_information);

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            BluetoothChatFragment fragment = new BluetoothChatFragment();
            transaction.replace(R.id.sample_content_fragment, fragment);
            transaction.commit();
        }

        /*
            Database stuff from here below
         */
        myDb = new DbHelper(this);


        //If left 0, the first column/id should autoincrement
        myDb.insertData(team_number, match_number, auton_lvl1, auton_lvl2, auton_lvl3, auton_line, auton_position,
                teleop_success_lvl1, teleop_success_lvl2, teleop_success_lvl3, teleop_fail_lvl1, teleop_fail_lvl2, teleop_fail_lvl3,
                rotation_control, position_control, endgame_int, rotation_time, position_time, cycles, defense_played, defense_played_on, level, notes);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
