package com.hj.tj.gohome.enums;

import java.util.Objects;

public enum OrderStatusEnum {

    DELETE((byte) 0, "已删除"), ROBBING((byte) 1, "抢票中"), SUCCESS((byte) 2, "交易成功"), CLOSED((byte) 3, "交易关闭");

    private Byte value;
    private String description;

    private OrderStatusEnum(Byte value, String description) {
        this.description = description;
        this.value = value;
    }

    public Byte getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public static OrderStatusEnum getOrderStatusEnumByValue(Byte value) {
        if (Objects.isNull(value)) {
            return null;
        }

        for (OrderStatusEnum orderStatusEnum : OrderStatusEnum.values()) {
            if (Objects.equals(orderStatusEnum.getValue(), value)) {
                return orderStatusEnum;
            }
        }

        return null;
    }
}
