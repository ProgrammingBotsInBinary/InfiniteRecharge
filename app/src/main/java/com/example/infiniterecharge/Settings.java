package com.example.infiniterecharge;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        final DbHelper myDb = new DbHelper(this);

        Button deletedb = findViewById(R.id.delete_db_button);
        deletedb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDb.deleteDb(getApplicationContext());
            }
        });
        Button deleterow = findViewById(R.id.delete_row_button);
        deleterow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v != null){
                    EditText teamnumb = findViewById(R.id.edittext_teamnumber);
                    int teamnumber = Integer.parseInt(teamnumb.getText().toString());
                    EditText matchnumb = findViewById(R.id.edittext_matchnumber);
                    int matchnumber = Integer.parseInt(matchnumb.getText().toString());
                    myDb.deleteRow(teamnumber, matchnumber);
                }
            }
        });
    }
}
