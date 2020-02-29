package com.example.infiniterecharge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ServerLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_login);

        final EditText password = (EditText) findViewById(R.id.edittext_password);
        Button submitbtn = (Button) findViewById(R.id.login_submit_button);
        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String attempt = password.getText().toString();
                if(attempt.contains("password")){
                    Intent intent = new Intent(ServerLogin.this, ServerMain.class);
                    ServerLogin.this.startActivity(intent);
                }
                else{
                    password.setText("");
                    Toast.makeText(ServerLogin.this, "Try again", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
