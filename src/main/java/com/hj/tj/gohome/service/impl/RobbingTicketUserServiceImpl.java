package com.hj.tj.gohome.service.impl;

import com.hj.tj.gohome.entity.RobbingTicketUser;
import com.hj.tj.gohome.entity.RobbingTicketUserExample;
import com.hj.tj.gohome.enums.BaseStatusEnum;
import com.hj.tj.gohome.mapper.RobbingTicketUserMapper;
import com.hj.tj.gohome.service.RobbingTicketUserService;
import com.hj.tj.gohome.utils.StringUtil;
import com.hj.tj.gohome.vo.requestVo.RobbingTicketUserReqObj;
import com.hj.tj.gohome.vo.responseVO.RobbingTicketUserResObj;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class RobbingTicketUserServiceImpl implements RobbingTicketUserService {

    @Resource
    private RobbingTicketUserMapper robbingTicketUserMapper;

    @Override
    public List<RobbingTicketUser> listRobbingTicketUser(RobbingTicketUserReqObj robbingTicketUserReqObj) {
        if (Objects.isNull(robbingTicketUserReqObj)) {
            return null;
        }

        RobbingTicketUserExample robbingTicketUserExample = genExample(robbingTicketUserReqObj);

        return robbingTicketUserMapper.selectByExample(robbingTicketUserExample);
    }

    @Override
    public List<RobbingTicketUserResObj> listRobbingTicketUserResObj(RobbingTicketUserReqObj robbingTicketUserReqObj) {
        List<RobbingTicketUser> robbingTicketUsers = this.listRobbingTicketUser(robbingTicketUserReqObj);
        if (CollectionUtils.isEmpty(robbingTicketUsers)) {
            return new ArrayList<>();
        }

        List<RobbingTicketUserResObj> resultList = new ArrayList<>();
        for (RobbingTicketUser robbingTicketUser : robbingTicketUsers) {
            RobbingTicketUserResObj robbingTicketUserResObj = new RobbingTicketUserResObj();
            BeanUtils.copyProperties(robbingTicketUser, robbingTicketUserResObj);

            resultList.add(robbingTicketUserResObj);
        }

        return resultList;
    }

    private RobbingTicketUserExample genExample(RobbingTicketUserReqObj robbingTicketUserReqObj) {
        RobbingTicketUserExample robbingTicketUserExample = new RobbingTicketUserExample();
        RobbingTicketUserExample.Criteria criteria = robbingTicketUserExample.createCriteria();

        if (StringUtil.isNotBlank(robbingTicketUserReqObj.getName())) {
            criteria.andNameLike("%" + robbingTicketUserReqObj.getName() + "%");
        }

        if (!CollectionUtils.isEmpty(robbingTicketUserReqObj.getRobbingIdList())) {
            criteria.andIdIn(robbingTicketUserReqObj.getRobbingIdList());
        }

        criteria.andStatusEqualTo(BaseStatusEnum.UN_DELETE.getValue());

        return robbingTicketUserExample;
    }
}
