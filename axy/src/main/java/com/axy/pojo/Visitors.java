package com.axy.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 
 * @TableName visitors
 */
@TableName(value ="visitors")
public class Visitors implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer visitid;

    /**
     * 
     */
    private String visitname;

    /**
     * 
     */
    private String sex;

    /**
     * 
     */
    private String visitphone;

    /**
     * 
     */
    private Date visitdate;

    /**
     * 
     */
    private String visitreason;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public Integer getVisitid() {
        return visitid;
    }

    /**
     * 
     */
    public void setVisitid(Integer visitid) {
        this.visitid = visitid;
    }

    /**
     * 
     */
    public String getVisitname() {
        return visitname;
    }

    /**
     * 
     */
    public void setVisitname(String visitname) {
        this.visitname = visitname;
    }

    /**
     * 
     */
    public String getSex() {
        return sex;
    }

    /**
     * 
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * 
     */
    public String getVisitphone() {
        return visitphone;
    }

    /**
     * 
     */
    public void setVisitphone(String visitphone) {
        this.visitphone = visitphone;
    }

    /**
     * 
     */
    public Date getVisitdate() {
        return visitdate;
    }

    /**
     * 
     */
    public void setVisitdate(Date visitdate) {
        this.visitdate = visitdate;
    }

    /**
     * 
     */
    public String getVisitreason() {
        return visitreason;
    }

    /**
     * 
     */
    public void setVisitreason(String visitreason) {
        this.visitreason = visitreason;
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
        Visitors other = (Visitors) that;
        return (this.getVisitid() == null ? other.getVisitid() == null : this.getVisitid().equals(other.getVisitid()))
            && (this.getVisitname() == null ? other.getVisitname() == null : this.getVisitname().equals(other.getVisitname()))
            && (this.getSex() == null ? other.getSex() == null : this.getSex().equals(other.getSex()))
            && (this.getVisitphone() == null ? other.getVisitphone() == null : this.getVisitphone().equals(other.getVisitphone()))
            && (this.getVisitdate() == null ? other.getVisitdate() == null : this.getVisitdate().equals(other.getVisitdate()))
            && (this.getVisitreason() == null ? other.getVisitreason() == null : this.getVisitreason().equals(other.getVisitreason()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getVisitid() == null) ? 0 : getVisitid().hashCode());
        result = prime * result + ((getVisitname() == null) ? 0 : getVisitname().hashCode());
        result = prime * result + ((getSex() == null) ? 0 : getSex().hashCode());
        result = prime * result + ((getVisitphone() == null) ? 0 : getVisitphone().hashCode());
        result = prime * result + ((getVisitdate() == null) ? 0 : getVisitdate().hashCode());
        result = prime * result + ((getVisitreason() == null) ? 0 : getVisitreason().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", visitid=").append(visitid);
        sb.append(", visitname=").append(visitname);
        sb.append(", sex=").append(sex);
        sb.append(", visitphone=").append(visitphone);
        sb.append(", visitdate=").append(visitdate);
        sb.append(", visitreason=").append(visitreason);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }


}