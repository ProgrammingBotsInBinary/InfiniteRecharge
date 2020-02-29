package com.example.infiniterecharge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class IntroScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_screen);

        Button client = (Button) findViewById(R.id.client_button);
        client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntroScreen.this, MainActivity.class);
                IntroScreen.this.startActivity(intent);
            }
        });
        Button server = (Button) findViewById(R.id.server_button);
        server.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntroScreen.this, ServerLogin.class);
                IntroScreen.this.startActivity(intent);
            }
        });
    }
}
