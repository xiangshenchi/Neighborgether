package com.axy.common;

import com.axy.pojo.Demand;

public class DupdateRequest {
    private String phonenumber;
    private Demand demand;

    public String getPhonenumber() {
        return phonenumber;
    }

    public Demand getDemand() {
        return demand;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public void setDemand(Demand demand) {
        this.demand = demand;
    }
}
