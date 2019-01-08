package com.hj.tj.gohome.mapper;

import com.hj.tj.gohome.entity.RelOwnerPassenger;
import com.hj.tj.gohome.entity.RelOwnerPassengerExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RelOwnerPassengerMapper {
    int countByExample(RelOwnerPassengerExample example);

    int deleteByExample(RelOwnerPassengerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RelOwnerPassenger record);

    int insertSelective(RelOwnerPassenger record);

    List<RelOwnerPassenger> selectByExample(RelOwnerPassengerExample example);

    RelOwnerPassenger selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RelOwnerPassenger record, @Param("example") RelOwnerPassengerExample example);

    int updateByExample(@Param("record") RelOwnerPassenger record, @Param("example") RelOwnerPassengerExample example);

    int updateByPrimaryKeySelective(RelOwnerPassenger record);

    int updateByPrimaryKey(RelOwnerPassenger record);
}