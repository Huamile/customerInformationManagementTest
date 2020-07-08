package com.huamile.service;

import com.huamile.mapper.Permission;

import java.util.List;

/**
 * @author Afa
 */

public interface PermissionService {

    List<Permission> selectByEmpLoginName(String empLoginName);

    List<Permission> selectPinfoByParentId();
}
