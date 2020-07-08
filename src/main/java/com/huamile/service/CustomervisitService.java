package com.huamile.service;

import com.huamile.mapper.Customervisit;
import com.huamile.mapper.CustomervisitExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Afa
 */
public interface CustomervisitService {
    int countByExample(CustomervisitExample example);

    int deleteByExample(CustomervisitExample example);

    int deleteByPrimaryKey(Integer vid);

    int deleteBycusid(Integer cusid);

    int insert(Customervisit record);

    int insertSelective(Customervisit record);

    List<Customervisit> selectByExample(CustomervisitExample example);

    List<Customervisit> selectByPage(CustomervisitExample example,int page,int limit);

    Customervisit selectByPrimaryKey(Integer vid);

    int updateByExampleSelective(@Param("record") Customervisit record, @Param("example") CustomervisitExample example);

    int updateByExample(@Param("record") Customervisit record, @Param("example") CustomervisitExample example);

    int updateByPrimaryKeySelective(Customervisit record);

    int updateByPrimaryKey(Customervisit record);
}
