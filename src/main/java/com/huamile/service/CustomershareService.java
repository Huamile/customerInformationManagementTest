package com.huamile.service;

import com.huamile.mapper.Customershare;
import com.huamile.mapper.CustomershareExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Afa
 */
public interface CustomershareService {
    int countByExample(CustomershareExample example);

    int deleteByExample(CustomershareExample example);

    int deleteByPrimaryKey(Integer sid);

    int deleteBycusId(Integer cusId);

    int insert(Customershare record);

    int insertSelective(Customershare record);

    List<Customershare> selectByExample(CustomershareExample example);

    List<Customershare> selectByPage(CustomershareExample example,int page,int limit);

    Customershare selectByPrimaryKey(Integer sid);

    int updateByExampleSelective(@Param("record") Customershare record, @Param("example") CustomershareExample example);

    int updateByExample(@Param("record") Customershare record, @Param("example") CustomershareExample example);

    int updateByPrimaryKeySelective(Customershare record);

    int updateByPrimaryKey(Customershare record);
}
