package com.example.numad22sp_yinanwang;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button about = (Button) findViewById(R.id.about);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar.make(v,"Yinan Wang \nwang.yinan3@northeastern.edu",Snackbar.LENGTH_SHORT);
                snackbar.show();
            }
        });

        Button clicky = (Button) findViewById(R.id.clicky);
        clicky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ClickyClicky.class);
                startActivity(intent);
            }
        });

        Button linkCollector = (Button) findViewById(R.id.linkCollector);
        linkCollector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,LinkCollector.class);
                startActivity(intent);
            }
        });

        Button locator = (Button) findViewById(R.id.locator);
        locator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Locator.class);
                startActivity(intent);
            }
        });

        Button webservice = (Button) findViewById(R.id.webservice);
        webservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,WebService.class);
                startActivity(intent);
            }
        });

    }



}
