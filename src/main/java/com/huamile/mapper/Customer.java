package com.huamile.mapper;

import javax.validation.constraints.Pattern;

public class Customer {
    private Integer cusid;

    private String cusname;

    private String cusaddress;

    private String contact;

    @Pattern(regexp = "^1[3456789]\\d{9}$",message = "请输入正确的手机号")
    private String custel;

    @Pattern(regexp = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,})$",message = "邮箱格式不正确")
    private String cusemail;

    public Integer getCusid() {
        return cusid;
    }

    public void setCusid(Integer cusid) {
        this.cusid = cusid;
    }

    public String getCusname() {
        return cusname;
    }

    public void setCusname(String cusname) {
        this.cusname = cusname == null ? null : cusname.trim();
    }

    public String getCusaddress() {
        return cusaddress;
    }

    public void setCusaddress(String cusaddress) {
        this.cusaddress = cusaddress == null ? null : cusaddress.trim();
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    public String getCustel() {
        return custel;
    }

    public void setCustel(String custel) {
        this.custel = custel == null ? null : custel.trim();
    }

    public String getCusemail() {
        return cusemail;
    }

    public void setCusemail(String cusemail) {
        this.cusemail = cusemail == null ? null : cusemail.trim();
    }

    @Override
    public String toString() {
        return "Customer{" +
                "cusid=" + cusid +
                ", cusname='" + cusname + '\'' +
                ", cusaddress='" + cusaddress + '\'' +
                ", contact='" + contact + '\'' +
                ", custel='" + custel + '\'' +
                ", cusemail='" + cusemail + '\'' +
                '}';
    }
}