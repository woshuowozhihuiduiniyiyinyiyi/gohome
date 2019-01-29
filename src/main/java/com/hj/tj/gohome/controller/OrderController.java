package com.hj.tj.gohome.controller;

import com.hj.tj.gohome.enums.ErrorMsgEnum;
import com.hj.tj.gohome.exception.CustomException;
import com.hj.tj.gohome.json.ApiRequest;
import com.hj.tj.gohome.json.JsonResponse;
import com.hj.tj.gohome.json.Page;
import com.hj.tj.gohome.json.Pagination;
import com.hj.tj.gohome.service.OrderService;
import com.hj.tj.gohome.utils.JSONUtils;
import com.hj.tj.gohome.utils.JwtUtil;
import com.hj.tj.gohome.utils.StringUtil;
import com.hj.tj.gohome.vo.requestVo.OrderInsertReqObj;
import com.hj.tj.gohome.vo.requestVo.OrderReqObj;
import com.hj.tj.gohome.vo.requestVo.OwnerInsertReqObj;
import com.hj.tj.gohome.vo.requestVo.PassengerInsertReqObj;
import com.hj.tj.gohome.vo.responseVO.OrderResObj;
import com.hj.tj.gohome.vo.responseVO.OrderStatisticDataResObj;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@RestController
public class OrderController {

    @Resource
    private OrderService orderService;

    @RequestMapping("/order/list")
    public JsonResponse listOrder(@RequestBody ApiRequest apiRequest) {
        OrderReqObj orderReqObj = JSONUtils.toBean(apiRequest.getData(), OrderReqObj.class);

        Page reqPage = Objects.isNull(apiRequest.getPage()) ? new Page() : apiRequest.getPage();

        List<OrderResObj> orderResObjs = orderService.listOrder(orderReqObj, reqPage);

        Integer count = orderService.countByReqObj(orderReqObj);

        Pagination pagination = Pagination.newInstance(reqPage, count, orderResObjs.size());

        return JsonResponse.newOk(pagination, orderResObjs);
    }

    @RequestMapping("/order/save")
    public JsonResponse saveOrder(@RequestBody ApiRequest apiRequest) throws Exception {
        OrderInsertReqObj orderInsertReqObj = JSONUtils.toBean(apiRequest.getData(), OrderInsertReqObj.class);

        Integer userId = JwtUtil.getUserId(apiRequest.getSid());
        orderInsertReqObj.setPortalUserId(userId);

        saveOrderParamValidation(orderInsertReqObj);

        Integer result = orderService.saveOrder(orderInsertReqObj);

        if (result.compareTo(0) > 0) {
            return JsonResponse.newOk();
        }

        return JsonResponse.newError();
    }

    @RequestMapping("/order/detail")
    public JsonResponse getOrderDetail(@RequestBody ApiRequest apiRequest) {
        Integer id = apiRequest.getParamAsInteger("id");

        if (Objects.isNull(id) || id.compareTo(0) < 0) {
            throw new CustomException(ErrorMsgEnum.PARAM_ERROR);
        }

        OrderResObj orderDetail = orderService.getOrderDetail(id);

        return JsonResponse.newOk(orderDetail);
    }

    @RequestMapping("/order/delete")
    public JsonResponse deleteOrder(@RequestBody ApiRequest apiRequest) {
        List<Integer> orderIdList = JSONUtils.toList(apiRequest.getParam("orderIdList"), Integer.class);

        if (CollectionUtils.isEmpty(orderIdList)) {
            throw new CustomException(ErrorMsgEnum.PARAM_ERROR);
        }

        orderService.deleteOrder(orderIdList);

        return JsonResponse.newOk();
    }

    @RequestMapping("/order/statistic/data")
    public JsonResponse statisticData(@RequestBody ApiRequest apiRequest) {
        OrderStatisticDataResObj orderStatisticDataResObj = orderService.statisticData();

        return JsonResponse.newOk(orderStatisticDataResObj);
    }


    private void saveOrderParamValidation(OrderInsertReqObj orderInsertReqObj) {
        if (Objects.isNull(orderInsertReqObj)) {
            throw new CustomException(ErrorMsgEnum.SAVE_ORDER_PARAM_VALID);
        }

        if (Objects.isNull(orderInsertReqObj.getId())) {
            throw new CustomException(ErrorMsgEnum.ORDER_ID_IS_NULL);
        }

        if (Objects.isNull(orderInsertReqObj.getOwnerId())) {
            throw new CustomException(ErrorMsgEnum.OWNER_ID_IS_NULL);
        }

        if (StringUtil.isBlank(orderInsertReqObj.getPhone())) {
            throw new CustomException(ErrorMsgEnum.OWNER_PHONE_ERROR);
        }

        if (StringUtil.isBlank(orderInsertReqObj.getOrigin())) {
            throw new CustomException(ErrorMsgEnum.ORIGIN_IS_NULL);
        }

        if (StringUtil.isBlank(orderInsertReqObj.getDestination())) {
            throw new CustomException(ErrorMsgEnum.DESTINATION_IS_NULL);
        }

        if (StringUtil.isBlank(orderInsertReqObj.getTrainNumber())) {
            throw new CustomException(ErrorMsgEnum.TRAIN_NUMBER_IS_NULL);
        }

        if (StringUtil.isBlank(orderInsertReqObj.getSeat())) {
            throw new CustomException(ErrorMsgEnum.SEAT_IS_NULL);
        }

        List<PassengerInsertReqObj> passengerList = orderInsertReqObj.getPassengerList();
        if (CollectionUtils.isEmpty(passengerList)) {
            throw new CustomException(ErrorMsgEnum.PASSENGER_IS_NULL);
        }
        for (PassengerInsertReqObj passengerInsertReqObj : passengerList) {
            if (StringUtil.isBlank(passengerInsertReqObj.getName())) {
                throw new CustomException(ErrorMsgEnum.PASSENGER_NAME_IS_NULL);
            }
            if (StringUtil.isBlank(passengerInsertReqObj.getIdCard())) {
                throw new CustomException(ErrorMsgEnum.PASSENGER_ID_CARD_IS_NULL);
            }
        }

        if (Objects.isNull(orderInsertReqObj.getPrice()) || orderInsertReqObj.getPrice().compareTo(0.0) <= 0) {
            throw new CustomException(ErrorMsgEnum.PRICE_IS_NULL);
        }

        if (Objects.isNull(orderInsertReqObj.getPortalUserId())) {
            throw new CustomException(ErrorMsgEnum.PORTAL_USER_IS_NULL);
        }

    }
}
