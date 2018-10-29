package com.hj.tj.gohome.vo.requestVo;

import java.util.List;

public class RobbingTicketUserReqObj {
    private String name;

    private List<Integer> robbingIdList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getRobbingIdList() {
        return robbingIdList;
    }

    public void setRobbingIdList(List<Integer> robbingIdList) {
        this.robbingIdList = robbingIdList;
    }
}
