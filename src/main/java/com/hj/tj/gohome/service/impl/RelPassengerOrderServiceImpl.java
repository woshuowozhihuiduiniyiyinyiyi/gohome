package com.hj.tj.gohome.service.impl;

import com.hj.tj.gohome.entity.PassengerExample;
import com.hj.tj.gohome.entity.RelPassengerOrder;
import com.hj.tj.gohome.entity.RelPassengerOrderExample;
import com.hj.tj.gohome.enums.BaseStatusEnum;
import com.hj.tj.gohome.mapper.RelPassengerOrderMapper;
import com.hj.tj.gohome.service.RelPassengerOrderService;
import com.hj.tj.gohome.vo.requestVo.PassengerInsertReqObj;
import com.hj.tj.gohome.vo.requestVo.RelPassengerOrderReqObj;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author tangj
 * @description
 * @since 2018/10/15 9:43
 */
@Service
public class RelPassengerOrderServiceImpl implements RelPassengerOrderService {

    @Resource
    private RelPassengerOrderMapper relPassengerOrderMapper;

    @Override
    public List<RelPassengerOrder> listRelPassengerOrder(RelPassengerOrderReqObj relPassengerOrderReqObj) {
        if (Objects.isNull(relPassengerOrderReqObj)) {
            return null;
        }

        RelPassengerOrderExample example = genExample(relPassengerOrderReqObj);

        return relPassengerOrderMapper.selectByExample(example);
    }

    @Override
    public List<Integer> saveRelPassengerOrder(List<Integer> passengerIdList, Integer orderId) {
        if (CollectionUtils.isEmpty(passengerIdList) || Objects.isNull(orderId)) {
            return null;
        }

        RelPassengerOrderExample relPassengerOrderExample = new RelPassengerOrderExample();
        relPassengerOrderExample.createCriteria().andOrderIdEqualTo(orderId).andStatusEqualTo(BaseStatusEnum.UN_DELETE.getValue());
        List<RelPassengerOrder> relPassengerOrders = relPassengerOrderMapper.selectByExample(relPassengerOrderExample);

        List<RelPassengerOrder> insertList = new ArrayList<>();
        List<RelPassengerOrder> delList = new ArrayList<>();

        if (CollectionUtils.isEmpty(relPassengerOrders)) {
            for (Integer passengerId : passengerIdList) {
                RelPassengerOrder relPassengerOrder = genRelPassengerOrder(orderId, passengerId);

                insertList.add(relPassengerOrder);
            }
        } else {
            Map<Integer, RelPassengerOrder> relPassengerOrderMap = relPassengerOrders.stream()
                    .collect(Collectors.toMap(RelPassengerOrder::getPassengerId, r -> r, (m1, m2) -> m1));

            // 在传入的参数中，不在数据库存在，则插入
            for (Integer passengerId : passengerIdList) {
                if (Objects.isNull(relPassengerOrderMap.get(passengerId))) {
                    RelPassengerOrder relPassengerOrder = genRelPassengerOrder(orderId, passengerId);

                    insertList.add(relPassengerOrder);
                }
            }

            // 在数据库中存在，不在传入的列表中，则删除
            for (RelPassengerOrder relPassengerOrder : relPassengerOrders) {
                if (!passengerIdList.contains(relPassengerOrder.getPassengerId())) {
                    delList.add(relPassengerOrder);
                }
            }
        }

        List<Integer> resultIdList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(insertList)) {
            for (RelPassengerOrder relPassengerOrder : insertList) {
                relPassengerOrderMapper.insertSelective(relPassengerOrder);
                resultIdList.add(relPassengerOrder.getId());
            }
        }

        if (!CollectionUtils.isEmpty(delList)) {
            for (RelPassengerOrder relPassengerOrder : delList) {
                relPassengerOrder.setUpdatedAt(new Date());
                relPassengerOrder.setStatus(BaseStatusEnum.DELETE.getValue());

                relPassengerOrderMapper.updateByPrimaryKeySelective(relPassengerOrder);
            }
        }

        return resultIdList;
    }

    private RelPassengerOrder genRelPassengerOrder(Integer orderId, Integer passengerId) {
        RelPassengerOrder relPassengerOrder = new RelPassengerOrder();
        relPassengerOrder.setCreatedAt(new Date());
        relPassengerOrder.setOrderId(orderId);
        relPassengerOrder.setPassengerId(passengerId);
        relPassengerOrder.setStatus(BaseStatusEnum.UN_DELETE.getValue());
        relPassengerOrder.setUpdatedAt(new Date());
        return relPassengerOrder;
    }

    private RelPassengerOrderExample genExample(RelPassengerOrderReqObj relPassengerOrderReqObj) {
        RelPassengerOrderExample example = new RelPassengerOrderExample();
        RelPassengerOrderExample.Criteria criteria = example.createCriteria();

        if (!CollectionUtils.isEmpty(relPassengerOrderReqObj.getOrderIdList())) {
            criteria.andOrderIdIn(relPassengerOrderReqObj.getOrderIdList());
        }

        if (!CollectionUtils.isEmpty(relPassengerOrderReqObj.getPassengerIdList())) {
            criteria.andPassengerIdIn(relPassengerOrderReqObj.getPassengerIdList());
        }

        criteria.andStatusEqualTo(BaseStatusEnum.UN_DELETE.getValue());

        return example;
    }
}
