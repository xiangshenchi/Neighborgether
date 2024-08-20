package com.axy.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * 
 * @TableName changepassword
 */
@TableName(value ="changepassword")
public class Changepassword implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer changepassid;

    /**
     * 
     */
    private String changephone;

    /**
     * 
     */
    private String newpassword;

    /**
     * 
     */
    private Integer adaction;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public Integer getChangepassid() {
        return changepassid;
    }

    /**
     * 
     */
    public void setChangepassid(Integer changepassid) {
        this.changepassid = changepassid;
    }

    /**
     * 
     */
    public String getChangephone() {
        return changephone;
    }

    /**
     * 
     */
    public void setChangephone(String changephone) {
        this.changephone = changephone;
    }

    /**
     * 
     */
    public String getNewpassword() {
        return newpassword;
    }

    /**
     * 
     */
    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }

    /**
     * 
     */
    public Integer getAdaction() {
        return adaction;
    }

    /**
     * 
     */
    public void setAdaction(Integer adaction) {
        this.adaction = adaction;
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
        Changepassword other = (Changepassword) that;
        return (this.getChangepassid() == null ? other.getChangepassid() == null : this.getChangepassid().equals(other.getChangepassid()))
            && (this.getChangephone() == null ? other.getChangephone() == null : this.getChangephone().equals(other.getChangephone()))
            && (this.getNewpassword() == null ? other.getNewpassword() == null : this.getNewpassword().equals(other.getNewpassword()))
            && (this.getAdaction() == null ? other.getAdaction() == null : this.getAdaction().equals(other.getAdaction()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getChangepassid() == null) ? 0 : getChangepassid().hashCode());
        result = prime * result + ((getChangephone() == null) ? 0 : getChangephone().hashCode());
        result = prime * result + ((getNewpassword() == null) ? 0 : getNewpassword().hashCode());
        result = prime * result + ((getAdaction() == null) ? 0 : getAdaction().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", changepassid=").append(changepassid);
        sb.append(", changephone=").append(changephone);
        sb.append(", newpassword=").append(newpassword);
        sb.append(", adaction=").append(adaction);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}