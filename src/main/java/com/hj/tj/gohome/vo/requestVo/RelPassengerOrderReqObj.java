package com.hj.tj.gohome.vo.requestVo;

import java.util.List;

/**
 * @author tangj
 * @description
 * @since 2018/10/15 9:43
 */
public class RelPassengerOrderReqObj {

    private List<Integer> passengerIdList;

    private List<Integer> orderIdList;

    public List<Integer> getPassengerIdList() {
        return passengerIdList;
    }

    public void setPassengerIdList(List<Integer> passengerIdList) {
        this.passengerIdList = passengerIdList;
    }

    public List<Integer> getOrderIdList() {
        return orderIdList;
    }

    public void setOrderIdList(List<Integer> orderIdList) {
        this.orderIdList = orderIdList;
    }
}
