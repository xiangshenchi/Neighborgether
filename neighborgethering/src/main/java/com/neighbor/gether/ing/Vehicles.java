package com.neighbor.gether.ing;

import java.sql.Timestamp;

public class Vehicles {
    private int vehicleid;
    private int userid;
    private String licenseplate;
    private String vehicletype;
    private Timestamp registrationdate;
    public Vehicles(){}

    public Vehicles(int vehicleid, int userid, String licenseplate, String vehicletype, Timestamp registrationdate) {
        this.vehicleid = vehicleid;
        this.userid = userid;
        this.licenseplate = licenseplate;
        this.vehicletype = vehicletype;
        this.registrationdate = registrationdate;
    }

    public int getVehicleid() {
        return vehicleid;
    }

    public void setVehicleid(int vehicleid) {
        this.vehicleid = vehicleid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getLicenseplate() {
        return licenseplate;
    }

    public void setLicenseplate(String licenseplate) {
        this.licenseplate = licenseplate;
    }

    public String getVehicletype() {
        return vehicletype;
    }

    public void setVehicletype(String vehicletype) {
        this.vehicletype = vehicletype;
    }

    public Timestamp getRegistrationdate() {
        return registrationdate;
    }

    public void setRegistrationdate(Timestamp registrationdate) {
        this.registrationdate = registrationdate;
    }

    @Override
    public String toString() {
        return "Vehicles{" +
                "vehicleid=" + vehicleid +
                ", userid=" + userid +
                ", licenseplate='" + licenseplate + '\'' +
                ", vehicletype='" + vehicletype + '\'' +
                ", registrationdate=" + registrationdate +
                '}';
    }
}
