package com.hj.tj.gohome.service.impl;

import com.hj.tj.gohome.entity.PortalUser;
import com.hj.tj.gohome.entity.PortalUserExample;
import com.hj.tj.gohome.enums.BaseStatusEnum;
import com.hj.tj.gohome.enums.GenderEnum;
import com.hj.tj.gohome.mapper.PortalUserMapper;
import com.hj.tj.gohome.service.PortalUserService;
import com.hj.tj.gohome.utils.DateUtil;
import com.hj.tj.gohome.utils.JwtUtil;
import com.hj.tj.gohome.vo.responseVO.LoginResObj;
import com.hj.tj.gohome.vo.responseVO.PortalUserResObj;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author tangj
 * @description
 * @since 2018/10/10 10:37
 */
@Service
public class PortalUserServiceImpl implements PortalUserService {

    @Resource
    private PortalUserMapper portalUserMapper;

    @Override
    public PortalUser getPortalUserBySid(String sid) {
        if (StringUtils.isEmpty(sid)) {
            return null;
        }

        PortalUserExample portalUserExample = new PortalUserExample();
        portalUserExample.createCriteria().andSidEqualTo(sid).andStatusEqualTo(BaseStatusEnum.UN_DELETE.getValue());

        List<PortalUser> portalUsers = portalUserMapper.selectByExample(portalUserExample);

        if (CollectionUtils.isEmpty(portalUsers)) {
            return null;
        }

        return portalUsers.get(0);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public LoginResObj login(String account, String password) {
        if (StringUtils.isEmpty(account) || StringUtils.isEmpty(password)) {
            return null;
        }

        PortalUserExample example = new PortalUserExample();
        example.createCriteria().andAccountEqualTo(account).andPasswordEqualTo(password)
                .andStatusEqualTo(BaseStatusEnum.UN_DELETE.getValue());
        List<PortalUser> portalUsers = portalUserMapper.selectByExample(example);

        if (CollectionUtils.isEmpty(portalUsers)) {
            return null;
        }

        PortalUser portalUser = portalUsers.get(0);

        LoginResObj loginResObj = genLoginResObj(portalUser);

        portalUser.setSidExpire(DateUtil.addHour(new Date(), 6));
        portalUser.setSid(loginResObj.getSid());

        portalUserMapper.updateByPrimaryKey(portalUser);

        return loginResObj;
    }

    @Override
    public List<PortalUserResObj> listPortalUserResObj() {
        PortalUserExample example = new PortalUserExample();
        example.createCriteria().andStatusEqualTo(BaseStatusEnum.UN_DELETE.getValue());
        List<PortalUser> portalUsers = portalUserMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(portalUsers)) {
            return null;
        }

        List<PortalUserResObj> portalUserResObjs = new ArrayList<>();
        for (PortalUser portalUser : portalUsers) {
            PortalUserResObj portalUserResObj = new PortalUserResObj();
            BeanUtils.copyProperties(portalUser, portalUserResObj);

            portalUserResObjs.add(portalUserResObj);
        }

        return portalUserResObjs;
    }

    private LoginResObj genLoginResObj(PortalUser portalUser) {
        LoginResObj loginResObj = new LoginResObj();

        loginResObj.setUserId(portalUser.getId());
        loginResObj.setUserName(portalUser.getName());
        loginResObj.setPhone(portalUser.getPhone());

        loginResObj.setGender(Objects.equals(GenderEnum.MAN.getValue(), portalUser.getGender()) ?
                GenderEnum.MAN.getDescription() : GenderEnum.WOMAN.getDescription());

        String sid = JwtUtil.encode(portalUser.getId(), portalUser.getPhone());
        loginResObj.setSid(sid);

        return loginResObj;
    }
}
