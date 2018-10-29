package com.hj.tj.gohome.vo.responseVO;

/**
 * @author tangj
 * @description
 * @since 2018/10/11 15:17
 */
public class LoginResObj {

    private String sid;
    private Integer userId;
    private String userName;
    private String phone;
    private String gender;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
