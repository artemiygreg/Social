package ru.social.Service.Preferences;


import ru.social.Model.User;

/**
 * Created by Admin on 19.03.15.
 */
public interface MyPreferences {
    public String getToken();
    public void setToken(String token);
    public String getPhone();
    public void setPhone(String phone);
    public String getFio();
    public void setFio(String fio);
    public String getEmail();
    public void setEmail(String emial);
    public String getAvatar();
    public void setAvatar(String avatar);
    public String getObjectFromPrefs(String key);
    public User getUserFromPref();
    public void savePrefFromUser(User user);
    public boolean clearPrefs();
}
