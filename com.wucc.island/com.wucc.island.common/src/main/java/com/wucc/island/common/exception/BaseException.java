package com.wucc.island.common.exception;


import com.wucc.island.common.result.BaseResultEnumI;

public class BaseException extends RuntimeException {

    private static final long serialVersionUID = -3817769317430507733L;

    private Integer code;

    public BaseException() {
        super();
    }

    public BaseException(String message) {
        super(message);
        this.code = -1;
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

    public BaseException(BaseResultEnumI enums) {
        super(enums.getMsg());
        this.code = enums.getCode();
    }

    public BaseException(Throwable cause) {
        super(cause.getMessage(), cause);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}
