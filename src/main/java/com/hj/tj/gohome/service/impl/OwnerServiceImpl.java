package com.hj.tj.gohome.service.impl;

import com.hj.tj.gohome.entity.Owner;
import com.hj.tj.gohome.entity.OwnerExample;
import com.hj.tj.gohome.enums.BaseStatusEnum;
import com.hj.tj.gohome.mapper.OwnerMapper;
import com.hj.tj.gohome.service.OwnerService;
import com.hj.tj.gohome.utils.StringUtil;
import com.hj.tj.gohome.vo.requestVo.OwnerInsertReqObj;
import com.hj.tj.gohome.vo.requestVo.OwnerReqObj;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author tangj
 * @description
 * @since 2018/10/9 14:35
 */
@Service
public class OwnerServiceImpl implements OwnerService {

    @Resource
    private OwnerMapper ownerMapper;

    @Override
    public List<Owner> listOwner(OwnerReqObj ownerReqObj) {
        if (Objects.isNull(ownerReqObj)) {
            return null;
        }

        OwnerExample ownerExample = genExample(ownerReqObj);

        return ownerMapper.selectByExample(ownerExample);
    }

    @Override
    public Integer saveOwner(OwnerInsertReqObj ownerInsertReqObj) {
        OwnerExample ownerExample = new OwnerExample();
        ownerExample.createCriteria().andWxAccountEqualTo(ownerInsertReqObj.getWxAccount());
        List<Owner> owners = ownerMapper.selectByExample(ownerExample);

        Owner owner = new Owner();
        BeanUtils.copyProperties(ownerInsertReqObj, owner);
        owner.setUpdatedAt(new Date());

        if (CollectionUtils.isEmpty(owners) && Objects.isNull(ownerInsertReqObj.getId())) {
            owner.setCreatedAt(new Date());
            owner.setStatus(BaseStatusEnum.UN_DELETE.getValue());
            if (Objects.isNull(owner.getGender())) {
                owner.setGender((byte) 0);
            }

            ownerMapper.insertSelective(owner);
        } else {
            owner.setId(ownerInsertReqObj.getId());

            if (!CollectionUtils.isEmpty(owners)) {
                owner.setId(owners.get(0).getId());
            }

            ownerMapper.updateByPrimaryKeySelective(owner);
        }

        return owner.getId();
    }

    private OwnerExample genExample(OwnerReqObj ownerReqObj) {
        OwnerExample ownerExample = new OwnerExample();
        OwnerExample.Criteria criteria = ownerExample.createCriteria();

        if (!CollectionUtils.isEmpty(ownerReqObj.getIdList())) {
            criteria.andIdIn(ownerReqObj.getIdList());
        }

        if (StringUtil.isNotBlank(ownerReqObj.getPhone())) {
            criteria.andPhoneLike("%" + ownerReqObj.getPhone() + "%");
        }

        if (StringUtil.isNotBlank(ownerReqObj.getWxAccount())) {
            criteria.andWxAccountLike("%" + ownerReqObj.getWxAccount() + "%");
        }

        if (StringUtil.isNotBlank(ownerReqObj.getWxNickName())) {
            criteria.andWxNicknameLike("%" + ownerReqObj.getWxNickName() + "%");
        }

        criteria.andStatusEqualTo(BaseStatusEnum.UN_DELETE.getValue());

        return ownerExample;
    }
}
