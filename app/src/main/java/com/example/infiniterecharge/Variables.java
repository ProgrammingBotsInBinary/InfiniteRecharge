package com.example.infiniterecharge;

public class Variables {

    public static int team_number = 0;
    public static int match_number = 0;
    public static int auton_lvl1 = 0;
    public static int auton_lvl2 = 0;
    public static int auton_lvl3 = 0;
    public static int auton_line = 0;
    public static int auton_position = 0;
    public static int teleop_success_lvl1 = 0;
    public static int teleop_success_lvl2 = 0;
    public static int teleop_success_lvl3 = 0;
    public static int teleop_fail_lvl1 = 0;
    public static int teleop_fail_lvl2 = 0;
    public static int teleop_fail_lvl3 = 0;
    public static int rotation_control = 0;
    public static int position_control = 0;
    public static int endgame_int = 0;
    public static int rotation_time = 0;
    public static int position_time = 0;
    public static int cycles = 0;
    public static int defense_played = 0;
    public static int defense_played_on = 0;
    public static String notes = "None";

    public static int state = -1;

    public static int trench_run = 0;
    public static int floor_pickup = 0;
    public static String input = "";

    public static final String Col0 = "id";
    public static final String Col1 = "Team_Number";
    public static final String Col2 = "Match_Number";
    public static final String Col3 = "auton_lvl1";
    public static final String Col4 = "auton_lvl2";
    public static final String Col5 = "auton_lvl3";
    public static final String Col6 = "auton_line";
    public static final String Col7 = "auton_position";
    public static final String Col8 = "teleop_success_lvl1";
    public static final String Col9 = "teleop_success_lvl2";
    public static final String Col10 = "telep_success_lvl3";
    public static final String Col11 = "teleop_fail_lvl1";
    public static final String Col12 = "teleop_fail_lvl2";
    public static final String Col13 = "teleop_fail_lvl3";
    public static final String Col14 = "rotation_control";
    public static final String Col15 = "position_control";
    public static final String Col16 = "endgame";
    public static final String Col17 = "rotation_time";
    public static final String Col18 = "position_time";
    public static final String Col19 = "cycles";
    public static final String Col20 = "played_defense";
    public static final String Col21 = "defense_played_on";
    public static final String Col22 = "Notes";


    public static final String delimiter = "<>";



    static final int MESSAGE_STATE_CHANGE = 1;
    static final int MESSAGE_READ = 2;
    static final int MESSAGE_WRITE = 3;
    static final int MESSAGE_DEVICE_NAME = 4;
    static final int MESSAGE_TOAST = 5;

    // Key names received from the BluetoothChatService Handler
    static final String DEVICE_NAME = "device_name";
    static final String TOAST = "toast";

    //String that is sent from client side
    static String toSend = "";

    public static final String DB_NAME = "ScoutingData.db";

    public static final String THE_NAME = "MainTable";

}
