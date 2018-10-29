package com.hj.tj.gohome.mapper;

import com.hj.tj.gohome.entity.PortalUser;
import com.hj.tj.gohome.entity.PortalUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PortalUserMapper {
    int countByExample(PortalUserExample example);

    int deleteByExample(PortalUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PortalUser record);

    int insertSelective(PortalUser record);

    List<PortalUser> selectByExample(PortalUserExample example);

    PortalUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PortalUser record, @Param("example") PortalUserExample example);

    int updateByExample(@Param("record") PortalUser record, @Param("example") PortalUserExample example);

    int updateByPrimaryKeySelective(PortalUser record);

    int updateByPrimaryKey(PortalUser record);
}