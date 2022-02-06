package com.example.numad22sp_yinanwang;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

public class ClickyClicky extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clickyclicky);

        TextView pressed=this.findViewById(R.id.pressed);

        Button A = (Button) findViewById(R.id.A);
        A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressed.setText("Pressed: A");
            }
        });

        Button B = (Button) findViewById(R.id.B);
        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressed.setText("Pressed: B");
            }
        });

        Button C = (Button) findViewById(R.id.C);
        C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressed.setText("Pressed: C");
            }
        });

        Button D = (Button) findViewById(R.id.D);
        D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressed.setText("Pressed: D");
            }
        });

        Button E = (Button) findViewById(R.id.E);
        E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressed.setText("Pressed: E");
            }
        });

        Button F = (Button) findViewById(R.id.F);
        F.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressed.setText("Pressed: F");
            }
        });
    }
}