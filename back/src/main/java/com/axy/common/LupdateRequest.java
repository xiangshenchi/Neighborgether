package com.axy.common;

import com.axy.pojo.Livingpayment;

public class LupdateRequest {
    private String phonenumber;
    private Livingpayment livingpayment;

    public String getPhonenumber() {
        return phonenumber;
    }

    public Livingpayment getLivingpayment() {
        return livingpayment;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public void setLivingpayment(Livingpayment livingpayment) {
        this.livingpayment = livingpayment;
    }
}
