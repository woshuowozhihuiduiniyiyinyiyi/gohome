package com.hj.tj.gohome.enums;

public enum BaseStatusEnum {

    DELETE((byte) 0, "已删除"), UN_DELETE((byte) 1, "未删除");

    private Byte value;
    private String description;

    private BaseStatusEnum(Byte value, String description) {
        this.description = description;
        this.value = value;
    }

    public Byte getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }
}
