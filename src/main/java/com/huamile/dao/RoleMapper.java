package com.huamile.dao;

import com.huamile.mapper.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Afa
 */
@Repository
public interface RoleMapper {

    List<Role> selectRolePermissionByRoleName(String roleName);

    List<Role> selectRolePermissionByRoleId(int roleId);
}
