package com.marwaeltayeb.movietrailer.ui.about;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.marwaeltayeb.movietrailer.R;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }
}