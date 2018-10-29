package com.hj.tj.gohome.mapper;

import com.hj.tj.gohome.entity.Passenger;
import com.hj.tj.gohome.entity.PassengerExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PassengerMapper {
    int countByExample(PassengerExample example);

    int deleteByExample(PassengerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Passenger record);

    int insertSelective(Passenger record);

    List<Passenger> selectByExample(PassengerExample example);

    Passenger selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Passenger record, @Param("example") PassengerExample example);

    int updateByExample(@Param("record") Passenger record, @Param("example") PassengerExample example);

    int updateByPrimaryKeySelective(Passenger record);

    int updateByPrimaryKey(Passenger record);
}