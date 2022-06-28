package com.gdng.support.common.dto.res;

public class ResDTO<T> {

    private int code;
    private String msg;
    private T data;

    public ResDTO() {
    }

    private ResDTO(GlobalResponseEnum resEnum) {
        this.code = resEnum.getCode();
        this.msg = resEnum.getMsg();
    }

    private ResDTO(GlobalResponseEnum resEnum, T data) {
        this.code = resEnum.getCode();
        this.msg = resEnum.getMsg();
        this.data = data;
    }

    public boolean isSuccess() {
        return this.code == GlobalResponseEnum.SUCCESS.getCode();
    }

    public static ResDTO buildSuccessResult() {
        return new ResDTO<>(GlobalResponseEnum.SUCCESS);
    }

    public static ResDTO buildSuccessResult(Object data) {
        return new ResDTO<>(GlobalResponseEnum.SUCCESS, data);
    }

    public static ResDTO buildFailResult() {
        return new ResDTO<>(GlobalResponseEnum.SYSTEM_ERR);
    }

    public static ResDTO buildBusyResult() {
        return new ResDTO<>(GlobalResponseEnum.SYSTEM_BUSY);
    }

    public static ResDTO buildFailResult(GlobalResponseEnum anEnum) {
        return new ResDTO<>(anEnum);
    }

    public static ResDTO buildFailResult(GlobalResponseEnum anEnum, String errMsg) {
        ResDTO<Object> resDTO = new ResDTO<>();
        resDTO.setCode(anEnum.getCode());
        resDTO.setMsg(String.format(anEnum.getMsg(), errMsg));
        return resDTO;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
