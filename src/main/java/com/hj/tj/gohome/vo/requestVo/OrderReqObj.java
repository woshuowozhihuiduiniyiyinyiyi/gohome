package com.hj.tj.gohome.vo.requestVo;

import java.util.Date;
import java.util.List;

public class OrderReqObj {
    /**
     * id
     */
    private Integer id;
    private List<Integer> idList;

    /**
     * 创建时间
     */
    private Date createdAtMin;
    private Date createdAtMax;

    /**
     * 抢票时间
     */
    private Date departureDateMin;
    private Date departureDateMax;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 业主微信昵称
     */
    private String ownerWxNickName;

    /**
     * 微信号
     */
    private String ownerWxAccount;

    /**
     * 旅客名称
     */
    private String passengerName;

    /**
     * 旅客身份证号
     */
    private String passengerIdCard;

    /**
     * 抢票人员
     */
    private String robbingUserName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Integer> getIdList() {
        return idList;
    }

    public void setIdList(List<Integer> idList) {
        this.idList = idList;
    }

    public String getOwnerWxNickName() {
        return ownerWxNickName;
    }

    public void setOwnerWxNickName(String ownerWxNickName) {
        this.ownerWxNickName = ownerWxNickName;
    }

    public String getOwnerWxAccount() {
        return ownerWxAccount;
    }

    public void setOwnerWxAccount(String ownerWxAccount) {
        this.ownerWxAccount = ownerWxAccount;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getPassengerIdCard() {
        return passengerIdCard;
    }

    public void setPassengerIdCard(String passengerIdCard) {
        this.passengerIdCard = passengerIdCard;
    }

    public String getRobbingUserName() {
        return robbingUserName;
    }

    public void setRobbingUserName(String robbingUserName) {
        this.robbingUserName = robbingUserName;
    }

    public Date getCreatedAtMin() {
        return createdAtMin;
    }

    public void setCreatedAtMin(Date createdAtMin) {
        this.createdAtMin = createdAtMin;
    }

    public Date getCreatedAtMax() {
        return createdAtMax;
    }

    public void setCreatedAtMax(Date createdAtMax) {
        this.createdAtMax = createdAtMax;
    }

    public Date getDepartureDateMin() {
        return departureDateMin;
    }

    public void setDepartureDateMin(Date departureDateMin) {
        this.departureDateMin = departureDateMin;
    }

    public Date getDepartureDateMax() {
        return departureDateMax;
    }

    public void setDepartureDateMax(Date departureDateMax) {
        this.departureDateMax = departureDateMax;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
