package com.cherry.librarypay.model;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * Parcelable方式的实现原理是将一个完整的对象进行分解，
 * 而分解后的每一部分都是Intent所支持的数据类型，这样也就实现传递对象的功能了。
 */
public class Pen implements Parcelable{

    private int color;
    private int size;


    //自动生成的
    protected Pen(Parcel in) {
        color = in.readInt();
        size = in.readInt();
    }

    public static final Creator<Pen> CREATOR = new Creator<Pen>() {
        @Override
        public Pen createFromParcel(Parcel in) {
            return new Pen(in);
        }

        @Override
        public Pen[] newArray(int size) {
            return new Pen[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(color);
        dest.writeInt(size);
    }

    //个人添加的

    public Pen(int color, int size) {
        this.color = color;
        this.size = size;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
