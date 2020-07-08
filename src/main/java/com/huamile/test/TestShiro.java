package com.huamile.test;

import com.huamile.dao.PermissionMapper;
import com.huamile.mapper.Permission;
import com.huamile.service.PermissionService;
import com.huamile.service.impl.PermissionServiceImpl;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Afa
 */
public class TestShiro {

    /*private static ComboPooledDataSource dataSource;*/

    /*static {
        try {
            dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/my_test?useSSL=true");
            dataSource.setDriverClass("com.mysql.jdbc.Driver");
            dataSource.setUser("root");
            dataSource.setPassword("123456");
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }*/

    /*public void testJdbcShiro(){
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        JdbcRealm jdbcRealm = new JdbcRealm();
        jdbcRealm.setDataSource(dataSource);
        //reaml默认没有打开授权开关，需手动打开
        jdbcRealm.setPermissionsLookupEnabled(true);
        String sql = "";

        defaultSecurityManager.setRealm(jdbcRealm);
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();
    }*/

    @Test
    public void testSelectPermission() throws IOException {
        ApplicationContext as = new ClassPathXmlApplicationContext("classpath:spring.xml");
        PermissionService permissionServiceImpl = as.getBean("permissionServiceImpl", PermissionService.class);
        List<Permission> hong = permissionServiceImpl.selectByEmpLoginName("hong");
        Set<String> set = new HashSet<>();

        for (Permission permission : hong) {
            set.add(permission.getPname());
            for (Permission p : permission.getPermissionsListOfSon()) {
                set.add(p.getPname());
            }
        }

        for (String s : set) {
            System.out.println(s);
        }
    }

}
