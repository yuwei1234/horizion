package com.msb.core.domain;

/**
 * 通用返回实体类
 */
public class AjaxResult {

    private int code;

    private String msg;

    private Object data;

    public AjaxResult(){}

    public static AjaxResult success(int code, String msg){
        AjaxResult ajaxResult= new AjaxResult();
        ajaxResult.setCode(code);
        ajaxResult.setMsg(msg);
        return ajaxResult;
    }

    public static AjaxResult success(int code, String msg, Object data){
        AjaxResult ajaxResult= new AjaxResult();
        ajaxResult.setCode(code);
        ajaxResult.setMsg(msg);
        ajaxResult.setData(data);
        return ajaxResult;
    }

    public static AjaxResult error(int code, String msg){
        AjaxResult ajaxResult= new AjaxResult();
        ajaxResult.setCode(code);
        ajaxResult.setMsg(msg);
        return ajaxResult;
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
}
