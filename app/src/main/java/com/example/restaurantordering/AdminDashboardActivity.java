package com.example.restaurantordering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminDashboardActivity extends AppCompatActivity {
    private Button btn_sysuser,btn_sysserver,btn_sysrestaurant,btn_sysclean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        Util.customColorStatusBar(AdminDashboardActivity.this, R.color.light_background);

        btn_sysuser=findViewById(R.id.btn_sysuser);
        btn_sysserver=findViewById(R.id.btn_sysserver);
        btn_sysrestaurant=findViewById(R.id.btn_sysrestaurant);
        btn_sysclean=findViewById(R.id.btn_sysclean);

        btn_sysuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ManageUserActivity.class));
            }
        });
    }
}