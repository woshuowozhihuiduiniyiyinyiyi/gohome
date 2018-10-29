package com.hj.tj.gohome.vo.requestVo;

import java.util.List;

/**
 * @author tangj
 * @description
 * @since 2018/10/9 15:43
 */
public class OwnerReqObj {

    private List<Integer> idList;

    private String wxAccount;

    private String wxNickName;

    private String phone;

    public List<Integer> getIdList() {
        return idList;
    }

    public void setIdList(List<Integer> idList) {
        this.idList = idList;
    }

    public String getWxAccount() {
        return wxAccount;
    }

    public void setWxAccount(String wxAccount) {
        this.wxAccount = wxAccount;
    }

    public String getWxNickName() {
        return wxNickName;
    }

    public void setWxNickName(String wxNickName) {
        this.wxNickName = wxNickName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
