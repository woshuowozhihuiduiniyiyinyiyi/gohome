package com.hj.tj.gohome.vo.requestVo;

/**
 * @author tangj
 * @description
 * @since 2018/10/10 16:13
 */
public class PortalUserLoginReqObj {
    /**
     * 用户名
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
