package com.hj.tj.gohome.mapper;

import com.hj.tj.gohome.entity.OrderExpectDateQuery;
import com.hj.tj.gohome.entity.OrderExpectDateQueryExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderExpectDateQueryMapper {
    int countByExample(OrderExpectDateQueryExample example);

    int deleteByExample(OrderExpectDateQueryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrderExpectDateQuery record);

    int insertSelective(OrderExpectDateQuery record);

    List<OrderExpectDateQuery> selectByExample(OrderExpectDateQueryExample example);

    OrderExpectDateQuery selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OrderExpectDateQuery record, @Param("example") OrderExpectDateQueryExample example);

    int updateByExample(@Param("record") OrderExpectDateQuery record, @Param("example") OrderExpectDateQueryExample example);

    int updateByPrimaryKeySelective(OrderExpectDateQuery record);

    int updateByPrimaryKey(OrderExpectDateQuery record);
}