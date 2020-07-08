package com.huamile.service.impl;

import com.github.pagehelper.PageHelper;
import com.huamile.dao.CustomervisitMapper;
import com.huamile.mapper.Customervisit;
import com.huamile.mapper.CustomervisitExample;
import com.huamile.service.CustomervisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Afa
 */
@Service("customervisitServiceImpl")
public class CustomervisitServiceImpl implements CustomervisitService {

    @Autowired
    private CustomervisitMapper customervisitMapper;

    @Override
    public int countByExample(CustomervisitExample example) {
        return customervisitMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(CustomervisitExample example) {
        return customervisitMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer vid) {
        return customervisitMapper.deleteByPrimaryKey(vid);
    }

    @Override
    public int deleteBycusid(Integer cusid) {
        return customervisitMapper.deleteBycusid(cusid);
    }

    @Override
    public int insert(Customervisit record) {
        return customervisitMapper.insert(record);
    }

    @Override
    public int insertSelective(Customervisit record) {
        return customervisitMapper.insertSelective(record);
    }

    @Override
    public List<Customervisit> selectByExample(CustomervisitExample example) {
        return customervisitMapper.selectByExample(example);
    }

    @Override
    public List<Customervisit> selectByPage(CustomervisitExample example, int page, int limit) {
        PageHelper.startPage(page,limit);
        List<Customervisit> customervisits = customervisitMapper.selectByExample(example);
        return customervisits;
    }

    @Override
    public Customervisit selectByPrimaryKey(Integer vid) {
        return customervisitMapper.selectByPrimaryKey(vid);
    }

    @Override
    public int updateByExampleSelective(Customervisit record, CustomervisitExample example) {
        return customervisitMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(Customervisit record, CustomervisitExample example) {
        return customervisitMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(Customervisit record) {
        return customervisitMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Customervisit record) {
        return customervisitMapper.updateByPrimaryKey(record);
    }
}
