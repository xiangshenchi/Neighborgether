package com.axy.common;

import com.axy.pojo.Propertyinfo;

public class UpdateRequest {
    private String phonenumber;
    private Propertyinfo propertyinfo;

    public String getPhonenumber() {
        return phonenumber;
    }

    public Propertyinfo getPropertyinfo() {
        return propertyinfo;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public void setPropertyinfo(Propertyinfo propertyinfo) {
        this.propertyinfo = propertyinfo;
    }
    // getters and setters
}
