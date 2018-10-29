package com.hj.tj.gohome.vo.responseVO;

import java.util.Date;
import java.util.List;

public class OrderResObj {
    private Integer id;

    /**
     * 微信昵称
     */
    private String ownerWxAccount;
    private String ownerWxNickname;

    /**
     * 业主手机号
     */
    private String ownerPhone;

    /**
     * 出发时间
     */
    private Date departureDate;
    private String departureDateStr;

    /**
     * 出发地
     */
    private String origin;

    /**
     * 目的地
     */
    private String destination;

    /**
     * 车次
     */
    private String trainNumber;

    /**
     * 座位
     */
    private String seat;

    /**
     * 乘客信息
     */
    private List<PassengerResObj> passengerInfo;

    /**
     * 价格
     */
    private Double price;

    /**
     * 收益
     */
    private Double profit;

    /**
     * 抢票人员
     */
    private String robbingTicketUserName;

    /**
     * 抢票状态
     */
    private Integer status;
    private String statusStr;

    /**
     * 接单人员
     */
    private String portalUserName;

    /**
     * 创建时间
     */
    private Date createdAt;
    private String createdAtStr;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }

    public String getOwnerWxAccount() {
        return ownerWxAccount;
    }

    public void setOwnerWxAccount(String ownerWxAccount) {
        this.ownerWxAccount = ownerWxAccount;
    }

    public String getPortalUserName() {
        return portalUserName;
    }

    public void setPortalUserName(String portalUserName) {
        this.portalUserName = portalUserName;
    }

    public String getOwnerWxNickname() {
        return ownerWxNickname;
    }

    public void setOwnerWxNickname(String ownerWxNickname) {
        this.ownerWxNickname = ownerWxNickname;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public String getDepartureDateStr() {
        return departureDateStr;
    }

    public void setDepartureDateStr(String departureDateStr) {
        this.departureDateStr = departureDateStr;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public String getRobbingTicketUserName() {
        return robbingTicketUserName;
    }

    public void setRobbingTicketUserName(String robbingTicketUserName) {
        this.robbingTicketUserName = robbingTicketUserName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedAtStr() {
        return createdAtStr;
    }

    public void setCreatedAtStr(String createdAtStr) {
        this.createdAtStr = createdAtStr;
    }

    public List<PassengerResObj> getPassengerInfo() {
        return passengerInfo;
    }

    public void setPassengerInfo(List<PassengerResObj> passengerInfo) {
        this.passengerInfo = passengerInfo;
    }
}
