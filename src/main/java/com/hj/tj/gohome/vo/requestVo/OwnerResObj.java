package com.hj.tj.gohome.vo.requestVo;

/**
 * @author tangj
 * @description
 * @since 2018/11/2 9:22
 */
public class OwnerResObj {

    private Integer id;

    /**
     * 微信号
     */
    private String wxAccount;

    /**
     * 微信昵称
     */
    private String wxNickname;

    /**
     * 业主手机号
     */
    private String phone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWxAccount() {
        return wxAccount;
    }

    public void setWxAccount(String wxAccount) {
        this.wxAccount = wxAccount;
    }

    public String getWxNickname() {
        return wxNickname;
    }

    public void setWxNickname(String wxNickname) {
        this.wxNickname = wxNickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
