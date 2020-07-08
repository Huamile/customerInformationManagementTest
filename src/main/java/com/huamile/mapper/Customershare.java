package com.huamile.mapper;

/**
 * @author Afa
 */
public class Customershare {
    private Integer sid;

    private Integer cusid;

    private String cusname;

    private Integer empid;

    private String empname;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getCusid() {
        return cusid;
    }

    public void setCusid(Integer cusid) {
        this.cusid = cusid;
    }

    public Integer getEmpid() {
        return empid;
    }

    public void setEmpid(Integer empid) {
        this.empid = empid;
    }

    public String getCusname() {
        return cusname;
    }

    public void setCusname(String cusname) {
        this.cusname = cusname;
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }

    @Override
    public String toString() {
        return "Customershare{" +
                "sid=" + sid +
                ", cusid=" + cusid +
                ", cusname='" + cusname + '\'' +
                ", empid=" + empid +
                ", empname='" + empname + '\'' +
                '}';
    }
}