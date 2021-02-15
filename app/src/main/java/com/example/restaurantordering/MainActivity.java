package com.example.restaurantordering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.muddzdev.styleabletoastlibrary.StyleableToast;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private int progressStatus = 0;
    private TextView textView;
    private Handler handler = new Handler();
    private String loginRole ="", loginName ="";
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Util.customColorStatusBar(MainActivity.this, R.color.light_background);
        progressBar = findViewById(R.id.pb_main);
        textView = findViewById(R.id.tv_main);

        if (getIntent().hasExtra("loginRole")) {
            loginRole = getIntent().getStringExtra("loginRole");
        } else {
            StyleableToast.makeText(MainActivity.this,"Invalid Access!", R.style.errorToast).show();
            finish();
        }

        // Start long running operation in a background thread
        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < 100) {
                    progressStatus += 1;
                    // Update the progress bar and display the
                    //current value in the text view
                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressStatus);
                            textView.setText("Loading "+progressStatus+ " %" );
                            if (progressStatus == 100) {
//                                if (FirebaseAuth.getInstance().getCurrentUser() != null && FirebaseAuth.getInstance().getCurrentUser().isEmailVerified()){
//                                    textView.setText(" Accessing Dashboard...");
//                                    startActivity(new Intent(getApplicationContext(),DashboardActivity.class));
//                                }else{
//                                    textView.setText(" Opening Signup Activity ...");
//                                    startActivity(new Intent(getApplicationContext(),SignupActivity.class));
//                                }
                                Log.d(TAG, "run: loginRole ="+loginRole);
                                if (loginRole.equals("System")){
                                    StyleableToast.makeText(MainActivity.this,"Welcome System Admin!", R.style.successToast).show();
                                    startActivity(new Intent(getApplicationContext(),AdminDashboardActivity.class));
                                }else if (loginRole.equals("login")){
                                    StyleableToast.makeText(MainActivity.this,"Welcome " +loginRole, R.style.successToast).show();
                                    startActivity(new Intent(getApplicationContext(),DashboardActivity.class));
                                }else{
                                    StyleableToast.makeText(MainActivity.this,"Invalid User!", R.style.errorToast).show();
                                }
                                finish();
                            }
                        }
                    });
                    try {
                        // Sleep for 200 milliseconds.
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }
}