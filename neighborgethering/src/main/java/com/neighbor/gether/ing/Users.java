package com.neighbor.gether.ing;

import java.sql.Timestamp;

public class Users {
    private int userid;
    private String username;
    private String password;
    private String phonenumber;
    private String email;
    private String address;
    private Enum role;
    private Timestamp createdat;
    public Users(){}

    public Users(int userid, String username, String password, String phonenumber, String email, String address, Enum role, Timestamp createdat) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.phonenumber = phonenumber;
        this.email = email;
        this.address = address;
        this.role = role;
        this.createdat = createdat;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Enum getRole() {
        return role;
    }

    public void setRole(Enum role) {
        this.role = role;
    }

    public Timestamp getCreatedat() {
        return createdat;
    }

    public void setCreatedat(Timestamp createdat) {
        this.createdat = createdat;
    }

    @Override
    public String toString() {
        return "Users{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", role=" + role +
                ", createdat=" + createdat +
                '}';
    }
}
