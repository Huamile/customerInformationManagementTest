package com.huamile.service;

import com.huamile.mapper.Customer;
import com.huamile.mapper.CustomerExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Afa
 */
public interface CustomerService {
    int countByExample(CustomerExample example);

    int deleteByExample(CustomerExample example);

    int deleteByPrimaryKey(Integer cusid);

    int insert(Customer record);

    int insertSelective(Customer record);

    List<Customer> selectByExample(CustomerExample example);

    List<Customer> selectByPage(CustomerExample example,int page,int limit);

    Customer selectByPrimaryKey(Integer cusid);

    int updateByExampleSelective(@Param("record") Customer record, @Param("example") CustomerExample example);

    int updateByExample(@Param("record") Customer record, @Param("example") CustomerExample example);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);
}
