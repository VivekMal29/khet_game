package com.vivek.khet_game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    khet_canvas khetCanvas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        khetCanvas = new khet_canvas(this);
        setContentView(khetCanvas);
    }
}
