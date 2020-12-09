package com.example.goldtracker.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Wallet implements Parcelable {
    private String buy;
    private String sell;
    private String date;

    public Wallet(String buy, String sell, String date) {
        this.buy = buy;
        this.sell = sell;
        this.date = date;
    }

    public Wallet() {
    }

    protected Wallet(Parcel in) {
        buy = in.readString();
        sell = in.readString();
        date = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(buy);
        dest.writeString(sell);
        dest.writeString(date);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Wallet> CREATOR = new Creator<Wallet>() {
        @Override
        public Wallet createFromParcel(Parcel in) {
            return new Wallet(in);
        }

        @Override
        public Wallet[] newArray(int size) {
            return new Wallet[size];
        }
    };

    public String getBuy() {
        return buy;
    }

    public void setBuy(String buy) {
        this.buy = buy;
    }

    public String getSell() {
        return sell;
    }

    public void setSell(String sell) {
        this.sell = sell;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
