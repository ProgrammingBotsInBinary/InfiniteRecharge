package com.example.infiniterecharge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ServerMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_main);


        Button datacollection = findViewById(R.id.datacollection_button);
        datacollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServerMain.this, DataCollectionActivity.class);
                ServerMain.this.startActivity(intent);
            }
        });
        Button settings = findViewById(R.id.settings_activity_button);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServerMain.this, Settings.class);
                ServerMain.this.startActivity(intent);
            }
        });
    }
}
