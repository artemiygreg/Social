package ru.social.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Admin on 10.03.15.
 */
public class User implements Parcelable {
    private String phone;
    private String email;
    private String fio;
    private String avatar;
    private String token;

    public User(){

    }

    public User(String phone, String email, String fio, String avatar, String token) {

        this.phone = phone;
        this.email = email;
        this.fio = fio;
        this.avatar = avatar;
        this.token = token;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getPhone());
        dest.writeString(getEmail());
        dest.writeString(getFio());
        dest.writeString(getAvatar());
        dest.writeString(getToken());
    }
    public static final Creator CREATOR = new Creator() {
        @Override
        public User createFromParcel(Parcel source) {
            User user = new User();
            user.setPhone(source.readString());
            user.setEmail(source.readString());
            user.setFio(source.readString());
            user.setAvatar(source.readString());
            user.setToken(source.readString());

            return user;
        }

        @Override
        public User[] newArray(int size) {
            return new User[0];
        }
    };
}
