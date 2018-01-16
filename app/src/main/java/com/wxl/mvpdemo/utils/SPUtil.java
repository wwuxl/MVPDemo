package com.wxl.mvpdemo.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

/**
 * Created by wxl on 2018/1/16.
 */

public class SPUtil {
    private static SharedPreferences mPreferences;

    public static SharedPreferences getSharePrefereces(){
        if(mPreferences==null){
            mPreferences=UIUtil.getContext().getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        return mPreferences;
    }

    public static void remove(String key){
        SharedPreferences.Editor edit = getSharePrefereces().edit();
        edit.remove(key);
        edit.apply();
    }

    public static void putString(String key, String value){
        SharedPreferences.Editor edit = getSharePrefereces().edit();
        edit.putString(key,value);
        edit.apply();
    }

    public static void putBoolean(String key, boolean value){
        SharedPreferences.Editor edit = getSharePrefereces().edit();
        edit.putBoolean(key,value);
        edit.apply();
    }

    public static void putInt(String key,int value){
        SharedPreferences.Editor edit = getSharePrefereces().edit();
        edit.putInt(key,value);
        edit.apply();
    }

    public static void putLong(String key,long value){
        SharedPreferences.Editor edit = getSharePrefereces().edit();
        edit.putLong(key,value);
        edit.apply();
    }
    public static void putFloat(String key,float value){
        SharedPreferences.Editor edit = getSharePrefereces().edit();
        edit.putFloat(key,value);
        edit.apply();
    }

    public static void putStringSet(String key,Set<String> value){
        SharedPreferences.Editor edit = getSharePrefereces().edit();
        edit.putStringSet(key,value);
        edit.apply();
    }

}
