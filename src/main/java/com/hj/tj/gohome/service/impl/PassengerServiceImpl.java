package com.hj.tj.gohome.service.impl;

import com.hj.tj.gohome.entity.Passenger;
import com.hj.tj.gohome.entity.PassengerExample;
import com.hj.tj.gohome.enums.BaseStatusEnum;
import com.hj.tj.gohome.mapper.PassengerMapper;
import com.hj.tj.gohome.service.PassengerService;
import com.hj.tj.gohome.utils.StringUtil;
import com.hj.tj.gohome.vo.requestVo.PassengerInsertReqObj;
import com.hj.tj.gohome.vo.requestVo.PassengerReqObj;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author tangj
 * @description
 * @since 2018/10/15 9:49
 */
@Service
public class PassengerServiceImpl implements PassengerService {

    @Resource
    private PassengerMapper passengerMapper;

    @Override
    public List<Passenger> listPassenger(PassengerReqObj passengerReqObj) {
        if (Objects.isNull(passengerReqObj)) {
            return null;
        }

        PassengerExample example = genExample(passengerReqObj);

        return passengerMapper.selectByExample(example);
    }

    @Override
    public List<Integer> batchSavePassenger(List<PassengerInsertReqObj> passengerInsertReqObjs) {
        if (CollectionUtils.isEmpty(passengerInsertReqObjs)) {
            return null;
        }

        List<String> idCardList = passengerInsertReqObjs.stream().map(PassengerInsertReqObj::getIdCard).collect(Collectors.toList());

        PassengerExample example = new PassengerExample();
        example.createCriteria().andIdCardIn(idCardList);
        List<Passenger> passengerList = passengerMapper.selectByExample(example);

        Map<String, Passenger> passengerMap = passengerList.stream().collect(Collectors.toMap(Passenger::getIdCard, p -> p, (m1, m2) -> m1));

        List<Integer> resultIdList = new ArrayList<>();

        for (PassengerInsertReqObj passengerInsertReqObj : passengerInsertReqObjs) {
            Passenger passenger = passengerMap.get(passengerInsertReqObj.getIdCard());
            if (Objects.nonNull(passenger)) {
                // 更新
                passenger.setUpdatedAt(new Date());
                passenger.setName(passengerInsertReqObj.getName());

                passengerMapper.updateByPrimaryKeySelective(passenger);
                resultIdList.add(passenger.getId());
            } else {
                // 插入
                passenger = genPassenger(passengerInsertReqObj);

                passengerMapper.insertSelective(passenger);
                resultIdList.add(passenger.getId());
            }
        }

        return resultIdList;
    }

    private Passenger genPassenger(PassengerInsertReqObj passengerInsertReqObj) {
        Passenger passenger = new Passenger();
        passenger.setName(passengerInsertReqObj.getName());
        passenger.setUpdatedAt(new Date());
        passenger.setCreatedAt(new Date());
        passenger.setIdCard(passengerInsertReqObj.getIdCard());
        passenger.setName(passengerInsertReqObj.getName());
        passenger.setStatus(BaseStatusEnum.UN_DELETE.getValue());

        return passenger;
    }

    private PassengerExample genExample(PassengerReqObj passengerReqObj) {
        PassengerExample example = new PassengerExample();
        PassengerExample.Criteria criteria = example.createCriteria();

        if (StringUtil.isNotBlank(passengerReqObj.getIdCard())) {
            criteria.andIdCardLike("%" + passengerReqObj.getIdCard() + "%");
        }

        if (StringUtil.isNotBlank(passengerReqObj.getName())) {
            criteria.andNameLike("%" + passengerReqObj.getName() + "%");
        }

        if (!CollectionUtils.isEmpty(passengerReqObj.getIdList())) {
            criteria.andIdIn(passengerReqObj.getIdList());
        }

        criteria.andStatusEqualTo(BaseStatusEnum.UN_DELETE.getValue());

        return example;
    }
}
