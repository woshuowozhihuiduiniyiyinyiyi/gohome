package com.hj.tj.gohome.mapper;

import com.hj.tj.gohome.entity.RelPassengerOrder;
import com.hj.tj.gohome.entity.RelPassengerOrderExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RelPassengerOrderMapper {
    int countByExample(RelPassengerOrderExample example);

    int deleteByExample(RelPassengerOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RelPassengerOrder record);

    int insertSelective(RelPassengerOrder record);

    List<RelPassengerOrder> selectByExample(RelPassengerOrderExample example);

    RelPassengerOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RelPassengerOrder record, @Param("example") RelPassengerOrderExample example);

    int updateByExample(@Param("record") RelPassengerOrder record, @Param("example") RelPassengerOrderExample example);

    int updateByPrimaryKeySelective(RelPassengerOrder record);

    int updateByPrimaryKey(RelPassengerOrder record);
}