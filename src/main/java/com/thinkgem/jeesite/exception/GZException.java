package com.thinkgem.jeesite.exception;

/**
 * @author duanshao
 */
public class GZException extends RuntimeException {
    private static final long serialVersionUID = -2742295823094596726L;

    private int code;
    private String msg;

    public GZException() {
        super();
    }

    public GZException(int code, String msg) {
        this(code, msg, null);
    }

    public GZException(int code, String msg, Throwable e) {
        super("code: " + code + " msg: " + msg, e);
        this.code = code;
        this.msg = msg;
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

    @Override
    public String toString() {
        return "GZException [code=" + code + ", msg=" + msg + "]";
    }
}
