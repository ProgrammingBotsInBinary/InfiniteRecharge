package com.example.infiniterecharge.ui.home;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import static com.example.infiniterecharge.Variables.*;

import com.example.infiniterecharge.R;

public class AutonFragment extends Fragment implements View.OnClickListener {

    private AutonViewModel autonViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        autonViewModel =
                ViewModelProviders.of(this).get(AutonViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_auton, container, false);

        Button plusbuttonlvl1 = (Button) root.findViewById(R.id.plusButton_lvl1);
        plusbuttonlvl1.setOnClickListener(this);
        Button minusbuttonlvl1 = (Button) root.findViewById(R.id.minusButton_lvl1);
        minusbuttonlvl1.setOnClickListener(this);
        Button plusbuttonlvl2 = (Button) root.findViewById(R.id.plusButton_lvl2);
        plusbuttonlvl2.setOnClickListener(this);
        Button minusbuttonlvl2 = (Button) root.findViewById(R.id.minusButton_lvl2);
        minusbuttonlvl2.setOnClickListener(this);
        Button plusbuttonlvl3 = (Button) root.findViewById(R.id.plusButton_lvl3);
        plusbuttonlvl3.setOnClickListener(this);
        Button minusbuttonlvl3 = (Button) root.findViewById(R.id.minusButton_lvl3);
        minusbuttonlvl3.setOnClickListener(this);

        TextView autonlvl1 = (TextView) root.findViewById(R.id.textview_lvl1);
        autonlvl1.setText(Integer.toString(auton_lvl1));
        TextView autonlvl2 = (TextView) root.findViewById(R.id.textview_lvl2);
        autonlvl2.setText(Integer.toString(auton_lvl2));
        TextView autonlvl3 = (TextView) root.findViewById(R.id.textview_lvl3);
        autonlvl3.setText(Integer.toString(auton_lvl3));
        if(team_number != 0){
            EditText teamnum = (EditText) root.findViewById(R.id.edittext_team_number);
            teamnum.setText(Integer.toString(team_number));
        }
        if(match_number != 0){
            EditText matchnum = (EditText) root.findViewById(R.id.edittext_match_number);
            matchnum.setText(Integer.toString(match_number));
        }

        RadioButton lineno = (RadioButton) root.findViewById(R.id.line_no);
        RadioButton lineyes = (RadioButton) root.findViewById(R.id.line_yes);

        RadioButton positionpowerport = (RadioButton) root.findViewById(R.id.powerport_button);
        RadioButton loadingbay = (RadioButton) root.findViewById(R.id.loadingbay_button);
        RadioButton far = (RadioButton) root.findViewById(R.id.far_button);

        switch (auton_line){
            case 2:
                lineyes.setChecked(true);
                lineno.setChecked(false);
                break;
            case 1:
                lineno.setChecked(true);
                lineyes.setChecked(false);
                break;
        }
        switch (auton_position){
            case 1:
                positionpowerport.setChecked(true);
                loadingbay.setChecked(false);
                far.setChecked(false);
                break;
            case 2:
                loadingbay.setChecked(true);
                positionpowerport.setChecked(false);
                far.setChecked(false);
                break;
            case 3:
                far.setChecked(true);
                positionpowerport.setChecked(false);
                loadingbay.setChecked(false);
                break;
        }
        final EditText teamnumber = (EditText) root.findViewById(R.id.edittext_team_number);
        teamnumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() != 0){
                    String input = teamnumber.getText().toString();
                    team_number = Integer.parseInt(input);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        final EditText matchnumber = (EditText) root.findViewById(R.id.edittext_match_number);
        matchnumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() != 0){
                    String input = matchnumber.getText().toString();
                    match_number = Integer.parseInt(input);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        RadioGroup line = (RadioGroup) root.findViewById(R.id.radiogroup_line);
        line.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.line_yes:
                        auton_line = 2;
                        break;
                    case R.id.line_no:
                        auton_line = 1;
                        break;
                }
            }
        });
        RadioGroup position = (RadioGroup) root.findViewById(R.id.radiogroup_position);
        position.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.powerport_button:
                        auton_position = 1;
                        break;
                    case R.id.loadingbay_button:
                        auton_position = 2;
                        break;
                    case R.id.far_button:
                        auton_position = 3;
                        break;
                }
            }
        });

        autonViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });
        return root;
    }
    public void onClick(View view){
        View v = getView();
        switch(view.getId()){
            case R.id.plusButton_lvl1:
                auton_lvl1++;
                TextView autonincrement = (TextView) v.findViewById(R.id.textview_lvl1);
                autonincrement.setText(Integer.toString(auton_lvl1));
                break;
            case R.id.minusButton_lvl1:
                if(auton_lvl1 > 0){
                    auton_lvl1--;
                    TextView autondecrement = (TextView) v.findViewById(R.id.textview_lvl1);
                    autondecrement.setText(Integer.toString(auton_lvl1));
                }
                break;
            case R.id.plusButton_lvl2:
                if(auton_lvl2 < 20){
                    auton_lvl2++;
                    TextView autonlvl1increment = (TextView) v.findViewById(R.id.textview_lvl2);
                    autonlvl1increment.setText(Integer.toString(auton_lvl2));
                }
                break;
            case R.id.minusButton_lvl2:
                if(auton_lvl2 > 0){
                    auton_lvl2--;
                    TextView autonlvl2decrement = (TextView) v.findViewById(R.id.textview_lvl2);
                    autonlvl2decrement.setText(Integer.toString(auton_lvl2));
                }
                break;
            case R.id.plusButton_lvl3:
                if(auton_lvl3 < 20){
                    auton_lvl3++;
                    TextView autonlvl3increment = (TextView) v.findViewById(R.id.textview_lvl3);
                    autonlvl3increment.setText(Integer.toString(auton_lvl3));
                }
                break;
            case R.id.minusButton_lvl3:
                if(auton_lvl3 > 0){
                    auton_lvl3--;
                    TextView autonlvl3decrement = (TextView) v.findViewById(R.id.textview_lvl3);
                    autonlvl3decrement.setText(Integer.toString(auton_lvl3));
                }
                break;
        }
    }
}