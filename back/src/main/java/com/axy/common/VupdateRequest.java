package com.axy.common;

import com.axy.pojo.Vehicles;

public class VupdateRequest {
    private String phonenumber;
    private Vehicles vehicles;

    public String getPhonenumber() {
        return phonenumber;
    }

    public Vehicles getVehicles() {
        return vehicles;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public void setVehicles(Vehicles vehicles) {
        this.vehicles = vehicles;
    }
}
