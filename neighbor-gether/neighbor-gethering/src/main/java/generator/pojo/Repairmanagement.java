package generator.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName repairmanagement
 */
@TableName(value ="repairmanagement")
public class Repairmanagement implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer repairid;

    /**
     * 
     */
    private String repaircontent;

    /**
     * 
     */
    private Date repairdate;

    /**
     * 
     */
    private Object repairstatus;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public Integer getRepairid() {
        return repairid;
    }

    /**
     * 
     */
    public void setRepairid(Integer repairid) {
        this.repairid = repairid;
    }

    /**
     * 
     */
    public String getRepaircontent() {
        return repaircontent;
    }

    /**
     * 
     */
    public void setRepaircontent(String repaircontent) {
        this.repaircontent = repaircontent;
    }

    /**
     * 
     */
    public Date getRepairdate() {
        return repairdate;
    }

    /**
     * 
     */
    public void setRepairdate(Date repairdate) {
        this.repairdate = repairdate;
    }

    /**
     * 
     */
    public Object getRepairstatus() {
        return repairstatus;
    }

    /**
     * 
     */
    public void setRepairstatus(Object repairstatus) {
        this.repairstatus = repairstatus;
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
        Repairmanagement other = (Repairmanagement) that;
        return (this.getRepairid() == null ? other.getRepairid() == null : this.getRepairid().equals(other.getRepairid()))
            && (this.getRepaircontent() == null ? other.getRepaircontent() == null : this.getRepaircontent().equals(other.getRepaircontent()))
            && (this.getRepairdate() == null ? other.getRepairdate() == null : this.getRepairdate().equals(other.getRepairdate()))
            && (this.getRepairstatus() == null ? other.getRepairstatus() == null : this.getRepairstatus().equals(other.getRepairstatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRepairid() == null) ? 0 : getRepairid().hashCode());
        result = prime * result + ((getRepaircontent() == null) ? 0 : getRepaircontent().hashCode());
        result = prime * result + ((getRepairdate() == null) ? 0 : getRepairdate().hashCode());
        result = prime * result + ((getRepairstatus() == null) ? 0 : getRepairstatus().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", repairid=").append(repairid);
        sb.append(", repaircontent=").append(repaircontent);
        sb.append(", repairdate=").append(repairdate);
        sb.append(", repairstatus=").append(repairstatus);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}