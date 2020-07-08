package com.huamile.dao;

import com.huamile.mapper.Permission;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Afa
 */

@Repository
public interface PermissionMapper {

    List<Permission> selectByEmpLoginName(String empLoginName);

    List<Permission> selectPinfoByParentId();
}
