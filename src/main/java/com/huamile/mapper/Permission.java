package com.huamile.mapper;

import java.util.List;

/**
 * @author Afa
 */
public class Permission {
    private int permissionId;
    private String pname;
    private String purl;
    private int ismenu;
    private int parentId;
    private String pinfo;

    /*当前权限的子权限*/
    private List<Permission> permissionsListOfSon;

    public Permission() {
    }

    public Permission(int permissionId, String pname, String purl, int ismenu, int parentId, String pinfo, List<Permission> permissionsListOfSon) {
        this.permissionId = permissionId;
        this.pname = pname;
        this.purl = purl;
        this.ismenu = ismenu;
        this.parentId = parentId;
        this.pinfo = pinfo;
        this.permissionsListOfSon = permissionsListOfSon;
    }

    public int getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPurl() {
        return purl;
    }

    public void setPurl(String purl) {
        this.purl = purl;
    }

    public int getIsmenu() {
        return ismenu;
    }

    public void setIsmenu(int ismenu) {
        this.ismenu = ismenu;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getPinfo() {
        return pinfo;
    }

    public void setPinfo(String pinfo) {
        this.pinfo = pinfo;
    }

    public List<Permission> getPermissionsListOfSon() {
        return permissionsListOfSon;
    }

    public void setPermissionsListOfSon(List<Permission> permissionsListOfSon) {
        this.permissionsListOfSon = permissionsListOfSon;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "permissionId=" + permissionId +
                ", pname='" + pname + '\'' +
                ", purl='" + purl + '\'' +
                ", ismenu=" + ismenu +
                ", parentId=" + parentId +
                ", pinfo='" + pinfo + '\'' +
                ", permissionsListOfSon=" + permissionsListOfSon +
                '}';
    }
}
