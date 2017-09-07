package com.education.myoschinatest.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;



public class DataBean implements Parcelable {

    @SerializedName("error")
    private int error;
    @SerializedName("data")
    private List<RoomBean> data;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public List<RoomBean> getData() {
        return data;
    }

    public void setData(List<RoomBean> data) {
        this.data = data;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.error);
        dest.writeList(this.data);
    }

    public DataBean() {
    }

    protected DataBean(Parcel in) {
        this.error = in.readInt();
        this.data = new ArrayList<RoomBean>();
        in.readList(this.data, RoomBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<DataBean> CREATOR = new Parcelable.Creator<DataBean>() {
        @Override
        public DataBean createFromParcel(Parcel source) {
            return new DataBean(source);
        }

        @Override
        public DataBean[] newArray(int size) {
            return new DataBean[size];
        }
    };
}
