package com.neighbor.gether.ing;

import java.sql.Timestamp;

public class Visitors {
    private int visitid;
    private String visitname;
    private String visitphone;
    private Timestamp visitdate;
    private String visitreason;
    public Visitors(){}

    public Visitors(int visitid, String visitname, String visitphone, Timestamp visitdate, String visitreason) {
        this.visitid = visitid;
        this.visitname = visitname;
        this.visitphone = visitphone;
        this.visitdate = visitdate;
        this.visitreason = visitreason;
    }

    public int getVisitid() {
        return visitid;
    }

    public void setVisitid(int visitid) {
        this.visitid = visitid;
    }

    public String getVisitname() {
        return visitname;
    }

    public void setVisitname(String visitname) {
        this.visitname = visitname;
    }

    public String getVisitphone() {
        return visitphone;
    }

    public void setVisitphone(String visitphone) {
        this.visitphone = visitphone;
    }

    public Timestamp getVisitdate() {
        return visitdate;
    }

    public void setVisitdate(Timestamp visitdate) {
        this.visitdate = visitdate;
    }

    public String getVisitreason() {
        return visitreason;
    }

    public void setVisitreason(String visitreason) {
        this.visitreason = visitreason;
    }

    @Override
    public String toString() {
        return "Visitors{" +
                "visitid=" + visitid +
                ", visitname='" + visitname + '\'' +
                ", visitphone='" + visitphone + '\'' +
                ", visitdate=" + visitdate +
                ", visitreason='" + visitreason + '\'' +
                '}';
    }
}
