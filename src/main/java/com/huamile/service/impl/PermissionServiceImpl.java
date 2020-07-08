package com.huamile.service.impl;

import com.huamile.dao.PermissionMapper;
import com.huamile.mapper.Permission;
import com.huamile.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Afa
 */
@Service("permissionServiceImpl")
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;


    @Override
    public List<Permission> selectByEmpLoginName(String empLoginName) {
        return permissionMapper.selectByEmpLoginName(empLoginName);
    }

    @Override
    public List<Permission> selectPinfoByParentId() {
        return permissionMapper.selectPinfoByParentId();
    }
}
