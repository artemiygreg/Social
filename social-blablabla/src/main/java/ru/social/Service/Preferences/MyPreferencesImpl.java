package ru.social.Service.Preferences;

import android.content.Context;
import android.content.SharedPreferences;
import ru.social.API.Vars;
import ru.social.Model.User;



public class MyPreferencesImpl implements MyPreferences {
    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public MyPreferencesImpl(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("myPref", Context.MODE_PRIVATE);
    }
    @Override
    public String getToken() {
        return sharedPreferences.getString(Vars.TOKEN, "");
    }
    @Override
    public void setToken(String token) {
        editor = sharedPreferences.edit();
        editor.putString(Vars.TOKEN, token);
        editor.apply();
    }
    @Override
    public String getPhone() {
        return sharedPreferences.getString(Vars.PHONE, "");
    }
    @Override
    public void setPhone(String phone) {
        editor = sharedPreferences.edit();
        editor.putString(Vars.PHONE, phone);
        editor.apply();
    }
    @Override
    public String getFio() {
        return sharedPreferences.getString(Vars.FIO, "");
    }
    @Override
    public void setFio(String fio) {
        editor = sharedPreferences.edit();
        editor.putString(Vars.FIO, fio);
        editor.apply();
    }

    @Override
    public String getEmail() {
        return sharedPreferences.getString(Vars.EMAIL, "");
    }

    @Override
    public void setEmail(String emial) {
        editor = sharedPreferences.edit();
        editor.putString(Vars.EMAIL, emial);
        editor.apply();
    }

    @Override
    public String getAvatar() {
        return sharedPreferences.getString(Vars.AVATAR, "");
    }
    @Override
    public void setAvatar(String avatar) {
        editor = sharedPreferences.edit();
        editor.putString(Vars.AVATAR, avatar);
        editor.apply();
    }
    @Override
    public String getObjectFromPrefs(String key){
        return sharedPreferences.getString(key, "");
    }
    @Override
    public User getUserFromPref(){
        User user = new User();
        user.setPhone(getPhone());
        user.setFio(getFio());
        user.setAvatar(getAvatar());
        user.setToken(getToken());
        user.setEmail(getEmail());

        return user;

    }
    @Override
    public void savePrefFromUser(User user){
        editor = sharedPreferences.edit();
        editor.putString(Vars.AVATAR, user.getAvatar());
        editor.putString(Vars.FIO, user.getFio());
        editor.putString(Vars.EMAIL, user.getEmail());
        editor.putString(Vars.TOKEN, user.getToken());
        editor.putString(Vars.PHONE, user.getPhone());

        editor.apply();

    }
    @Override
    public boolean clearPrefs(){
        boolean cleared;
        try {
            editor = sharedPreferences.edit();
            editor.putString(Vars.AVATAR, "");
            editor.putString(Vars.FIO, "");
            editor.putString(Vars.EMAIL, "");
            editor.putString(Vars.TOKEN, "");
            editor.putString(Vars.PHONE, "");

            editor.apply();
            cleared = true;
        }
        catch (Exception e){
            cleared = false;
        }
        return cleared;
    }
}
