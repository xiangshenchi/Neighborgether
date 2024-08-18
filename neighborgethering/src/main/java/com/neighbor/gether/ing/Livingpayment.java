package com.neighbor.gether.ing;

import java.sql.Timestamp;

public class Livingpayment {
    private int paymentid;
    private int userid;
    private Enum paymenttype;
    private double amount;
    private Timestamp paymentdate;
    private Enum status;
    public Livingpayment(){}

    public Livingpayment(int paymentid, int userid, Enum paymenttype, double amount, Timestamp paymentdate, Enum status) {
        this.paymentid = paymentid;
        this.userid = userid;
        this.paymenttype = paymenttype;
        this.amount = amount;
        this.paymentdate = paymentdate;
        this.status = status;
    }

    public int getPaymentid() {
        return paymentid;
    }

    public void setPaymentid(int paymentid) {
        this.paymentid = paymentid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public Enum getPaymenttype() {
        return paymenttype;
    }

    public void setPaymenttype(Enum paymenttype) {
        this.paymenttype = paymenttype;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Timestamp getPaymentdate() {
        return paymentdate;
    }

    public void setPaymentdate(Timestamp paymentdate) {
        this.paymentdate = paymentdate;
    }

    public Enum getStatus() {
        return status;
    }

    public void setStatus(Enum status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Livingpayment{" +
                "paymentid=" + paymentid +
                ", userid=" + userid +
                ", paymenttype=" + paymenttype +
                ", amount=" + amount +
                ", paymentdate=" + paymentdate +
                ", status=" + status +
                '}';
    }
}
