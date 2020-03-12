package com.example.infiniterecharge.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.style.BulletSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import static com.example.infiniterecharge.Variables.*;

import com.example.infiniterecharge.R;
import com.example.infiniterecharge.Variables;
import com.example.infiniterecharge.clientSendInformationActivity;

public class TeleopFragment extends Fragment implements View.OnClickListener {

    private TeleopViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(TeleopViewModel.class);
        View root = inflater.inflate(R.layout.fragment_teleop, container, false);

        Button plusbuttonsuccesslvl1 = root.findViewById(R.id.plusButton_success_lvl1);
        plusbuttonsuccesslvl1.setOnClickListener(this);
        Button minusbuttonsuccesslvl1 = root.findViewById(R.id.minusButton_success_lvl1);
        minusbuttonsuccesslvl1.setOnClickListener(this);
        Button plusbuttonsuccesslvl2 = root.findViewById(R.id.plusButton_success_lvl2);
        plusbuttonsuccesslvl2.setOnClickListener(this);
        Button minusbuttonsuccesslvl2 = root.findViewById(R.id.minusButton_success_lvl2);
        minusbuttonsuccesslvl2.setOnClickListener(this);
        Button plusbuttonsuccesslvl3 = root.findViewById(R.id.plusButton_success_lvl3);
        plusbuttonsuccesslvl3.setOnClickListener(this);
        Button minusbuttonsuccesslvl3 = root.findViewById(R.id.minusButton_success_lvl3);
        minusbuttonsuccesslvl3.setOnClickListener(this);

        Button rotationtimedec = root.findViewById(R.id.rotate_time_decrease);
        rotationtimedec.setOnClickListener(this);
        Button rotationtimeinc = root.findViewById(R.id.rotation_time_increase);
        rotationtimeinc.setOnClickListener(this);
        Button positiontimedec = root.findViewById(R.id.position_time_decrease);
        positiontimedec.setOnClickListener(this);
        Button positiontimeinc = root.findViewById(R.id.position_time_increment);
        positiontimeinc.setOnClickListener(this);
        Button cyclesdec = root.findViewById(R.id.cycles_decrease_button);
        cyclesdec.setOnClickListener(this);
        Button cyclesinc = root.findViewById(R.id.cycles_increase_button);
        cyclesinc.setOnClickListener(this);

        Button plusbuttonfaillvl1 = root.findViewById(R.id.plusButton_fail_lvl1);
        plusbuttonfaillvl1.setOnClickListener(this);
        Button minusbuttonfaillvl1 = root.findViewById(R.id.minusButton_fail_lvl1);
        minusbuttonfaillvl1.setOnClickListener(this);
        Button plusbuttonfaillvl2 = root.findViewById(R.id.plusButton_fail_lvl2);
        plusbuttonfaillvl2.setOnClickListener(this);
        Button minusbuttonfaillvl2 = root.findViewById(R.id.minusButton_fail_lvl2);
        minusbuttonfaillvl2.setOnClickListener(this);
        Button plusbuttonfaillvl3 = root.findViewById(R.id.plusButton_fail_lvl3);
        plusbuttonfaillvl3.setOnClickListener(this);
        Button minusbuttonfaillvl3 = root.findViewById(R.id.minusButton_fail_lvl3);
        minusbuttonfaillvl3.setOnClickListener(this);

        TextView teleopsuccesslvl1 = root.findViewById(R.id.textview_success_lvl1);
        teleopsuccesslvl1.setText(Integer.toString(teleop_success_lvl1));
        TextView teleopfaillvl1 = root.findViewById(R.id.textview_fail_lvl1);
        teleopfaillvl1.setText(Integer.toString(teleop_fail_lvl1));
        TextView teleopsuccesslvl2 = root.findViewById(R.id.textview_success_lvl2);
        teleopsuccesslvl2.setText(Integer.toString(teleop_success_lvl2));
        TextView teleopfaillvl2 = root.findViewById(R.id.textview_fail_lvl2);
        teleopfaillvl2.setText(Integer.toString(teleop_fail_lvl2));
        TextView teleopsuccesslvl3 = root.findViewById(R.id.textview_success_lvl3);
        teleopsuccesslvl3.setText(Integer.toString(teleop_success_lvl3));
        TextView teleopfaillvl3 = root.findViewById(R.id.textview_fail_lvl3);
        teleopfaillvl3.setText(Integer.toString(teleop_fail_lvl3));
        TextView rotationtime = root.findViewById(R.id.rotationtime_textview);
        rotationtime.setText(Integer.toString(rotation_time));
        TextView positiontime = root.findViewById(R.id.position_time_textview);
        positiontime.setText(Integer.toString(position_time));
        TextView numberofcycles = root.findViewById(R.id.cycles_textview);
        numberofcycles.setText(Integer.toString(cycles));
        final TextView playeddef = root.findViewById(R.id.defense_played_textview);
        final TextView defnded = root.findViewById(R.id.defended_textview);
        final TextView lvl = root.findViewById(R.id.switch_level_textview);

