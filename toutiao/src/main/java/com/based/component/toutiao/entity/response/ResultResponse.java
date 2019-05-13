package com.based.component.toutiao.entity.response;

/**
 * Created by Administrator on 2019/5/13.
 */

public class ResultResponse<T> {
    public String has_more;
    public String message;
    public String success;
    public T data;

    public ResultResponse(String more, String _message, T result) {
        has_more = more;
        message = _message;
        data = result;
    }
}
