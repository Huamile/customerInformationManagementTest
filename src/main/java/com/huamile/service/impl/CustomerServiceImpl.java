package com.huamile.service.impl;

import com.github.pagehelper.PageHelper;
import com.huamile.dao.CustomerMapper;
import com.huamile.mapper.Customer;
import com.huamile.mapper.CustomerExample;
import com.huamile.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Afa
 */
@Service("customerServiceImpl")
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public int countByExample(CustomerExample example) {
        return customerMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(CustomerExample example) {
        return customerMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer cusid) {
        return customerMapper.deleteByPrimaryKey(cusid);
    }

    @Override
    public int insert(Customer record) {
        return customerMapper.insert(record);
    }

    @Override
    public int insertSelective(Customer record) {
        return customerMapper.insertSelective(record);
    }

    @Override
    public List<Customer> selectByExample(CustomerExample example) {
        return customerMapper.selectByExample(example);
    }

    @Override
    public List<Customer> selectByPage(CustomerExample example, int page, int limit) {
        PageHelper.startPage(page,limit);
        List<Customer> customers = customerMapper.selectByExample(example);
        return customers;
    }

    @Override
    public Customer selectByPrimaryKey(Integer cusid) {
        return customerMapper.selectByPrimaryKey(cusid);
    }

    @Override
    public int updateByExampleSelective(Customer record, CustomerExample example) {
        return customerMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(Customer record, CustomerExample example) {
        return customerMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(Customer record) {
        return customerMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Customer record) {
        return customerMapper.updateByPrimaryKey(record);
    }
}
