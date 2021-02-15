package com.example.restaurantordering;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.NonNull;

public class CustomProgressDialog extends Dialog {
    public CustomProgressDialog(@NonNull Context context) {
        super(context);
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
        getWindow().setAttributes(layoutParams);
        setTitle(null);
        setCancelable(false);
        setOnCancelListener(null);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_loading,null);
        setContentView(view);
    }
}
