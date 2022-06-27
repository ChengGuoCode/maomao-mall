package com.gdng.support.common.exception;

import com.gdng.support.common.dto.res.GlobalResponseEnum;

public class GdngException extends RuntimeException {

    private final int code;

    public GdngException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public GdngException(GlobalResponseEnum e) {
        super(e.getMsg());
        this.code = e.getCode();
    }

    public GdngException(GlobalResponseEnum e, String msg) {
        super(String.format(e.getMsg(), msg));
        this.code = e.getCode();
    }

    public int getCode() {
        return code;
    }
}
