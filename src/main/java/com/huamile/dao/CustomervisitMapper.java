package com.huamile.dao;

import com.huamile.mapper.Customervisit;
import com.huamile.mapper.CustomervisitExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CustomervisitMapper {
    int countByExample(CustomervisitExample example);

    int deleteByExample(CustomervisitExample example);

    int deleteByPrimaryKey(Integer vid);

    int deleteBycusid(Integer cusid);

    int insert(Customervisit record);

    int insertSelective(Customervisit record);

    List<Customervisit> selectByExample(CustomervisitExample example);

    Customervisit selectByPrimaryKey(Integer vid);

    int updateByExampleSelective(@Param("record") Customervisit record, @Param("example") CustomervisitExample example);

    int updateByExample(@Param("record") Customervisit record, @Param("example") CustomervisitExample example);

    int updateByPrimaryKeySelective(Customervisit record);

    int updateByPrimaryKey(Customervisit record);
}