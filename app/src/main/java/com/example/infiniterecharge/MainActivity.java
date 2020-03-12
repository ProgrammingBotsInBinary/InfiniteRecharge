package com.example.infiniterecharge;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toolbar;

import com.example.infiniterecharge.ui.notifications.NotesViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import static com.example.infiniterecharge.Variables.*;

public class MainActivity extends AppCompatActivity {
    private NotesViewModel notesViewModel;
    Cursor matchnumb;
    int matchnumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        DbHelper myDb = new DbHelper(getApplicationContext());
        matchnumb = myDb.getData();
        matchnumber = Integer.parseInt(Integer.toString(matchnumb.getCount()));
        if(matchnumber != state){
            Log.d("RESET", "Makes it to state change");
            //resetValues();
            state = matchnumber;
        }
        team_number = 0;
        match_number = matchnumber + 1;
        auton_lvl1 = 0;
        auton_lvl2 = 0;
        auton_lvl3 = 0;
        auton_line = 0;
        auton_position = 0;
        teleop_success_lvl1 = 0;
        teleop_success_lvl2 = 0;
        teleop_success_lvl3 = 0;
        teleop_fail_lvl1 = 0;
        teleop_fail_lvl2 = 0;
        teleop_fail_lvl3 = 0;
        rotation_control = 0;
        position_control = 0;
        endgame_int = 0;
        rotation_time = 0;
        position_time = 0;
        cycles = 0;
        defense_played = 0;
        defense_played_on = 0;
        level = 0;
        notes = "None";
        input = "";
        toSend = "";
        Log.d("RESET", "This is rotation val: " + Integer.toString(rotation_control));
        Log.d("RESET", "This is line val: " + Integer.toString(auton_line));

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);



    }

    public void resetValues(){
        Log.d("RESET", "Makes it in reset method");
        team_number = 0;
        match_number = matchnumber + 1;
        auton_lvl1 = 0;
        auton_lvl2 = 0;
        auton_lvl3 = 0;
        auton_line = 0;
        auton_position = 0;
        teleop_success_lvl1 = 0;
        teleop_success_lvl2 = 0;
        teleop_success_lvl3 = 0;
        teleop_fail_lvl1 = 0;
        teleop_fail_lvl2 = 0;
        teleop_fail_lvl3 = 0;
        rotation_control = 0;
        position_control = 0;
        endgame_int = 0;
        rotation_time = 0;
        position_time = 0;
        cycles = 0;
        defense_played = 0;
        defense_played_on = 0;
        notes = "None";
        input = "";
        toSend = "";
    }

}
