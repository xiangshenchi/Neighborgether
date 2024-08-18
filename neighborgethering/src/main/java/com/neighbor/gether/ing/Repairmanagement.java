package com.neighbor.gether.ing;

import java.sql.Date;

public class Repairmanagement {
    private int repairid;
    private String repaircontent;
    private Date repairdate;
    private Enum repairstatus;
    public Repairmanagement(){}

    public Repairmanagement(int repairid, String repaircontent, Date repairdate, Enum repairstatus) {
        this.repairid = repairid;
        this.repaircontent = repaircontent;
        this.repairdate = repairdate;
        this.repairstatus = repairstatus;
    }

    public int getRepairid() {
        return repairid;
    }

    public void setRepairid(int repairid) {
        this.repairid = repairid;
    }

    public String getRepaircontent() {
        return repaircontent;
    }

    public void setRepaircontent(String repaircontent) {
        this.repaircontent = repaircontent;
    }

    public Date getRepairdate() {
        return repairdate;
    }

    public void setRepairdate(Date repairdate) {
        this.repairdate = repairdate;
    }

    public Enum getRepairstatus() {
        return repairstatus;
    }

    public void setRepairstatus(Enum repairstatus) {
        this.repairstatus = repairstatus;
    }

    @Override
    public String toString() {
        return "Repairmanagement{" +
                "repairid=" + repairid +
                ", repaircontent='" + repaircontent + '\'' +
                ", repairdate=" + repairdate +
                ", repairstatus=" + repairstatus +
                '}';
    }
}