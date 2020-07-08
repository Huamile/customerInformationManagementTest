package com.huamile.shiro;

import com.huamile.mapper.Permission;
import com.huamile.service.EmployeesService;
import com.huamile.service.PermissionService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Afa
 */
public class  MyRealm extends AuthorizingRealm {
    @Autowired
    private EmployeesService employeesService;
    @Autowired
    private PermissionService permissionService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        /*授权*/
        String userName = (String) principalCollection.getPrimaryPrincipal();
        //获取用户角色信息(从数据库获取)
        Set<String> roles = new HashSet<>(employeesService.selectTypeByEmpLoginName(userName));
        //获取用户权限信息(从数据库获取)
        List<Permission> ps = permissionService.selectByEmpLoginName(userName);
        Set<String> permissions = new HashSet<>();
        for (Permission p : ps) {
            permissions.add(p.getPname());
            for (Permission permission : p.getPermissionsListOfSon()) {
                permissions.add(permission.getPname());
            }
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(roles);
        info.setStringPermissions(permissions);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        /*认证*/
        String userName = (String) authenticationToken.getPrincipal();
        //从数据库获取
        String password = employeesService.selectPasswordByLoginName(userName);
        if (password == null){
            return null;
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userName,password,"MyRealm");
        info.setCredentialsSalt(ByteSource.Util.bytes(userName));
        return info;
    }
    
}
