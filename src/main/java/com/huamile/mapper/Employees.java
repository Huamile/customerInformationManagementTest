package com.huamile.mapper;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;

public class Employees {
    private Integer empid;

    private String emploginname;

    private String emppassword;

    @Pattern(regexp = "^1[3456789]\\d{9}$",message = "请输入正确的手机号")
    private String emptel;

    private String empname;

    @Pattern(regexp = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,})$",message = "邮箱格式不正确")
    private String empemail;

    private String emptype;

    private int roleid;

    private boolean remember;

    public Integer getEmpid() {
        return empid;
    }

    public void setEmpid(Integer empid) {
        this.empid = empid;
    }

    public String getEmploginname() {
        return emploginname;
    }

    public void setEmploginname(String emploginname) {
        this.emploginname = emploginname == null ? null : emploginname.trim();
    }

    public String getEmppassword() {
        return emppassword;
    }

    public void setEmppassword(String emppassword) {
        this.emppassword = emppassword == null ? null : emppassword.trim();
    }

    public String getEmptel() {
        return emptel;
    }

    public void setEmptel(String emptel) {
        this.emptel = emptel == null ? null : emptel.trim();
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname == null ? null : empname.trim();
    }

    public String getEmpemail() {
        return empemail;
    }

    public void setEmpemail(String empemail) {
        this.empemail = empemail == null ? null : empemail.trim();
    }

    public String getEmptype() {
        return emptype;
    }

    public void setEmptype(String emptype) {
        this.emptype = emptype == null ? null : emptype.trim();
    }

    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }

    @Override
    public String toString() {
        return "Employees{" +
                "empid=" + empid +
                ", emploginname='" + emploginname + '\'' +
                ", emppassword='" + emppassword + '\'' +
                ", emptel='" + emptel + '\'' +
                ", empname='" + empname + '\'' +
                ", empemail='" + empemail + '\'' +
                ", emptype='" + emptype + '\'' +
                ", roleid=" + roleid +
                ", remember=" + remember +
                '}';
    }
}