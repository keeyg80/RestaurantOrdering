package com.example.restaurantordering;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Session {
    private SharedPreferences prefs;
    public Session(Context cntx){
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }
    public void setuseid(String useid) {
        prefs.edit().putString("useid",useid).commit();
    }
    public String getuseid(){
        String useid = prefs.getString("useid","");
        return useid;
    }

    public void setuserole(String userole) {
        prefs.edit().putString("userole",userole).commit();
    }
    public String getuserole(){
        String userole = prefs.getString("userole","");
        return userole;
    }
    public void setusename(String usename) {
        prefs.edit().putString("usename",usename).commit();
    }
    public String getusename(){
        String usename = prefs.getString("usename","");
        return usename;
    }
}
