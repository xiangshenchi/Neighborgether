package com.neighbor.gether.ing;

public class Propertyinfo {
    private int propertyid;
    private int userid;
    private String roomnumber;
    private String buildingnumber;
    private String unitnumber;
    private float area;
    public Propertyinfo(){}

    public Propertyinfo(int propertyid, int userid, String roomnumber, String buildingnumber, String unitnumber, float area) {
        this.propertyid = propertyid;
        this.userid = userid;
        this.roomnumber = roomnumber;
        this.buildingnumber = buildingnumber;
        this.unitnumber = unitnumber;
        this.area = area;
    }

    public int getPropertyid() {
        return propertyid;
    }

    public void setPropertyid(int propertyid) {
        this.propertyid = propertyid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getRoomnumber() {
        return roomnumber;
    }

    public void setRoomnumber(String roomnumber) {
        this.roomnumber = roomnumber;
    }

    public String getBuildingnumber() {
        return buildingnumber;
    }

    public void setBuildingnumber(String buildingnumber) {
        this.buildingnumber = buildingnumber;
    }

    public String getUnitnumber() {
        return unitnumber;
    }

    public void setUnitnumber(String unitnumber) {
        this.unitnumber = unitnumber;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "Propertyinfo{" +
                "propertyid=" + propertyid +
                ", userid=" + userid +
                ", roomnumber='" + roomnumber + '\'' +
                ", buildingnumber='" + buildingnumber + '\'' +
                ", unitnumber='" + unitnumber + '\'' +
                ", area=" + area +
                '}';
    }
}
