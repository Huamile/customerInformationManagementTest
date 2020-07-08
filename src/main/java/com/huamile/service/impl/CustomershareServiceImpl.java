package com.huamile.service.impl;

import com.github.pagehelper.PageHelper;
import com.huamile.dao.CustomershareMapper;
import com.huamile.mapper.Customershare;
import com.huamile.mapper.CustomershareExample;
import com.huamile.service.CustomershareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Afa
 */
@Service("customershareServiceImpl")
public class CustomershareServiceImpl implements CustomershareService {

    @Autowired
    private CustomershareMapper customershareMapper;

    @Override
    public int countByExample(CustomershareExample example) {
        return customershareMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(CustomershareExample example) {
        return customershareMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer sid) {
        return customershareMapper.deleteByPrimaryKey(sid);
    }

    @Override
    public int deleteBycusId(Integer cusId) {
        return customershareMapper.deleteBycusId(cusId);
    }

    @Override
    public int insert(Customershare record) {
        return customershareMapper.insert(record);
    }

    @Override
    public int insertSelective(Customershare record) {
        return customershareMapper.insertSelective(record);
    }

    @Override
    public List<Customershare> selectByExample(CustomershareExample example) {
        return customershareMapper.selectByExample(example);
    }

    @Override
    public List<Customershare> selectByPage(CustomershareExample example, int page, int limit) {
        PageHelper.startPage(page,limit);
        List<Customershare> customershares = customershareMapper.selectByExample(example);
        return customershares;
    }

    @Override
    public Customershare selectByPrimaryKey(Integer sid) {
        return customershareMapper.selectByPrimaryKey(sid);
    }

    @Override
    public int updateByExampleSelective(Customershare record, CustomershareExample example) {
        return customershareMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(Customershare record, CustomershareExample example) {
        return customershareMapper.updateByExample(record,example);
    }

    @Override
    public int updateByPrimaryKeySelective(Customershare record) {
        return customershareMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Customershare record) {
        return customershareMapper.updateByPrimaryKey(record);
    }
}
