package com.xes.yuedupractice.utils;

/**
 * <pre>
 *     author : gooch
 *     e-mail : zhaoguangchao@100tal.com
 *     time   : 2017/06/28
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class RxBusBaseMessage {
    private int code;
    private Object object;

    public RxBusBaseMessage(int code, Object object) {
        this.code = code;
        this.object = object;
    }

    public RxBusBaseMessage() {
    }

    public int getCode() {
        return code;
    }

    public Object getObject() {
        return object;
    }
}
