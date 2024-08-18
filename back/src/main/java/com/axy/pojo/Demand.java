package com.axy.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName demand
 */
@TableName(value ="demand")
public class Demand implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer demandid;

    /**
     * 
     */
    private Object demandtype;

    /**
     * 
     */
    private String demandmsg;

    /**
     * 
     */
    private Date demanddate;

    /**
     * 
     */
    private Integer userid;

    /**
     * 
     */
    private Integer staffid;

    /**
     * 
     */
    private Object status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public Integer getDemandid() {
        return demandid;
    }

    /**
     * 
     */
    public void setDemandid(Integer demandid) {
        this.demandid = demandid;
    }

    /**
     * 
     */
    public Object getDemandtype() {
        return demandtype;
    }

    /**
     * 
     */
    public void setDemandtype(Object demandtype) {
        this.demandtype = demandtype;
    }

    /**
     * 
     */
    public String getDemandmsg() {
        return demandmsg;
    }

    /**
     * 
     */
    public void setDemandmsg(String demandmsg) {
        this.demandmsg = demandmsg;
    }

    /**
     * 
     */
    public Date getDemanddate() {
        return demanddate;
    }

    /**
     * 
     */
    public void setDemanddate(Date demanddate) {
        this.demanddate = demanddate;
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
    public Integer getStaffid() {
        return staffid;
    }

    /**
     * 
     */
    public void setStaffid(Integer staffid) {
        this.staffid = staffid;
    }

    /**
     * 
     */
    public Object getStatus() {
        return status;
    }

    /**
     * 
     */
    public void setStatus(Object status) {
        this.status = status;
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
        Demand other = (Demand) that;
        return (this.getDemandid() == null ? other.getDemandid() == null : this.getDemandid().equals(other.getDemandid()))
            && (this.getDemandtype() == null ? other.getDemandtype() == null : this.getDemandtype().equals(other.getDemandtype()))
            && (this.getDemandmsg() == null ? other.getDemandmsg() == null : this.getDemandmsg().equals(other.getDemandmsg()))
            && (this.getDemanddate() == null ? other.getDemanddate() == null : this.getDemanddate().equals(other.getDemanddate()))
            && (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()))
            && (this.getStaffid() == null ? other.getStaffid() == null : this.getStaffid().equals(other.getStaffid()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getDemandid() == null) ? 0 : getDemandid().hashCode());
        result = prime * result + ((getDemandtype() == null) ? 0 : getDemandtype().hashCode());
        result = prime * result + ((getDemandmsg() == null) ? 0 : getDemandmsg().hashCode());
        result = prime * result + ((getDemanddate() == null) ? 0 : getDemanddate().hashCode());
        result = prime * result + ((getUserid() == null) ? 0 : getUserid().hashCode());
        result = prime * result + ((getStaffid() == null) ? 0 : getStaffid().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", demandid=").append(demandid);
        sb.append(", demandtype=").append(demandtype);
        sb.append(", demandmsg=").append(demandmsg);
        sb.append(", demanddate=").append(demanddate);
        sb.append(", userid=").append(userid);
        sb.append(", staffid=").append(staffid);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}