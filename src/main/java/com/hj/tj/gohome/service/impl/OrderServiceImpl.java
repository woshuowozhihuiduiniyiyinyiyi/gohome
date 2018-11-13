package com.hj.tj.gohome.service.impl;

import com.hj.tj.gohome.entity.*;
import com.hj.tj.gohome.enums.BaseStatusEnum;
import com.hj.tj.gohome.enums.ErrorMsgEnum;
import com.hj.tj.gohome.enums.OrderStatusEnum;
import com.hj.tj.gohome.exception.CustomException;
import com.hj.tj.gohome.json.Page;
import com.hj.tj.gohome.mapper.*;
import com.hj.tj.gohome.service.*;
import com.hj.tj.gohome.utils.DateUtil;
import com.hj.tj.gohome.utils.StringUtil;
import com.hj.tj.gohome.vo.requestVo.*;
import com.hj.tj.gohome.vo.responseVO.OrderResObj;
import com.hj.tj.gohome.vo.responseVO.OrderStatisticDataResObj;
import com.hj.tj.gohome.vo.responseVO.PassengerResObj;
import org.hibernate.validator.constraints.pl.REGON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private OwnerService ownerService;

    @Resource
    private RobbingTicketUserService robbingTicketUserService;

    @Resource
    private PassengerService passengerService;

    @Resource
    private RelPassengerOrderService relPassengerOrderService;

    @Resource
    private PortalUserMapper portalUserMapper;

    @Override
    public List<OrderResObj> listOrder(OrderReqObj orderReqObj, Page page) {
        if (Objects.isNull(page)) {
            return null;
        }

        List<Integer> queryIdList = new ArrayList<>();
        if (StringUtil.isNotBlank(orderReqObj.getOwnerWxNickName()) || StringUtil.isNotBlank(orderReqObj.getOwnerWxAccount())) {
            getOrderIdsByOwner(orderReqObj, queryIdList);
        }

        if (StringUtil.isNotBlank(orderReqObj.getRobbingUserName())) {
            getIdListByRobbingName(orderReqObj, queryIdList);
        }

        if (StringUtil.isNotBlank(orderReqObj.getPassengerIdCard()) || StringUtil.isNotBlank(orderReqObj.getPassengerName())) {
            getIdListByPassengerInfo(orderReqObj, queryIdList);
        }

        if (!CollectionUtils.isEmpty(queryIdList)) {
            orderReqObj.setIdList(queryIdList);
        }

        OrderExample orderExample = genExample(orderReqObj);
        orderExample.setOrderByClause(page.getOrderByIdDesc());

        List<Order> orders = orderMapper.selectByExample(orderExample);

        if (CollectionUtils.isEmpty(orders)) {
            return new ArrayList<>();
        }

        List<Integer> orderIds = orders.stream().map(Order::getId).collect(Collectors.toList());

        // 业主信息map
        List<Integer> ownerIds = orders.stream().map(Order::getOwnerId).collect(Collectors.toList());
        Map<Integer, Owner> ownerInfoMap = getOwnerInfoMap(ownerIds);

        // 抢票人员map
        List<Integer> robbingTicketUserIds = orders.stream().map(Order::getRobbingTicketUserId).collect(Collectors.toList());
        Map<Integer, RobbingTicketUser> robbingInfoMap = getRobbingInfoMap(robbingTicketUserIds);

        // 旅客map
        Map<Integer, List<Passenger>> passengerInfoMap = getPassengerInfoMap(orderIds);

        List<OrderResObj> orderResObjList = new ArrayList<>(orders.size());
        for (Order order : orders) {
            OrderResObj orderResObj = convertToResObj(order, ownerInfoMap, robbingInfoMap, passengerInfoMap);

            orderResObjList.add(orderResObj);
        }

        return orderResObjList;
    }

    @Override
    public Integer countByReqObj(OrderReqObj orderReqObj) {

        List<Integer> queryIdList = new ArrayList<>();
        if (StringUtil.isNotBlank(orderReqObj.getOwnerWxNickName()) || StringUtil.isNotBlank(orderReqObj.getOwnerWxAccount())) {
            getOrderIdsByOwner(orderReqObj, queryIdList);
        }

        if (StringUtil.isNotBlank(orderReqObj.getRobbingUserName())) {
            getIdListByRobbingName(orderReqObj, queryIdList);
        }

        if (StringUtil.isNotBlank(orderReqObj.getPassengerIdCard()) || StringUtil.isNotBlank(orderReqObj.getPassengerName())) {
            getIdListByPassengerInfo(orderReqObj, queryIdList);
        }

        if (!CollectionUtils.isEmpty(queryIdList)) {
            orderReqObj.setIdList(queryIdList);
        }

        OrderExample orderExample = genExample(orderReqObj);

        return orderMapper.countByExample(orderExample);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public Integer saveOrder(OrderInsertReqObj orderInsertReqObj) throws Exception {
        Integer ownerId = ownerService.saveOwner(orderInsertReqObj.getOwnerInfo());
        if (Objects.isNull(ownerId) || ownerId.compareTo(0) <= 0) {
            logger.error("[action = `saveOrder`, save owner error.ownerId:{}]", ownerId);
            throw new CustomException(ErrorMsgEnum.SYS_ERR);
        }

        Integer orderId;
        if (Objects.nonNull(orderInsertReqObj.getId())) {
            // 更新
            Order order = genOrder(orderInsertReqObj, ownerId);

            orderMapper.updateByPrimaryKeySelective(order);

            orderId = orderInsertReqObj.getId();
        } else {
            // 插入
            Order order = genOrder(orderInsertReqObj, ownerId);
            order.setCreatedAt(new Date());

            orderMapper.insertSelective(order);

            orderId = order.getId();
        }

        List<Integer> passengerIdList = passengerService.batchSavePassenger(orderInsertReqObj.getPassengerList());
        if (CollectionUtils.isEmpty(passengerIdList)) {
            logger.error("[action = `saveOrder`, save passenger error.passengerIdList:{}]", passengerIdList);
            throw new CustomException(ErrorMsgEnum.SYS_ERR);
        }

        // 插入订单旅客关联表
        relPassengerOrderService.saveRelPassengerOrder(passengerIdList, orderId);

        return orderId;
    }

    @Override
    public OrderResObj getOrderDetail(Integer id) {
        if (Objects.isNull(id)) {
            return null;
        }

        Order order = orderMapper.selectByPrimaryKey(id);
        if (Objects.isNull(order) || Objects.equals(order.getStatus(), OrderStatusEnum.DELETE.getValue())) {
            throw new CustomException(ErrorMsgEnum.ORDER_NOT_EXIST);
        }


        // 业主信息map
        List<Integer> ownerIds = Arrays.asList(order.getOwnerId());
        Map<Integer, Owner> ownerInfoMap = getOwnerInfoMap(ownerIds);

        // 抢票人员map
        List<Integer> robbingTicketUserIds = Arrays.asList(order.getRobbingTicketUserId());
        Map<Integer, RobbingTicketUser> robbingInfoMap = getRobbingInfoMap(robbingTicketUserIds);

        // 旅客map
        List<Integer> orderIds = Arrays.asList(id);
        Map<Integer, List<Passenger>> passengerInfoMap = getPassengerInfoMap(orderIds);

        OrderResObj orderResObj = convertToResObj(order, ownerInfoMap, robbingInfoMap, passengerInfoMap);

        orderResObj.setPortalUserName("");
        PortalUser portalUser = portalUserMapper.selectByPrimaryKey(order.getPortalUserId());
        if (Objects.nonNull(portalUser)) {
            orderResObj.setPortalUserName(portalUser.getName());
        }

        return orderResObj;
    }

    @Override
    public void deleteOrder(List<Integer> orderIdList) {
        if (CollectionUtils.isEmpty(orderIdList)) {
            throw new CustomException(ErrorMsgEnum.PARAM_ERROR);
        }

        Order order = new Order();
        order.setStatus(OrderStatusEnum.DELETE.getValue());
        order.setUpdatedAt(new Date());

        OrderExample orderExample = new OrderExample();
        orderExample.createCriteria().andIdIn(orderIdList);

        orderMapper.updateByExampleSelective(order, orderExample);
    }

    @Override
    public OrderStatisticDataResObj statisticData() {
        OrderStatisticDataResObj orderStatisticDataResObj = new OrderStatisticDataResObj();

        List<Byte> statusList = Arrays.asList(OrderStatusEnum.CLOSED.getValue(), OrderStatusEnum.SUCCESS.getValue(),
                OrderStatusEnum.ROBBING.getValue());

        OrderExample orderExample = new OrderExample();
        orderExample.createCriteria().andStatusIn(statusList);
        int orderCount = orderMapper.countByExample(orderExample);

        orderStatisticDataResObj.setTotalOrderCount(orderCount);

        orderExample = new OrderExample();
        orderExample.createCriteria().andStatusEqualTo(OrderStatusEnum.ROBBING.getValue());
        int robbingCount = orderMapper.countByExample(orderExample);

        orderStatisticDataResObj.setRobbingCount(robbingCount);

        orderExample = new OrderExample();
        orderExample.createCriteria().andStatusEqualTo(OrderStatusEnum.SUCCESS.getValue());
        int successCount = orderMapper.countByExample(orderExample);

        orderStatisticDataResObj.setSuccessCount(successCount);

        int totalProfit = orderMapper.getTotalProfit(statusList);

        orderStatisticDataResObj.setTotalProfit(totalProfit / 100.0);

        return orderStatisticDataResObj;
    }

    /**
     * 构造订单信息
     *
     * @param orderInsertReqObj 插入订单请求参数
     * @return 订单实体
     */
    private Order genOrder(OrderInsertReqObj orderInsertReqObj, Integer ownerId) {
        Order order = new Order();
        BeanUtils.copyProperties(orderInsertReqObj, order);

        order.setPrice(0);
        order.setProfit(0);
        if (Objects.nonNull(orderInsertReqObj.getPrice())) {
            order.setPrice((int) (orderInsertReqObj.getPrice().doubleValue() * 100));
            if (Objects.nonNull(orderInsertReqObj.getRobbingPrice())) {
                double profit = orderInsertReqObj.getPrice() - orderInsertReqObj.getRobbingPrice();
                order.setProfit((int) (profit * 100));
            }
        }

        order.setStatus(orderInsertReqObj.getStatus().byteValue());
        if (Objects.isNull(orderInsertReqObj.getStatus())) {
            order.setStatus(BaseStatusEnum.UN_DELETE.getValue());
        }

        order.setUpdatedAt(new Date());

        order.setOwnerId(ownerId);

        return order;
    }

    /**
     * 根据订单id 列表来获取对应订单抢票人员信息
     *
     * @param robbingTicketUserIds 抢票人员id 列表
     * @return key：抢票人员Id ， value：抢票人信息
     */
    private Map<Integer, RobbingTicketUser> getRobbingInfoMap(List<Integer> robbingTicketUserIds) {
        if (CollectionUtils.isEmpty(robbingTicketUserIds)) {
            return new HashMap<>();
        }

        RobbingTicketUserReqObj robbingTicketUserReqObj = new RobbingTicketUserReqObj();
        robbingTicketUserReqObj.setRobbingIdList(robbingTicketUserIds);

        List<RobbingTicketUser> robbingTicketUsers = robbingTicketUserService.listRobbingTicketUser(robbingTicketUserReqObj);
        if (CollectionUtils.isEmpty(robbingTicketUsers)) {
            return new HashMap<>();
        }

        return robbingTicketUsers.stream().collect(Collectors.toMap(RobbingTicketUser::getId, r -> r, (m1, m2) -> m1));
    }

    private Map<Integer, List<Passenger>> getPassengerInfoMap(List<Integer> orderIds) {
        RelPassengerOrderReqObj relPassengerOrderReqObj = new RelPassengerOrderReqObj();
        relPassengerOrderReqObj.setOrderIdList(orderIds);

        List<RelPassengerOrder> relPassengerOrders = relPassengerOrderService.listRelPassengerOrder(relPassengerOrderReqObj);
        if (CollectionUtils.isEmpty(relPassengerOrders)) {
            return new HashMap<>();
        }

        List<Integer> passengerIds = relPassengerOrders.stream().map(RelPassengerOrder::getPassengerId).collect(Collectors.toList());

        PassengerReqObj passengerReqObj = new PassengerReqObj();
        passengerReqObj.setIdList(passengerIds);
        List<Passenger> passengers = passengerService.listPassenger(passengerReqObj);

        if (CollectionUtils.isEmpty(passengers)) {
            return new HashMap<>();
        }

        Map<Integer, List<RelPassengerOrder>> relPassengerOrderMap = relPassengerOrders.stream()
                .collect(Collectors.groupingBy(RelPassengerOrder::getOrderId));

        Map<Integer, Passenger> passengerMap = passengers.stream().collect(Collectors.toMap(Passenger::getId, p -> p, (m1, m2) -> m1));

        Map<Integer, List<Passenger>> resultMap = new HashMap<>();

        for (Integer orderId : orderIds) {
            List<RelPassengerOrder> relPassengerOrderList = relPassengerOrderMap.get(orderId);
            if (!CollectionUtils.isEmpty(relPassengerOrderList)) {
                List<Passenger> passengerList = resultMap.get(orderId);
                if (Objects.isNull(passengerList)) {
                    passengerList = new ArrayList<>();
                    resultMap.put(orderId, passengerList);
                }

                for (RelPassengerOrder tempRelPassengerOrder : relPassengerOrderList) {
                    Passenger passenger = passengerMap.get(tempRelPassengerOrder.getPassengerId());

                    if (Objects.nonNull(passenger)) {
                        passengerList.add(passenger);
                    }
                }
            }
        }

        return resultMap;
    }


    /**
     * 根据订单id 列表,来获取对应的业主信息Map
     *
     * @param ownerIds 订单id 列表
     * @return key:订单Id,value:业主信息
     */
    private Map<Integer, Owner> getOwnerInfoMap(List<Integer> ownerIds) {
        OwnerReqObj ownerReqObj = new OwnerReqObj();
        ownerReqObj.setIdList(ownerIds);
        List<Owner> owners = ownerService.listOwner(ownerReqObj);

        if (CollectionUtils.isEmpty(owners)) {
            return new HashMap<>();
        }

        return owners.stream().collect(Collectors.toMap(Owner::getId, o -> o, (m1, m2) -> m1));
    }

    /**
     * 根据抢票用户名来获取该抢票用户的订单Id 列表
     *
     * @param orderReqObj 根据抢票人查询订单Id 列表
     * @param queryIdList 查询成功的订单Id 列表
     */
    private void getIdListByRobbingName(OrderReqObj orderReqObj, List<Integer> queryIdList) {
        RobbingTicketUserReqObj robbingTicketUserReqObj = new RobbingTicketUserReqObj();
        robbingTicketUserReqObj.setName(orderReqObj.getRobbingUserName());

        List<RobbingTicketUser> robbingTicketUsers = robbingTicketUserService.listRobbingTicketUser(robbingTicketUserReqObj);
        if (CollectionUtils.isEmpty(robbingTicketUsers)) {
            queryIdList.add(-1);
            return;
        }

        List<Integer> robbingTicketUserIds = robbingTicketUsers.stream().map(RobbingTicketUser::getId).collect(Collectors.toList());

        OrderExample example = new OrderExample();
        example.createCriteria().andRobbingTicketUserIdIn(robbingTicketUserIds);

        List<Order> orders = orderMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(orders)) {
            queryIdList.add(-1);
            return;
        }

        List<Integer> orderIds = orders.stream().map(Order::getId).collect(Collectors.toList());

        queryIdList.addAll(orderIds);
    }

    /**
     * 根据业主用户来获取该业主对应的订单Id 列表
     *
     * @param orderReqObj
     * @param queryIdList
     */
    private void getOrderIdsByOwner(OrderReqObj orderReqObj, List<Integer> queryIdList) {
        OwnerReqObj ownerReqObj = new OwnerReqObj();
        ownerReqObj.setWxAccount(orderReqObj.getOwnerWxAccount());
        ownerReqObj.setWxNickName(orderReqObj.getOwnerWxNickName());
        List<Owner> owners = ownerService.listOwner(ownerReqObj);

        if (CollectionUtils.isEmpty(owners)) {
            queryIdList.add(-1);
            return;
        }

        List<Integer> ownerIds = owners.stream().map(Owner::getId).collect(Collectors.toList());

        OrderExample orderExample = new OrderExample();
        orderExample.createCriteria().andOwnerIdIn(ownerIds);

        List<Order> orders = orderMapper.selectByExample(orderExample);
        if (CollectionUtils.isEmpty(orders)) {
            queryIdList.add(-1);
            return;
        }

        List<Integer> orderIds = orders.stream().map(Order::getId).collect(Collectors.toList());

        queryIdList.addAll(orderIds);
    }

    private void getIdListByPassengerInfo(OrderReqObj orderReqObj, List<Integer> queryIdList) {
        List<RelPassengerOrder> relPassengerOrder = getRelPassengerOrder(orderReqObj);

        if (CollectionUtils.isEmpty(relPassengerOrder)) {
            queryIdList.add(-1);
        } else {
            List<Integer> orderIds = relPassengerOrder.stream().map(RelPassengerOrder::getOrderId).collect(Collectors.toList());
            queryIdList.addAll(orderIds);
        }
    }

    private OrderResObj convertToResObj(Order order, Map<Integer, Owner> ownerMap, Map<Integer, RobbingTicketUser> robbingTicketUserMap,
                                        Map<Integer, List<Passenger>> passengerInfoMap) {
        OrderResObj orderResObj = new OrderResObj();
        BeanUtils.copyProperties(order, orderResObj);

        orderResObj.setCreatedAtStr(DateUtil.formatDateNormal(order.getCreatedAt()));
        orderResObj.setDepartureDateStr(DateUtil.formatDateNormal(order.getDepartureDate()));
        orderResObj.setPrice(order.getPrice() / 100.0);
        orderResObj.setProfit(order.getProfit() / 100.0);
        orderResObj.setServicePrice((order.getPrice() - order.getProfit()) / 100.0);
        orderResObj.setRobbingTicketUserId(order.getPortalUserId());
        orderResObj.setPortalUserId(order.getPortalUserId());

        orderResObj.setStatus(order.getStatus().intValue());
        OrderStatusEnum orderStatusEnum = OrderStatusEnum.getOrderStatusEnumByValue(order.getStatus());
        if (Objects.nonNull(orderStatusEnum)) {
            orderResObj.setStatusStr(orderStatusEnum.getDescription());
        }

        orderResObj.setPassengerList(new ArrayList<>());
        if (Objects.nonNull(passengerInfoMap.get(order.getId()))) {
            List<Passenger> passengerList = passengerInfoMap.get(order.getId());
            List<PassengerResObj> passengerResObjs = new ArrayList<>();
            for (Passenger passenger : passengerList) {
                PassengerResObj passengerResObj = new PassengerResObj();
                passengerResObj.setIdCard(passenger.getIdCard());
                passengerResObj.setName(passenger.getName());
                passengerResObjs.add(passengerResObj);
            }

            orderResObj.setPassengerList(passengerResObjs);
        }


        OwnerResObj ownerResObj = new OwnerResObj();
        ownerResObj.setPhone("");
        ownerResObj.setWxAccount("");
        ownerResObj.setWxNickname("");
        if (Objects.nonNull(ownerMap.get(order.getOwnerId()))) {
            Owner owner = ownerMap.get(order.getOwnerId());
            ownerResObj.setWxAccount(owner.getWxAccount());
            ownerResObj.setWxNickname(owner.getWxNickname());
            ownerResObj.setPhone(owner.getPhone());
            ownerResObj.setId(owner.getId());
        }
        orderResObj.setOwnerInfo(ownerResObj);

        orderResObj.setRobbingTicketUserName("");
        if (Objects.nonNull(robbingTicketUserMap.get(order.getRobbingTicketUserId()))) {
            RobbingTicketUser robbingTicketUser = robbingTicketUserMap.get(order.getRobbingTicketUserId());
            orderResObj.setRobbingTicketUserName(robbingTicketUser.getName());
        }

        return orderResObj;
    }

    private OrderExample genExample(OrderReqObj orderReqObj) {
        OrderExample orderExample = new OrderExample();
        OrderExample.Criteria criteria = orderExample.createCriteria();

        if (Objects.nonNull(orderReqObj.getId())) {
            criteria.andIdEqualTo(orderReqObj.getId());
        }

        if (!CollectionUtils.isEmpty(orderReqObj.getIdList())) {
            criteria.andIdIn(orderReqObj.getIdList());
        }

        if (Objects.nonNull(orderReqObj.getCreatedAtMax()) && Objects.nonNull(orderReqObj.getCreatedAtMin())) {
            criteria.andCreatedAtBetween(orderReqObj.getCreatedAtMin(), orderReqObj.getCreatedAtMax());
        } else if (Objects.nonNull(orderReqObj.getCreatedAtMin())) {
            criteria.andCreatedAtGreaterThanOrEqualTo(orderReqObj.getCreatedAtMin());
        } else if (Objects.nonNull(orderReqObj.getCreatedAtMax())) {
            criteria.andCreatedAtLessThanOrEqualTo(orderReqObj.getCreatedAtMax());
        }

        if (Objects.nonNull(orderReqObj.getDepartureDateMax()) && Objects.nonNull(orderReqObj.getDepartureDateMin())) {
            criteria.andDepartureDateBetween(orderReqObj.getDepartureDateMin(), orderReqObj.getDepartureDateMax());
        } else if (Objects.nonNull(orderReqObj.getDepartureDateMin())) {
            criteria.andDepartureDateGreaterThanOrEqualTo(orderReqObj.getDepartureDateMin());
        } else if (Objects.nonNull(orderReqObj.getDepartureDateMax())) {
            criteria.andDepartureDateLessThanOrEqualTo(orderReqObj.getDepartureDateMax());
        }

        if (Objects.nonNull(orderReqObj.getStatus())) {
            criteria.andStatusEqualTo(orderReqObj.getStatus().byteValue());
        } else {
            criteria.andStatusIn(Arrays.asList(OrderStatusEnum.ROBBING.getValue(), OrderStatusEnum.SUCCESS.getValue(), OrderStatusEnum.CLOSED.getValue()));
        }

        return orderExample;
    }

    private List<RelPassengerOrder> getRelPassengerOrder(OrderReqObj orderReqObj) {
        PassengerReqObj passengerReqObj = new PassengerReqObj();
        passengerReqObj.setIdCard(orderReqObj.getPassengerIdCard());
        passengerReqObj.setName(orderReqObj.getPassengerName());

        List<Passenger> passengers = passengerService.listPassenger(passengerReqObj);

        if (CollectionUtils.isEmpty(passengers)) {
            return new ArrayList<>();
        }

        List<Integer> passengerIdList = passengers.stream().map(Passenger::getId).collect(Collectors.toList());

        RelPassengerOrderReqObj relPassengerOrderReqObj = new RelPassengerOrderReqObj();
        relPassengerOrderReqObj.setPassengerIdList(passengerIdList);

        return relPassengerOrderService.listRelPassengerOrder(relPassengerOrderReqObj);
    }
}
