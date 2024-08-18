package generator.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName users
 */
@TableName(value ="users")
public class Users implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer userid;

    /**
     * 
     */
    private String username;

    /**
     * 
     */
    private String password;

    /**
     * 
     */
    private String phonenumber;

    /**
     * 
     */
    private String email;

    /**
     * 
     */
    private String address;

    /**
     * 
     */
    private Object role;

    /**
     * 
     */
    private Date createdat;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

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
    public String getUsername() {
        return username;
    }

    /**
     * 
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 
     */
    public String getPassword() {
        return password;
    }

    /**
     * 
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 
     */
    public String getPhonenumber() {
        return phonenumber;
    }

    /**
     * 
     */
    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    /**
     * 
     */
    public String getEmail() {
        return email;
    }

    /**
     * 
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 
     */
    public String getAddress() {
        return address;
    }

    /**
     * 
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 
     */
    public Object getRole() {
        return role;
    }

    /**
     * 
     */
    public void setRole(Object role) {
        this.role = role;
    }

    /**
     * 
     */
    public Date getCreatedat() {
        return createdat;
    }

    /**
     * 
     */
    public void setCreatedat(Date createdat) {
        this.createdat = createdat;
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
        Users other = (Users) that;
        return (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()))
            && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getPhonenumber() == null ? other.getPhonenumber() == null : this.getPhonenumber().equals(other.getPhonenumber()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getRole() == null ? other.getRole() == null : this.getRole().equals(other.getRole()))
            && (this.getCreatedat() == null ? other.getCreatedat() == null : this.getCreatedat().equals(other.getCreatedat()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUserid() == null) ? 0 : getUserid().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getPhonenumber() == null) ? 0 : getPhonenumber().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getRole() == null) ? 0 : getRole().hashCode());
        result = prime * result + ((getCreatedat() == null) ? 0 : getCreatedat().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userid=").append(userid);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", phonenumber=").append(phonenumber);
        sb.append(", email=").append(email);
        sb.append(", address=").append(address);
        sb.append(", role=").append(role);
        sb.append(", createdat=").append(createdat);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}