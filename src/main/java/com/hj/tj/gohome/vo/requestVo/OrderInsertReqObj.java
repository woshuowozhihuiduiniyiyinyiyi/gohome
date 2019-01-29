package com.hj.tj.gohome.vo.requestVo;

import java.util.Date;
import java.util.List;

public class OrderInsertReqObj {

    /**
     * id
     */
    private Integer id;

    /**
     * 起点
     */
    private String origin;

    /**
     * 目的地
     */
    private String destination;

    /**
     * 用户期望出发日期
     */
    private String expectDate;

    /**
     * 出发日期
     */
    private Date departureDate;

    /**
     * 车次
     */
    private String trainNumber;

    /**
     * 座位
     */
    private String seat;

    /**
     * 旅客信息
     */
    private List<PassengerInsertReqObj> passengerList;

    /**
     * 接章价格
     */
    private Double price;

    /**
     * 接单人员
     */
    private Integer portalUserId;

    /**
     * 抢票用户
     */
    private Integer robbingTicketUserId;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 抢票价格
     */
    private Double robbingPrice;

    /**
     * 订单联系手机号
     */
    private String phone;

    /**
     * 业主id
     */
    private Integer ownerId;

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getExpectDate() {
        return expectDate;
    }

    public void setExpectDate(String expectDate) {
        this.expectDate = expectDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
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

    public Integer getRobbingTicketUserId() {
        return robbingTicketUserId;
    }

    public void setRobbingTicketUserId(Integer robbingTicketUserId) {
        this.robbingTicketUserId = robbingTicketUserId;
    }

    public Double getRobbingPrice() {
        return robbingPrice;
    }

    public void setRobbingPrice(Double robbingPrice) {
        this.robbingPrice = robbingPrice;
    }

    public List<PassengerInsertReqObj> getPassengerList() {
        return passengerList;
    }

    public void setPassengerList(List<PassengerInsertReqObj> passengerList) {
        this.passengerList = passengerList;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getPortalUserId() {
        return portalUserId;
    }

    public void setPortalUserId(Integer portalUserId) {
        this.portalUserId = portalUserId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
