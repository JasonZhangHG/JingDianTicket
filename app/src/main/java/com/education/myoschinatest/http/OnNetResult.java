package com.education.myoschinatest.http;

/**
 *  Created by Json on 2017/5/16.
 */
//网络操作回调
public interface OnNetResult<T> {
    void onNetSuccess(T data);
    void onNetFail(String errorMsg);
}
