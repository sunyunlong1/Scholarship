package com.scholarship.demo.model;

/**
 * 统一返回json
 */
public class Result {
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 返回信息提示
     */
    private String message;
    /**
     * 返回的数据
     */
    private Object date;

    public Result(Integer code, String message, Object date) {
        this.code = code;
        this.message = message;
        this.date = date;
    }

    public Result(){

    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", date=" + date +
                '}';
    }
}
