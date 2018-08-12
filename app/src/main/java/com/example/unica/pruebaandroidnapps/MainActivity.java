package com.example.unica.pruebaandroidnapps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.unica.pruebaandroidnapps.gui.CrmActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(MainActivity.this, CrmActivity.class);
        startActivity(intent);
    }
}
