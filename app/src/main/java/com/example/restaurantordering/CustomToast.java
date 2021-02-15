package com.example.restaurantordering;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;

public class CustomToast {
    public static void showToast(Activity activity, String message){
        View view = activity.getLayoutInflater().inflate(R.layout.layout_toast,null);
        Toast toast = new Toast(activity);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(view);
        toast.show();
    }
}
