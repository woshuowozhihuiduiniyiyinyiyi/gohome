package com.hj.tj.gohome.mapper;

import com.hj.tj.gohome.entity.RobbingTicketUser;
import com.hj.tj.gohome.entity.RobbingTicketUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RobbingTicketUserMapper {
    int countByExample(RobbingTicketUserExample example);

    int deleteByExample(RobbingTicketUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RobbingTicketUser record);

    int insertSelective(RobbingTicketUser record);

    List<RobbingTicketUser> selectByExample(RobbingTicketUserExample example);

    RobbingTicketUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RobbingTicketUser record, @Param("example") RobbingTicketUserExample example);

    int updateByExample(@Param("record") RobbingTicketUser record, @Param("example") RobbingTicketUserExample example);

    int updateByPrimaryKeySelective(RobbingTicketUser record);

    int updateByPrimaryKey(RobbingTicketUser record);
}