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
                rotation_control, position_control, endgame_int, rotation_time, position_time, cycles, defense_played, defense_played_on, notes);
/*
        Cursor cur = myDb.getAll();
        StringBuffer buffer = new StringBuffer();
        while(cur.moveToNext()) {
            buffer.append(cur.getString(0) + "\n");
            buffer.append(cur.getString(1) + "\n");
            buffer.append(cur.getString(2) + "\n");
            buffer.append(cur.getString(3) + "\n");
            buffer.append(cur.getString(4) + "\n");
            buffer.append(cur.getString(5) + "\n");
            buffer.append(cur.getString(6) + "\n");
            buffer.append(cur.getString(7) + "\n");
            buffer.append(cur.getString(8) + "\n");
            buffer.append(cur.getString(9) + "\n");
            buffer.append(cur.getString(10) + "\n");
            buffer.append(cur.getString(11) + "\n");
            buffer.append(cur.getString(12) + "\n");
            buffer.append(cur.getString(13) + "\n");
            buffer.append(cur.getString(14) + "\n");
            buffer.append(cur.getString(15) + "\n");
            buffer.append(cur.getString(16) + "\n");
            buffer.append(cur.getString(17) + "\n");
            buffer.append(cur.getString(18) + "\n");
            buffer.append(cur.getString(19) + "\n");
            buffer.append(cur.getString(20) + "\n");
            buffer.append(cur.getString(21) + "\n");
            buffer.append(cur.getString(22) + "\n");
            buffer.append(cur.getString(23) + "\n");
            buffer.append(cur.getString(24) + "\n");
            buffer.append(cur.getString(25) + "\n");
            buffer.append(cur.getString(25) + "\n");
            buffer.append(cur.getString(26) + "\n");
            buffer.append(cur.getString(27) + "\n");
            buffer.append(cur.getString(28) + "\n");
            buffer.append(cur.getString(29) + "\n");
            buffer.append(cur.getString(30) + "\n");
            buffer.append(cur.getString(31) + "\n");
            buffer.append(cur.getString(32) + "\n");
            buffer.append(cur.getString(33) + "\n");
            buffer.append(cur.getString(34) + "\n");
            buffer.append(cur.getString(35) + "\n");
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Test stuff");
        builder.setMessage(buffer);
        builder.show();
*/
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
