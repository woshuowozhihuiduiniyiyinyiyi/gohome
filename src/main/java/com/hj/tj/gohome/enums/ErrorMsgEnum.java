package com.hj.tj.gohome.enums;

public enum ErrorMsgEnum {

    // 系统错误
    SYS_ERR(5000, "系统错误", "系统错误"),
    PARAM_ERROR(5001, "参数错误", "参数错误"),

    // 登陆错误
    USER_INFO_ERR(5100, "用户名或者密码错误 ", "用户名或者密码错误"),

    // 订单错误
    SAVE_ORDER_PARAM_VALID(5200, "保存订单参数错误", "保存订单参数错误"),
    OWNER_IS_NULL(5200, "客户信息为空", "客户信息为空"),
    OWNER_WX_ACCOUNT_IS_NULL(5201, "客户微信号为空", "客户微信号为空"),
    OWNER_WX_NICKNAME_IS_NULL(5202, "客户昵称为空", "客户昵称为空"),
    OWNER_PHONE_ERROR(5203, "业主手机号错误", "业主手机号错误"),
    ORIGIN_IS_NULL(5204, "出发地为空", "出发地为空"),
    DESTINATION_IS_NULL(5205, "目的地为空", "目的地为空"),
    DEPARTURE_DATE_IS_NULL(5206, "出发日期为空", "出发日期为空"),
    TRAIN_NUMBER_IS_NULL(5207, "车次信息为空", "车次信息为空"),
    SEAT_IS_NULL(5208, "座位信息为空", "座位信息为空"),
    PASSENGER_IS_NULL(5209, "乘客信息为空", "乘客信息为空"),
    PASSENGER_NAME_IS_NULL(5210, "有乘客姓名为空", "有乘客姓名为空"),
    PASSENGER_ID_CARD_IS_NULL(5211, "有乘客身份证信息为空", "有乘客身份证信息为空"),
    PRICE_IS_NULL(5212, "代抢服务费为空", "代抢服务费为空"),
    PORTAL_USER_IS_NULL(5213, "接单用户为空", "接单用户为空"),
    ORDER_STATUS_IS_NULL(5214, "订单状态为空", "订单状态为空"),
    ROBBING_ID_IS_NULL(5214, "抢票用户为空", "抢票用户为空"),
    ROBBING_PRICE_ERROR(5214, "抢票价格错误", "抢票价格错误"),
    ORDER_NOT_EXIST(5215, "订单不存在", "订单不存在"),

    ;

    /**
     * 错误编码
     */
    private Integer code;

    /**
     * 给我们自己看的错误信息
     */
    private String errorMsg;

    /**
     * 给用户看的信息
     */
    private String userMsg;

    private ErrorMsgEnum(Integer code, String errorMsg, String userMsg) {
        this.code = code;
        this.userMsg = userMsg;
        this.errorMsg = errorMsg;
    }

    public Integer getCode() {
        return code;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public String getUserMsg() {
        return userMsg;
    }
}
