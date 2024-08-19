package com.axy.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName vehicles
 */
@TableName(value ="vehicles")
public class Vehicles implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer vehicleid;

    /**
     * 
     */
    private Integer userid;

    /**
     * 
     */
    private String licenseplate;

    /**
     * 
     */
    private String vehicletype;

    /**
     * 
     */
    private Date registrationdate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public Integer getVehicleid() {
        return vehicleid;
    }

    /**
     * 
     */
    public void setVehicleid(Integer vehicleid) {
        this.vehicleid = vehicleid;
    }

    /**
     * 
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * 
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * 
     */
    public String getLicenseplate() {
        return licenseplate;
    }

    /**
     * 
     */
    public void setLicenseplate(String licenseplate) {
        this.licenseplate = licenseplate;
    }

    /**
     * 
     */
    public String getVehicletype() {
        return vehicletype;
    }

    /**
     * 
     */
    public void setVehicletype(String vehicletype) {
        this.vehicletype = vehicletype;
    }

    /**
     * 
     */
    public Date getRegistrationdate() {
        return registrationdate;
    }

    /**
     * 
     */
    public void setRegistrationdate(Date registrationdate) {
        this.registrationdate = registrationdate;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Vehicles other = (Vehicles) that;
        return (this.getVehicleid() == null ? other.getVehicleid() == null : this.getVehicleid().equals(other.getVehicleid()))
            && (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()))
            && (this.getLicenseplate() == null ? other.getLicenseplate() == null : this.getLicenseplate().equals(other.getLicenseplate()))
            && (this.getVehicletype() == null ? other.getVehicletype() == null : this.getVehicletype().equals(other.getVehicletype()))
            && (this.getRegistrationdate() == null ? other.getRegistrationdate() == null : this.getRegistrationdate().equals(other.getRegistrationdate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getVehicleid() == null) ? 0 : getVehicleid().hashCode());
        result = prime * result + ((getUserid() == null) ? 0 : getUserid().hashCode());
        result = prime * result + ((getLicenseplate() == null) ? 0 : getLicenseplate().hashCode());
        result = prime * result + ((getVehicletype() == null) ? 0 : getVehicletype().hashCode());
        result = prime * result + ((getRegistrationdate() == null) ? 0 : getRegistrationdate().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", vehicleid=").append(vehicleid);
        sb.append(", userid=").append(userid);
        sb.append(", licenseplate=").append(licenseplate);
        sb.append(", vehicletype=").append(vehicletype);
        sb.append(", registrationdate=").append(registrationdate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}