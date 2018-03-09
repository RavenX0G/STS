package com.mogul.xxm.sts.BmobBean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Raven on 2018/1/26.
 */

public class BusBean extends BmobObject{


    private String busNumber;
    private int busType;

    public BusBean(String tableName, String busNumber, int busType) {
        super(tableName);
        this.busNumber = busNumber;
        this.busType = busType;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    public int getBusType() {
        return busType;
    }

    public void setBusType(int busType) {
        this.busType = busType;
    }
}
