package com.example.restaurantordering;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ManageUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_user);
        Util.customColorStatusBar(ManageUserActivity.this, R.color.light_background);
    }
}