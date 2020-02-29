package com.example.infiniterecharge.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import static com.example.infiniterecharge.Variables.*;

import com.example.infiniterecharge.DataCollectionActivity;
import com.example.infiniterecharge.IntroScreen;
import com.example.infiniterecharge.R;
import com.example.infiniterecharge.ServerMain;

public class NotesFragment extends Fragment {

    private NotesViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notes, container, false);
        if(notes != "None"){
            EditText note = (EditText) root.findViewById(R.id.edittext_notes);
            note.setText(notes);
        }
        Button introscreen = (Button) root.findViewById(R.id.intro_button);
        introscreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), IntroScreen.class);
                NotesFragment.this.startActivity(intent);
            }
        });

        final EditText notestext = (EditText) root.findViewById(R.id.edittext_notes);
        notestext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() != 0){
                    notes = notestext.getText().toString();
                }
            }
            @Override
            public void afterTextChanged(Editable s) { }
        });
        return root;
    }
}