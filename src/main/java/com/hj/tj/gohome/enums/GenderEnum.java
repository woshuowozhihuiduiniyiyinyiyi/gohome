package com.hj.tj.gohome.enums;

public enum GenderEnum {

    MAN((byte) 0, "男"), WOMAN((byte) 1, "女");

    private Byte value;
    private String description;

    private GenderEnum(Byte value, String description) {
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
