package com.example.infiniterecharge.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.style.BulletSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import static com.example.infiniterecharge.Variables.*;

import com.example.infiniterecharge.DirectWifi;
import com.example.infiniterecharge.MainActivity;
import com.example.infiniterecharge.R;
import com.example.infiniterecharge.SendActivity;
import com.example.infiniterecharge.clientSendInformationActivity;

public class TeleopFragment extends Fragment implements View.OnClickListener {

    private TeleopViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(TeleopViewModel.class);
        View root = inflater.inflate(R.layout.fragment_teleop, container, false);

        Button plusbuttonsuccesslvl1 = (Button) root.findViewById(R.id.plusButton_success_lvl1);
        plusbuttonsuccesslvl1.setOnClickListener(this);
        Button minusbuttonsuccesslvl1 = (Button) root.findViewById(R.id.minusButton_success_lvl1);
        minusbuttonsuccesslvl1.setOnClickListener(this);
        Button plusbuttonsuccesslvl2 = (Button) root.findViewById(R.id.plusButton_success_lvl2);
        plusbuttonsuccesslvl2.setOnClickListener(this);
        Button minusbuttonsuccesslvl2 = (Button) root.findViewById(R.id.minusButton_success_lvl2);
        minusbuttonsuccesslvl2.setOnClickListener(this);
        Button plusbuttonsuccesslvl3 = (Button) root.findViewById(R.id.plusButton_success_lvl3);
        plusbuttonsuccesslvl3.setOnClickListener(this);
        Button minusbuttonsuccesslvl3 = (Button) root.findViewById(R.id.minusButton_success_lvl3);
        minusbuttonsuccesslvl3.setOnClickListener(this);

        Button rotationtimedec = (Button) root.findViewById(R.id.rotate_time_decrease);
        rotationtimedec.setOnClickListener(this);
        Button rotationtimeinc = (Button) root.findViewById(R.id.rotation_time_increase);
        rotationtimeinc.setOnClickListener(this);
        Button positiontimedec = (Button) root.findViewById(R.id.position_time_decrease);
        positiontimedec.setOnClickListener(this);
        Button positiontimeinc = (Button) root.findViewById(R.id.position_time_increment);
        positiontimeinc.setOnClickListener(this);
        Button cyclesdec = (Button) root.findViewById(R.id.cycles_decrease_button);
        cyclesdec.setOnClickListener(this);
        Button cyclesinc = (Button) root.findViewById(R.id.cycles_increase_button);
        cyclesinc.setOnClickListener(this);

        Button plusbuttonfaillvl1 = (Button) root.findViewById(R.id.plusButton_fail_lvl1);
        plusbuttonfaillvl1.setOnClickListener(this);
        Button minusbuttonfaillvl1 = (Button) root.findViewById(R.id.minusButton_fail_lvl1);
        minusbuttonfaillvl1.setOnClickListener(this);
        Button plusbuttonfaillvl2 = (Button) root.findViewById(R.id.plusButton_fail_lvl2);
        plusbuttonfaillvl2.setOnClickListener(this);
        Button minusbuttonfaillvl2 = (Button) root.findViewById(R.id.minusButton_fail_lvl2);
        minusbuttonfaillvl2.setOnClickListener(this);
        Button plusbuttonfaillvl3 = (Button) root.findViewById(R.id.plusButton_fail_lvl3);
        plusbuttonfaillvl3.setOnClickListener(this);
        Button minusbuttonfaillvl3 = (Button) root.findViewById(R.id.minusButton_fail_lvl3);
        minusbuttonfaillvl3.setOnClickListener(this);

        TextView teleopsuccesslvl1 = (TextView) root.findViewById(R.id.textview_success_lvl1);
        teleopsuccesslvl1.setText(Integer.toString(teleop_success_lvl1));
        TextView teleopfaillvl1 = (TextView) root.findViewById(R.id.textview_fail_lvl1);
        teleopfaillvl1.setText(Integer.toString(teleop_fail_lvl1));
        TextView teleopsuccesslvl2 = (TextView) root.findViewById(R.id.textview_success_lvl2);
        teleopsuccesslvl2.setText(Integer.toString(teleop_success_lvl2));
        TextView teleopfaillvl2 = (TextView) root.findViewById(R.id.textview_fail_lvl2);
        teleopfaillvl2.setText(Integer.toString(teleop_fail_lvl2));
        TextView teleopsuccesslvl3 = (TextView) root.findViewById(R.id.textview_success_lvl3);
        teleopsuccesslvl3.setText(Integer.toString(teleop_success_lvl3));
        TextView teleopfaillvl3 = (TextView) root.findViewById(R.id.textview_fail_lvl3);
        teleopfaillvl3.setText(Integer.toString(teleop_fail_lvl3));
        TextView rotationtime = (TextView) root.findViewById(R.id.rotationtime_textview);
        rotationtime.setText(Integer.toString(rotation_time));
        TextView positiontime = (TextView) root.findViewById(R.id.position_time_textview);
        positiontime.setText(Integer.toString(position_time));
        TextView numberofcycles = (TextView) root.findViewById(R.id.cycles_textview);
        numberofcycles.setText(Integer.toString(cycles));

