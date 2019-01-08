package com.hj.tj.gohome.entity;

import java.util.Date;

public class Order {
    private Integer id;

    private String origin;

    private String destination;

    private String expectDate;

    private Date departureDate;

    private String trainNumber;

    private String seat;

    private Integer price;

    private Integer profit;

    private Date completeDate;

    private Integer ownerId;

    private Integer portalUserId;

    private Integer robbingTicketUserId;

    private String phone;

    private String canBuyTicketLater;

    private Byte status;

    private String creator;

    private Date createdAt;

    private String updater;

    private Date updatedAt;

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
        this.origin = origin == null ? null : origin.trim();
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination == null ? null : destination.trim();
    }

    public String getExpectDate() {
        return expectDate;
    }

    public void setExpectDate(String expectDate) {
        this.expectDate = expectDate == null ? null : expectDate.trim();
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
        this.trainNumber = trainNumber == null ? null : trainNumber.trim();
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat == null ? null : seat.trim();
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getProfit() {
        return profit;
    }

    public void setProfit(Integer profit) {
        this.profit = profit;
    }

    public Date getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(Date completeDate) {
        this.completeDate = completeDate;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getPortalUserId() {
        return portalUserId;
    }

    public void setPortalUserId(Integer portalUserId) {
        this.portalUserId = portalUserId;
    }

    public Integer getRobbingTicketUserId() {
        return robbingTicketUserId;
    }

    public void setRobbingTicketUserId(Integer robbingTicketUserId) {
        this.robbingTicketUserId = robbingTicketUserId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getCanBuyTicketLater() {
        return canBuyTicketLater;
    }

    public void setCanBuyTicketLater(String canBuyTicketLater) {
        this.canBuyTicketLater = canBuyTicketLater == null ? null : canBuyTicketLater.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater == null ? null : updater.trim();
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}