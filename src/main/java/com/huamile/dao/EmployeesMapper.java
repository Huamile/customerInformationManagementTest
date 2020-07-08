package com.huamile.dao;

import com.huamile.mapper.Employees;
import com.huamile.mapper.EmployeesExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EmployeesMapper {
    int countByExample(EmployeesExample example);

    int deleteByExample(EmployeesExample example);

    int deleteByPrimaryKey(Integer empid);

    int insert(Employees record);

    int insertSelective(Employees record);

    List<Employees> selectByExample(EmployeesExample example);

    Employees selectByPrimaryKey(Integer empid);

    List<String> selectTypeByEmpLoginName(String loginName);

    String selectPasswordByLoginName(String loginName);

    int updateByExampleSelective(@Param("record") Employees record, @Param("example") EmployeesExample example);

    int updateByExample(@Param("record") Employees record, @Param("example") EmployeesExample example);

    int updateByPrimaryKeySelective(Employees record);

    int updateByPrimaryKey(Employees record);
}