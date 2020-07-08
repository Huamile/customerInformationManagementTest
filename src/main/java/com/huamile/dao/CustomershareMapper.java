package com.huamile.dao;

import com.huamile.mapper.Customershare;
import com.huamile.mapper.CustomershareExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CustomershareMapper {
    int countByExample(CustomershareExample example);

    int deleteByExample(CustomershareExample example);

    int deleteByPrimaryKey(Integer sid);

    int deleteBycusId(Integer cusid);

    int insert(Customershare record);

    int insertSelective(Customershare record);

    List<Customershare> selectByExample(CustomershareExample example);

    Customershare selectByPrimaryKey(Integer sid);

    int updateByExampleSelective(@Param("record") Customershare record, @Param("example") CustomershareExample example);

    int updateByExample(@Param("record") Customershare record, @Param("example") CustomershareExample example);

    int updateByPrimaryKeySelective(Customershare record);

    int updateByPrimaryKey(Customershare record);
}