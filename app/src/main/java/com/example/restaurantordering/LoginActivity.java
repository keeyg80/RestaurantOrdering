package com.example.restaurantordering;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Pair;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.muddzdev.styleabletoastlibrary.StyleableToast;

public class LoginActivity extends AppCompatActivity {
    private EditText et_id, et_password;
    private ImageView iv_loginlogo;
    private Button btn_login;
    private TextView tv_displayText, tv_loginComName;
    private Session session;
    private String loginRole ="", loginName ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Util.customColorStatusBar(LoginActivity.this, R.color.light_background);

        DatabaseHelper databaseHelper = new DatabaseHelper(LoginActivity.this);
        String versionName = BuildConfig.VERSION_NAME;
//        final CustomProgressDialog dialog = new CustomProgressDialog(LoginActivity.this);

        et_id=findViewById(R.id.et_id);
        et_password=findViewById(R.id.et_password);
        btn_login=findViewById(R.id.btn_login);
        tv_displayText=findViewById(R.id.tv_displayText);
        tv_loginComName=findViewById(R.id.tv_loginComName);
        iv_loginlogo=findViewById(R.id.iv_loginlogo);
        session = new Session(this);
        String android_id = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);



        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loginId = et_id.getText().toString();
                String loginPassword = et_password.getText().toString();
                boolean access = false;
                if (loginId.equals("") || loginPassword.equals("")) {
                    StyleableToast.makeText(LoginActivity.this,"ID and Password is mandatory!", R.style.errorToast).show();
//                    CustomToast.showToast(LoginActivity.this, "ID and Password is mandatory!");
//                    Toast.makeText(getApplicationContext(),"ID and Password is mandatory! ",Toast.LENGTH_SHORT).show();
                } else {
                    if (loginId.equals("a") && (loginPassword.equals("0") )) {
                        access=true;
                        StyleableToast.makeText(LoginActivity.this,"Admin Login Successfully!", R.style.successToast).show();
                        loginRole = "System";
                        loginName = "System";
//                        CustomToast.showToast(LoginActivity.this, "Admin Login Successfully!");
//                        Toast.makeText(getApplicationContext(), "Admin login successfully", Toast.LENGTH_SHORT).show();

                    }else {
                        int status = Integer.parseInt(databaseHelper.authentication(loginId, loginPassword));
                        if (status > 0) {
                            access = true;
                            loginRole="login";
                            loginName="Mr. Hello";
                        } else {
//                            Toast.makeText(getApplicationContext(), "Access Denied!", Toast.LENGTH_SHORT).show();
                            StyleableToast.makeText(LoginActivity.this,"Access Denied!", R.style.errorToast).show();
                        }
                    }
                }
                if (access){
                    et_id.setText("");
                    et_password.setText("");
                    et_id.requestFocus();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent (LoginActivity.this, MainActivity.class);
                            intent.putExtra("loginRole", loginRole);
                            intent.putExtra("loginName", loginName);
                            ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this,
                                    Pair.create(iv_loginlogo, "logo"),
                                    Pair.create(tv_loginComName, "companyName")
                            );
                            startActivity(intent, activityOptions.toBundle());
                        }
                    },1000);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to quit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LoginActivity.super.onBackPressed();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
}