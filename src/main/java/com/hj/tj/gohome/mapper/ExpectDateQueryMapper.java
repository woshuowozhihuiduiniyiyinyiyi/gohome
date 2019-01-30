package com.hj.tj.gohome.mapper;

import com.hj.tj.gohome.entity.ExpectDateQuery;
import com.hj.tj.gohome.entity.ExpectDateQueryExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExpectDateQueryMapper {
    int countByExample(ExpectDateQueryExample example);

    int deleteByExample(ExpectDateQueryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ExpectDateQuery record);

    int insertSelective(ExpectDateQuery record);

    List<ExpectDateQuery> selectByExample(ExpectDateQueryExample example);

    ExpectDateQuery selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ExpectDateQuery record, @Param("example") ExpectDateQueryExample example);

    int updateByExample(@Param("record") ExpectDateQuery record, @Param("example") ExpectDateQueryExample example);

    int updateByPrimaryKeySelective(ExpectDateQuery record);

    int updateByPrimaryKey(ExpectDateQuery record);
}