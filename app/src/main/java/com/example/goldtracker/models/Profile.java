package com.example.goldtracker.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Profile implements Parcelable {
    private int id;

    private String username;
    private String email;
    private String image;

    public Profile(int id, String username, String email, String image) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.image = image;
    }

    public Profile() {
    }

    protected Profile(Parcel in) {
        id = in.readInt();
        username = in.readString();
        email = in.readString();
        image = in.readString();
    }

    public static final Creator<Profile> CREATOR = new Creator<Profile>() {
        @Override
        public Profile createFromParcel(Parcel in) {
            return new Profile(in);
        }

        @Override
        public Profile[] newArray(int size) {
            return new Profile[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(username);
        dest.writeString(email);
        dest.writeString(image);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