        RadioButton rotation_yes = root.findViewById(R.id.rotation_yes);
        RadioButton rotation_no = root.findViewById(R.id.rotation_no);
        RadioButton position_yes = root.findViewById(R.id.position_yes);
        RadioButton position_no = root.findViewById(R.id.position_no);
        RadioButton endgamehang = root.findViewById(R.id.endgame_hang);
        RadioButton endgamepark = root.findViewById(R.id.endgame_park);
        RadioButton endgameneither = root.findViewById(R.id.endgame_neither);
        Switch defenseplayed = root.findViewById(R.id.switch_defenseplayed);
        Switch defended = root.findViewById(R.id.switch_defended);
        Switch levelswitch = root.findViewById(R.id.switch_level);

        Button sendactivity = root.findViewById(R.id.sendactivity_button);
        sendactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), clientSendInformationActivity.class);
                TeleopFragment.this.startActivity(intent);
            }
        });

        switch (defense_played){
            case 0:
                defenseplayed.setChecked(false);
                playeddef.setText("No");
                break;
            case 1:
                defenseplayed.setChecked(true);
                playeddef.setText("Yes");
                break;
        }
        switch (defense_played_on){
            case 0:
                defended.setChecked(false);
                defnded.setText("No");
                break;
            case 1:
                defended.setChecked(true);
                defnded.setText("Yes");
                break;
        }
        switch (level){
            case 0:
                levelswitch.setChecked(false);
                lvl.setText("No");
                break;
            case 1:
                levelswitch.setChecked(true);
                lvl.setText("Yes");
                break;
        }
        switch (rotation_control){
            case 2:
                rotation_yes.setChecked(true);
                rotation_no.setChecked(false);
                break;
            case 1:
                rotation_no.setChecked(true);
                rotation_yes.setChecked(false);
                break;
        }
        switch (position_control){
            case 2:
                position_yes.setChecked(true);
                position_no.setChecked(false);
                break;
            case 1:
                position_no.setChecked(true);
                position_yes.setChecked(false);
                break;
        }
        switch (endgame_int){
            case 1:
                endgameneither.setChecked(true);
                endgamehang.setChecked(false);
                endgamepark.setChecked(false);
                break;
            case 2:
                endgamepark.setChecked(true);
                endgameneither.setChecked(false);
                endgamehang.setChecked(false);
                break;
            case 3:
                endgamehang.setChecked(true);
                endgameneither.setChecked(false);
                endgamepark.setChecked(false);
                break;
        }
        defenseplayed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    defense_played = 1;
                    playeddef.setText("Yes");
                }
                else{
                    defense_played = 0;
                    playeddef.setText("No");
                }
            }
        });
        defended.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    defense_played_on = 1;
                    defnded.setText("Yes");
                }
                else{
                    defense_played_on = 0;
                    defnded.setText("No");
                }
            }
        });
        levelswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    level = 1;
                    lvl.setText("Yes");
                }
                else{
                    level = 0;
                    lvl.setText("No");
                }
            }
        });

        RadioGroup rotation = root.findViewById(R.id.radiogroup_rotation);
        rotation.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rotation_yes:
                        rotation_control = 2;
                        break;
                    case R.id.rotation_no:
                        rotation_control = 1;
                        break;
                }
            }
        });
        RadioGroup position = root.findViewById(R.id.radiogroup_position);
        position.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.position_yes:
                        position_control = 2;
                        break;
                    case R.id.position_no:
                        position_control = 1;
                        break;
                }
            }
        });
        RadioGroup endgame  = root.findViewById(R.id.radiogroup_endgame);
        endgame.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.endgame_hang:
                        endgame_int = 3;
                        break;
                    case R.id.endgame_park:
                        endgame_int = 2;
                        break;
                    case R.id.endgame_neither:
                        endgame_int = 1;
                        break;
                }
            }
        });


        dashboardViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onClick(View view) {
        View v = getView();
        switch (view.getId()){
            case R.id.plusButton_success_lvl1:
                if(teleop_success_lvl1 < 60){
                    teleop_success_lvl1++;
                    TextView teleopsuccessincrementlvl1 = v.findViewById(R.id.textview_success_lvl1);
                    teleopsuccessincrementlvl1.setText(Integer.toString(teleop_success_lvl1));
                }
                break;
            case R.id.minusButton_success_lvl1:
                if(teleop_success_lvl1 > 0){
                    teleop_success_lvl1--;
                    TextView teleopsuccessdecrementlvl1 = v.findViewById(R.id.textview_success_lvl1);
                    teleopsuccessdecrementlvl1.setText(Integer.toString(teleop_success_lvl1));
                }
                break;
            case R.id.plusButton_fail_lvl1:
                if(teleop_fail_lvl1 < 60){
                    teleop_fail_lvl1++;
                    TextView teleopfailincrementlvl1 = v.findViewById(R.id.textview_fail_lvl1);
                    teleopfailincrementlvl1.setText(Integer.toString(teleop_fail_lvl1));
                }
                break;
            case R.id.minusButton_fail_lvl1:
                if(teleop_fail_lvl1 > 0){
                    teleop_fail_lvl1--;
                    TextView teleopfaildecrementlvl1 = v.findViewById(R.id.textview_fail_lvl1);
                    teleopfaildecrementlvl1.setText(Integer.toString(teleop_fail_lvl1));
                }
                break;
            case R.id.plusButton_success_lvl2:
                if(teleop_success_lvl2 < 60){
                    teleop_success_lvl2++;
                    TextView teleopsuccessincrementlvl2 = v.findViewById(R.id.textview_success_lvl2);
                    teleopsuccessincrementlvl2.setText(Integer.toString(teleop_success_lvl2));
                }
                break;
            case R.id.minusButton_success_lvl2:
                if(teleop_success_lvl2 > 0){
                    teleop_success_lvl2--;
                    TextView teleopsuccessdecrementlvl2 = v.findViewById(R.id.textview_success_lvl2);
                    teleopsuccessdecrementlvl2.setText(Integer.toString(teleop_success_lvl2));
                }
                break;
            case R.id.plusButton_fail_lvl2:
                if(teleop_fail_lvl2 < 60){
                    teleop_fail_lvl2++;
                    TextView teleopfailincrementlvl2 = v.findViewById(R.id.textview_fail_lvl2);
                    teleopfailincrementlvl2.setText(Integer.toString(teleop_fail_lvl2));
                }
                break;
            case R.id.minusButton_fail_lvl2:
                if(teleop_fail_lvl2 > 0){
                    teleop_fail_lvl2--;
                    TextView teleopfaildecrementlvl2 = v.findViewById(R.id.textview_fail_lvl2);
                    teleopfaildecrementlvl2.setText(Integer.toString(teleop_fail_lvl2));
                }
                break;
            case R.id.plusButton_success_lvl3:
                if(teleop_success_lvl3 < 60){
                    teleop_success_lvl3++;
                    TextView tsilvl3 = v.findViewById(R.id.textview_success_lvl3);
                    tsilvl3.setText(Integer.toString(teleop_success_lvl3));
                }
                break;
            case R.id.minusButton_success_lvl3:
                if(teleop_success_lvl3 > 0){
                    teleop_success_lvl3--;
                    TextView tsdlvl3 = v.findViewById(R.id.textview_success_lvl3);
                    tsdlvl3.setText(Integer.toString(teleop_success_lvl3));
                }
                break;
            case R.id.plusButton_fail_lvl3:
                if(teleop_fail_lvl3 < 60){
                    teleop_fail_lvl3++;
                    TextView tfilvl3 = v.findViewById(R.id.textview_fail_lvl3);
                    tfilvl3.setText(Integer.toString(teleop_fail_lvl3));
                }
                break;
            case R.id.minusButton_fail_lvl3:
                if(teleop_fail_lvl3 > 0){
                    teleop_fail_lvl3--;
                    TextView tfdlvl3 = v.findViewById(R.id.textview_fail_lvl3);
                    tfdlvl3.setText(Integer.toString(teleop_fail_lvl3));
                }
                break;
            case R.id.rotation_time_increase:
                if(rotation_time < 30){
                    rotation_time += 5;
                    TextView rti = v.findViewById(R.id.rotationtime_textview);
                    rti.setText(Integer.toString(rotation_time));
                }
                break;
            case R.id.rotate_time_decrease:
                if(rotation_time > 0){
                    rotation_time -= 5;
                    TextView rtd = v.findViewById(R.id.rotationtime_textview);
                    rtd.setText(Integer.toString(rotation_time));
                }
                break;
            case R.id.position_time_increment:
                if(position_time < 30){
                    position_time += 5;
                    TextView pti = v.findViewById(R.id.position_time_textview);
                    pti.setText(Integer.toString(position_time));
                }
                break;
            case R.id.position_time_decrease:
                if(position_time > 0){
                    position_time -= 5;
                    TextView ptd = v.findViewById(R.id.position_time_textview);
                    ptd.setText(Integer.toString(position_time));
                }
                break;
            case R.id.cycles_increase_button:
                if(cycles < 50){
                    cycles ++;
                    TextView ci = v.findViewById(R.id.cycles_textview);
                    ci.setText(Integer.toString(cycles));
                }
                break;
            case R.id.cycles_decrease_button:
                if(cycles > 0){
                    cycles --;
                    TextView cd = v.findViewById(R.id.cycles_textview);
                    cd.setText(Integer.toString(cycles));
                }
                break;
        }
    }
}