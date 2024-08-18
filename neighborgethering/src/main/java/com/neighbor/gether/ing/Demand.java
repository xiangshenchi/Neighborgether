package com.neighbor.gether.ing;

import java.sql.Timestamp;

public class Demand {
    private int demandid;
    private Enum demandtype;
    private String demandmsg;
    private Timestamp demanddate;
    private int userid;
    private int staffid;
    private Enum status;
    public Demand(){

    }

    public Demand(int demandid, Enum demandtype, String demandmsg, Timestamp demanddate, int userid, int staffid, Enum status) {
        this.demandid = demandid;
        this.demandtype = demandtype;
        this.demandmsg = demandmsg;
        this.demanddate = demanddate;
        this.userid = userid;
        this.staffid = staffid;
        this.status = status;
    }

    public int getDemandid() {
        return demandid;
    }

    public void setDemandid(int demandid) {
        this.demandid = demandid;
    }

    public Enum getDemandtype() {
        return demandtype;
    }

    public void setDemandtype(Enum demandtype) {
        this.demandtype = demandtype;
    }

    public String getDemandmsg() {
        return demandmsg;
    }

    public void setDemandmsg(String demandmsg) {
        this.demandmsg = demandmsg;
    }

    public Timestamp getDemanddate() {
        return demanddate;
    }

    public void setDemanddate(Timestamp demanddate) {
        this.demanddate = demanddate;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getStaffid() {
        return staffid;
    }

    public void setStaffid(int staffid) {
        this.staffid = staffid;
    }

    public Enum getStatus() {
        return status;
    }

    public void setStatus(Enum status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Demand{" +
                "demandid=" + demandid +
                ", demandtype=" + demandtype +
                ", demandmsg='" + demandmsg + '\'' +
                ", demanddate=" + demanddate +
                ", userid=" + userid +
                ", staffid=" + staffid +
                ", status=" + status +
                '}';
    }
}
