package com.thinkgem.jeesite.domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.thinkgem.jeesite.utils.JsonHelper;

/**
 * 
 * controller返回对象，mvc容器会自动转为json对象
 * 
 * @author duanshao
 * 
 */
@JsonInclude(Include.NON_NULL)
public class Resp implements Serializable {

    private static final long serialVersionUID = -8845772931610570022L;
    private int code;
    private String msg;
    private Object data;

    public Resp(int code, String msg) {
        this(code, msg, null);
    }

    public Resp(int code, String msg, Object data) {
        super();
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Resp() {
        super();
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static void main(String[] args) {
        Resp resp = new Resp(1, "aaa");
        System.out.println(JsonHelper.toJson(resp));
    }
}
