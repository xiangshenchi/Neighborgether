package com.axy.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * 
 * @TableName propertyinfo
 */
@TableName(value ="propertyinfo")
public class Propertyinfo implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer propertyid;

    /**
     * 
     */
    private Integer userid;

    /**
     * 
     */
    private String roomnumber;

    /**
     * 
     */
    private String buildingnumber;

    /**
     * 
     */
    private String unitnumber;

    /**
     * 
     */
    private Double area;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public Integer getPropertyid() {
        return propertyid;
    }

    /**
     * 
     */
    public void setPropertyid(Integer propertyid) {
        this.propertyid = propertyid;
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
    public String getRoomnumber() {
        return roomnumber;
    }

    /**
     * 
     */
    public void setRoomnumber(String roomnumber) {
        this.roomnumber = roomnumber;
    }

    /**
     * 
     */
    public String getBuildingnumber() {
        return buildingnumber;
    }

    /**
     * 
     */
    public void setBuildingnumber(String buildingnumber) {
        this.buildingnumber = buildingnumber;
    }

    /**
     * 
     */
    public String getUnitnumber() {
        return unitnumber;
    }

    /**
     * 
     */
    public void setUnitnumber(String unitnumber) {
        this.unitnumber = unitnumber;
    }

    /**
     * 
     */
    public Double getArea() {
        return area;
    }

    /**
     * 
     */
    public void setArea(Double area) {
        this.area = area;
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
        Propertyinfo other = (Propertyinfo) that;
        return (this.getPropertyid() == null ? other.getPropertyid() == null : this.getPropertyid().equals(other.getPropertyid()))
            && (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()))
            && (this.getRoomnumber() == null ? other.getRoomnumber() == null : this.getRoomnumber().equals(other.getRoomnumber()))
            && (this.getBuildingnumber() == null ? other.getBuildingnumber() == null : this.getBuildingnumber().equals(other.getBuildingnumber()))
            && (this.getUnitnumber() == null ? other.getUnitnumber() == null : this.getUnitnumber().equals(other.getUnitnumber()))
            && (this.getArea() == null ? other.getArea() == null : this.getArea().equals(other.getArea()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPropertyid() == null) ? 0 : getPropertyid().hashCode());
        result = prime * result + ((getUserid() == null) ? 0 : getUserid().hashCode());
        result = prime * result + ((getRoomnumber() == null) ? 0 : getRoomnumber().hashCode());
        result = prime * result + ((getBuildingnumber() == null) ? 0 : getBuildingnumber().hashCode());
        result = prime * result + ((getUnitnumber() == null) ? 0 : getUnitnumber().hashCode());
        result = prime * result + ((getArea() == null) ? 0 : getArea().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", propertyid=").append(propertyid);
        sb.append(", userid=").append(userid);
        sb.append(", roomnumber=").append(roomnumber);
        sb.append(", buildingnumber=").append(buildingnumber);
        sb.append(", unitnumber=").append(unitnumber);
        sb.append(", area=").append(area);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}