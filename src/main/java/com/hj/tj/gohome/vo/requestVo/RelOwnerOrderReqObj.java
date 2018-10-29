package com.hj.tj.gohome.vo.requestVo;

import java.util.List;

public class RelOwnerOrderReqObj {

    private List<Integer> orderIdList;

    private List<Integer> ownerIdList;

    public List<Integer> getOrderIdList() {
        return orderIdList;
    }

    public void setOrderIdList(List<Integer> orderIdList) {
        this.orderIdList = orderIdList;
    }

    public List<Integer> getOwnerIdList() {
        return ownerIdList;
    }

    public void setOwnerIdList(List<Integer> ownerIdList) {
        this.ownerIdList = ownerIdList;
    }
}
