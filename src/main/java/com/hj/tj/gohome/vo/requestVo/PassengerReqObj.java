package com.hj.tj.gohome.vo.requestVo;

import java.util.List;

/**
 * @author tangj
 * @description
 * @since 2018/10/15 9:50
 */
public class PassengerReqObj {

    private String idCard;

    private String name;

    private List<Integer> idList;

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getIdList() {
        return idList;
    }

    public void setIdList(List<Integer> idList) {
        this.idList = idList;
    }
}
