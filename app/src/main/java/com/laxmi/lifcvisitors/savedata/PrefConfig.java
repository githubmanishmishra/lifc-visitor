package com.laxmi.lifcvisitors.savedata;

import android.content.Context;
import android.content.SharedPreferences;

import com.laxmi.lifcvisitors.R;

public class PrefConfig {

    private SharedPreferences sharedPreferences;
    private Context context;

    public PrefConfig(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(context.getString(R.string.pref_file), Context.MODE_PRIVATE);
    }

    public void writeLoginStatus(boolean status) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(context.getString(R.string.pref_login_status), status);
        editor.apply();
    }

    public boolean readLoginStatus() {

        return sharedPreferences.getBoolean(context.getString(R.string.pref_login_status), false);
    }

    public void writeName(String name, String token, String type, Integer id, String email,String profile_image) {

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_username), name);
        editor.putString(context.getString(R.string.pref_token), token);
        editor.putString(context.getString(R.string.pref_type), type);
        editor.putInt(context.getString(R.string.pref_login_id), id);
        editor.putString(context.getString(R.string.pref_email), email);
        editor.putString(context.getString(R.string.pref_image),profile_image);
        editor.apply();
    }

    public String readName() {
        return sharedPreferences.getString(context.getString(R.string.pref_username), "Welcome User");
    }
    public String readEmail() {
        return sharedPreferences.getString(context.getString(R.string.pref_email), "Welcome User");
    }
public String readProfile_image()
{
    return sharedPreferences.getString(context.getString(R.string.pref_image),"image set");

}
    public String readToken() {

        return sharedPreferences.getString(context.getString(R.string.pref_token), "");
    }

    public String readType() {

        return sharedPreferences.getString(context.getString(R.string.pref_type), "");
    }
    public String readID() {

        return sharedPreferences.getString(context.getString(R.string.pref_login_id), "");
    }
}
