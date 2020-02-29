package com.example.infiniterecharge;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        final DbHelper myDb = new DbHelper(this);

        Button deletedb = (Button) findViewById(R.id.delete_db_button);
        deletedb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDb.deleteDb(getApplicationContext());
            }
        });
    }
}
