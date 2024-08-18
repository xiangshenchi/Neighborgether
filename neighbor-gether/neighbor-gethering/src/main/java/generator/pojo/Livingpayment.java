package generator.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @TableName livingpayment
 */
@TableName(value ="livingpayment")
public class Livingpayment implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer paymentid;

    /**
     * 
     */
    private Integer userid;

    /**
     * 
     */
    private Object paymenttype;

    /**
     * 
     */
    private BigDecimal amount;

    /**
     * 
     */
    private Date paymentdate;

    /**
     * 
     */
    private Object status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public Integer getPaymentid() {
        return paymentid;
    }

    /**
     * 
     */
    public void setPaymentid(Integer paymentid) {
        this.paymentid = paymentid;
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
    public Object getPaymenttype() {
        return paymenttype;
    }

    /**
     * 
     */
    public void setPaymenttype(Object paymenttype) {
        this.paymenttype = paymenttype;
    }

    /**
     * 
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 
     */
    public Date getPaymentdate() {
        return paymentdate;
    }

    /**
     * 
     */
    public void setPaymentdate(Date paymentdate) {
        this.paymentdate = paymentdate;
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
        Livingpayment other = (Livingpayment) that;
        return (this.getPaymentid() == null ? other.getPaymentid() == null : this.getPaymentid().equals(other.getPaymentid()))
            && (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()))
            && (this.getPaymenttype() == null ? other.getPaymenttype() == null : this.getPaymenttype().equals(other.getPaymenttype()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
            && (this.getPaymentdate() == null ? other.getPaymentdate() == null : this.getPaymentdate().equals(other.getPaymentdate()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPaymentid() == null) ? 0 : getPaymentid().hashCode());
        result = prime * result + ((getUserid() == null) ? 0 : getUserid().hashCode());
        result = prime * result + ((getPaymenttype() == null) ? 0 : getPaymenttype().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getPaymentdate() == null) ? 0 : getPaymentdate().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", paymentid=").append(paymentid);
        sb.append(", userid=").append(userid);
        sb.append(", paymenttype=").append(paymenttype);
        sb.append(", amount=").append(amount);
        sb.append(", paymentdate=").append(paymentdate);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}