        RadioButton rotation_yes = (RadioButton) root.findViewById(R.id.rotation_yes);
        RadioButton rotation_no = (RadioButton) root.findViewById(R.id.rotation_no);
        RadioButton position_yes = (RadioButton) root.findViewById(R.id.position_yes);
        RadioButton position_no = (RadioButton) root.findViewById(R.id.position_no);
        RadioButton endgamehang = (RadioButton) root.findViewById(R.id.endgame_hang);
        RadioButton endgamepark = (RadioButton) root.findViewById(R.id.endgame_park);
        RadioButton endgameneither = (RadioButton) root.findViewById(R.id.endgame_neither);
        RadioButton playeddefenseyes = (RadioButton) root.findViewById(R.id.defense_played_yes);
        RadioButton playeddefenseno = (RadioButton) root.findViewById(R.id.defense_played_no);
        RadioButton defenseplayedonyes = (RadioButton) root.findViewById(R.id.defense_played_on_yes);
        RadioButton defenseplayedonno = (RadioButton) root.findViewById(R.id.defense_played_on_no);

        Button sendactivity = (Button) root.findViewById(R.id.sendactivity_button);
        sendactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), clientSendInformationActivity.class);
                TeleopFragment.this.startActivity(intent);
            }
        });

        switch(defense_played){
            case 2:
                playeddefenseyes.setChecked(true);
                playeddefenseno.setChecked(false);
                break;
            case 1:
                playeddefenseno.setChecked(true);
                playeddefenseyes.setChecked(false);
                break;
        }
        switch (defense_played_on){
            case 2:
                defenseplayedonyes.setChecked(true);
                defenseplayedonno.setChecked(false);
                break;
            case 1:
                defenseplayedonno.setChecked(true);
                defenseplayedonyes.setChecked(false);
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
        RadioGroup playeddef = (RadioGroup) root.findViewById(R.id.radiogroup_defenseplayed);
        playeddef.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.defense_played_yes:
                        defense_played = 2;
                        break;
                    case R.id.defense_played_no:
                        defense_played = 1;
                        break;
                }
            }
        });
        RadioGroup defplayedon = (RadioGroup) root.findViewById(R.id.radiogroup_defended);
        defplayedon.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.defense_played_on_yes:
                        defense_played_on = 2;
                        break;
                    case R.id.defense_played_on_no:
                        defense_played_on = 1;
                        break;
                }
            }
        });

        RadioGroup rotation = (RadioGroup) root.findViewById(R.id.radiogroup_rotation);
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
        RadioGroup position = (RadioGroup) root.findViewById(R.id.radiogroup_position);
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
        RadioGroup endgame  =(RadioGroup) root.findViewById(R.id.radiogroup_endgame);
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
                teleop_success_lvl1++;
                TextView teleopsuccessincrementlvl1 = (TextView) v.findViewById(R.id.textview_success_lvl1);
                teleopsuccessincrementlvl1.setText(Integer.toString(teleop_success_lvl1));
                break;
            case R.id.minusButton_success_lvl1:
                if(teleop_success_lvl1 > 0){
                    teleop_success_lvl1--;
                    TextView teleopsuccessdecrementlvl1 = (TextView) v.findViewById(R.id.textview_success_lvl1);
                    teleopsuccessdecrementlvl1.setText(Integer.toString(teleop_success_lvl1));
                }
                break;
            case R.id.plusButton_fail_lvl1:
                teleop_fail_lvl1++;
                TextView teleopfailincrementlvl1 = (TextView) v.findViewById(R.id.textview_fail_lvl1);
                teleopfailincrementlvl1.setText(Integer.toString(teleop_fail_lvl1));
                break;
            case R.id.minusButton_fail_lvl1:
                if(teleop_fail_lvl1 > 0){
                    teleop_fail_lvl1--;
                    TextView teleopfaildecrementlvl1 = (TextView) v.findViewById(R.id.textview_fail_lvl1);
                    teleopfaildecrementlvl1.setText(Integer.toString(teleop_fail_lvl1));
                }
                break;
            case R.id.plusButton_success_lvl2:
                teleop_success_lvl2++;
                TextView teleopsuccessincrementlvl2 = (TextView) v.findViewById(R.id.textview_success_lvl2);
                teleopsuccessincrementlvl2.setText(Integer.toString(teleop_success_lvl2));
                break;
            case R.id.minusButton_success_lvl2:
                if(teleop_success_lvl2 > 0){
                    teleop_success_lvl2--;
                    TextView teleopsuccessdecrementlvl2 = (TextView) v.findViewById(R.id.textview_success_lvl2);
                    teleopsuccessdecrementlvl2.setText(Integer.toString(teleop_success_lvl2));
                }
                break;
            case R.id.plusButton_fail_lvl2:
                teleop_fail_lvl2++;
                TextView teleopfailincrementlvl2 = (TextView) v.findViewById(R.id.textview_fail_lvl2);
                teleopfailincrementlvl2.setText(Integer.toString(teleop_fail_lvl2));
                break;
            case R.id.minusButton_fail_lvl2:
                if(teleop_fail_lvl2 > 0){
                    teleop_fail_lvl2--;
                    TextView teleopfaildecrementlvl2 = (TextView) v.findViewById(R.id.textview_fail_lvl2);
                    teleopfaildecrementlvl2.setText(Integer.toString(teleop_fail_lvl2));
                }
                break;
            case R.id.plusButton_success_lvl3:
                teleop_success_lvl3++;
                TextView tsilvl3 = (TextView) v.findViewById(R.id.textview_success_lvl3);
                tsilvl3.setText(Integer.toString(teleop_success_lvl3));
                break;
            case R.id.minusButton_success_lvl3:
                if(teleop_success_lvl3 > 0){
                    teleop_success_lvl3--;
                    TextView tsdlvl3 = (TextView) v.findViewById(R.id.textview_success_lvl3);
                    tsdlvl3.setText(Integer.toString(teleop_success_lvl3));
                }
                break;
            case R.id.plusButton_fail_lvl3:
                teleop_fail_lvl3++;
                TextView tfilvl3 = (TextView) v.findViewById(R.id.textview_fail_lvl3);
                tfilvl3.setText(Integer.toString(teleop_fail_lvl3));
                break;
            case R.id.minusButton_fail_lvl3:
                if(teleop_fail_lvl3 > 0){
                    teleop_fail_lvl3--;
                    TextView tfdlvl3 = (TextView) v.findViewById(R.id.textview_fail_lvl3);
                    tfdlvl3.setText(Integer.toString(teleop_fail_lvl3));
                }
                break;
            case R.id.rotation_time_increase:
                if(rotation_time < 30){
                    rotation_time += 5;
                    TextView rti = (TextView) v.findViewById(R.id.rotationtime_textview);
                    rti.setText(Integer.toString(rotation_time));
                }
                break;
            case R.id.rotate_time_decrease:
                if(rotation_time > 0){
                    rotation_time -= 5;
                    TextView rtd = (TextView) v.findViewById(R.id.rotationtime_textview);
                    rtd.setText(Integer.toString(rotation_time));
                }
                break;
            case R.id.position_time_increment:
                if(position_time < 30){
                    position_time += 5;
                    TextView pti = (TextView) v.findViewById(R.id.position_time_textview);
                    pti.setText(Integer.toString(position_time));
                }
                break;
            case R.id.position_time_decrease:
                if(position_time > 0){
                    position_time -= 5;
                    TextView ptd = (TextView) v.findViewById(R.id.position_time_textview);
                    ptd.setText(Integer.toString(position_time));
                }
                break;
            case R.id.cycles_increase_button:
                if(cycles < 50){
                    cycles ++;
                    TextView ci = (TextView) v.findViewById(R.id.cycles_textview);
                    ci.setText(Integer.toString(cycles));
                }
                break;
            case R.id.cycles_decrease_button:
                if(cycles > 0){
                    cycles --;
                    TextView cd = (TextView) v.findViewById(R.id.cycles_textview);
                    cd.setText(Integer.toString(cycles));
                }
                break;
        }
    }
}