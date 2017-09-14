package com.xes.yuedupractice.data;

import java.io.Serializable;

/**
 * <pre>
 *     author : gooch
 *     e-mail : zhaoguangchao@100tal.com
 *     time   : 2017/09/11
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class BaseResult<T> implements Serializable {
    private static final long serialVersionUID = -7641392641405626610L;
    private String code;
    private String msg;
    private T body;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return body;
    }

    public void setData(T data) {
        this.body = data;
    }

    @Override
    public String toString() {
        return "BaseResult{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + body +
                '}';
    }
}
