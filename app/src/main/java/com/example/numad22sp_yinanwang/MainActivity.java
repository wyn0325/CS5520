package com.example.numad22sp_yinanwang;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button_Panel = (Button) findViewById(R.id.about);
        button_Panel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Yinan Wang, wang.yinan3@northeastern.edu", Toast.LENGTH_SHORT).show();
            }
        });

        Button button_click = (Button) findViewById(R.id.clicky);
        button_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ClickyClicky.class);
                startActivity(intent);
            }
        });
    }

}
