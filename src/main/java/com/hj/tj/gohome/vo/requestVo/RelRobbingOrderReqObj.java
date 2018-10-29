package com.hj.tj.gohome.vo.requestVo;

import java.util.List;

public class RelRobbingOrderReqObj {

    private List<Integer> robbingIdList;

    private List<Integer> orderIdList;

    public List<Integer> getRobbingIdList() {
        return robbingIdList;
    }

    public void setRobbingIdList(List<Integer> robbingIdList) {
        this.robbingIdList = robbingIdList;
    }

    public List<Integer> getOrderIdList() {
        return orderIdList;
    }

    public void setOrderIdList(List<Integer> orderIdList) {
        this.orderIdList = orderIdList;
    }
}
