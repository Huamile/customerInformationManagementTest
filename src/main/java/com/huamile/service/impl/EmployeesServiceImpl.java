package com.huamile.service.impl;

import com.github.pagehelper.PageHelper;
import com.huamile.dao.EmployeesMapper;
import com.huamile.mapper.Employees;
import com.huamile.mapper.EmployeesExample;
import com.huamile.service.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Afa
 */
@Service("employeesServiceImpl")
public class EmployeesServiceImpl implements EmployeesService {
    
    @Autowired
    private EmployeesMapper employeesMapper;
    
    @Override
    public int countByExample(EmployeesExample example) {
        return employeesMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(EmployeesExample example) {
        return employeesMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer empid) {
        return employeesMapper.deleteByPrimaryKey(empid);
    }

    @Override
    public int insert(Employees record) {
        return employeesMapper.insert(record);
    }

    @Override
    public int insertSelective(Employees record) {
        return employeesMapper.insertSelective(record);
    }

    @Override
    public List<Employees> selectByExample(EmployeesExample example) {
        return employeesMapper.selectByExample(example);
    }

    @Override
    public List<Employees> selectByPage(EmployeesExample example,int page, int limit) {
        PageHelper.startPage(page,limit);
        List<Employees> employees = employeesMapper.selectByExample(example);
        return employees;
    }

    @Override
    public Employees selectByPrimaryKey(Integer empid) {
        return employeesMapper.selectByPrimaryKey(empid);
    }

    @Override
    public List<String> selectTypeByEmpLoginName(String loginName) {
        return employeesMapper.selectTypeByEmpLoginName(loginName);
    }

    @Override
    public String selectPasswordByLoginName(String loginName) {
        return employeesMapper.selectPasswordByLoginName(loginName);
    }

    @Override
    public int updateByExampleSelective(Employees record, EmployeesExample example) {
        return employeesMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(Employees record, EmployeesExample example) {
        return employeesMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(Employees record) {
        return employeesMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Employees record) {
        return employeesMapper.updateByPrimaryKey(record);
    }
}
