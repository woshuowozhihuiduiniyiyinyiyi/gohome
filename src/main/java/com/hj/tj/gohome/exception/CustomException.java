package com.hj.tj.gohome.exception;

import com.hj.tj.gohome.enums.ErrorMsgEnum;

/**
 * @author tangj
 * @description
 * @since 2018/10/10 15:29
 */
public class CustomException extends RuntimeException {

    private ErrorMsgEnum errorMsgEnum;
    private static final long serialVersionUID = 1L;

    public CustomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomException(String message) {
        super(message);
    }

    public CustomException(Throwable cause) {
        super(cause);
    }

    public CustomException() {
        super();
    }

    public CustomException(ErrorMsgEnum errorMsgEnum) {
        this.errorMsgEnum = errorMsgEnum;
    }

    public ErrorMsgEnum getErrorMsgEnum() {
        return errorMsgEnum;
    }

    public void setErrorMsgEnum(ErrorMsgEnum errorMsgEnum) {
        this.errorMsgEnum = errorMsgEnum;
    }

}
