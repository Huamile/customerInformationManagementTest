package com.huamile.mapper;

import sun.dc.pr.PRError;

import java.util.List;

/**
 * @author Afa
 */
public class Role {
    private int roleId;
    private String roleName;
    private String roleInfo;

    /*角色对应的权限*/
    private List<String> permission;

    public Role() {
    }

    public Role(int roleId, String roleName, String roleInfo, List<String> permission) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.roleInfo = roleInfo;
        this.permission = permission;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleInfo() {
        return roleInfo;
    }

    public void setRoleInfo(String roleInfo) {
        this.roleInfo = roleInfo;
    }

    public List<String> getPermission() {
        return permission;
    }

    public void setPermission(List<String> permission) {
        this.permission = permission;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", roleInfo='" + roleInfo + '\'' +
                ", permission=" + permission +
                '}';
    }
}
