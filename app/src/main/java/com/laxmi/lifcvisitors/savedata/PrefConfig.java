package com.laxmi.lifcvisitors.savedata;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.laxmi.lifcvisitors.R;

public class PrefConfig {

    private SharedPreferences sharedPreferences;
    private Context context;

    public PrefConfig(Context context){
        this.context = context;

        sharedPreferences = context.getSharedPreferences(context.getString(R.string.pref_file), Context.MODE_PRIVATE);
    }

    public void writeLoginStatus(boolean status){

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(context.getString(R.string.pref_login_status),status);
        editor.apply();
    }

    public boolean readLoginStatus(){

        return sharedPreferences.getBoolean(context.getString(R.string.pref_login_status),false);
    }

    public void writeName(String name, String email, String login_id, String pic,String deviceId,String services){

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(context.getString(R.string.pref_username),name);
        editor.putString(context.getString(R.string.pref_email),email);
        editor.putString(context.getString(R.string.pref_login_id),login_id);
        editor.putString(context.getString(R.string.pref_pic),pic);
        editor.putString(context.getString(R.string.pref_deviceId),deviceId);
        editor.putString(context.getString(R.string.pref_services),services);
        editor.apply();

    }

    public String readName(){

        return sharedPreferences.getString(context.getString(R.string.pref_username),"Welcome User");
    }

    public String readDeviceId(){

        return sharedPreferences.getString(context.getString(R.string.pref_deviceId),"");
    }

public String readline(){
        return sharedPreferences.getString(context.getString(R.string.pref_login_id),"");
    }

    public String readPic()
    {

        return sharedPreferences.getString(context.getString(R.string.pref_pic),"");
    }

    public String readServices(){

        return sharedPreferences.getString(context.getString(R.string.pref_services),"");
    }

    public void displayToast(String message){

        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

    }
}
