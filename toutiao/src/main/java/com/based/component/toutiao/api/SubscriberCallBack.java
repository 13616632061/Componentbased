package com.based.component.toutiao.api;

import android.text.TextUtils;

import com.apkfuns.logutils.LogUtils;
import com.based.component.toutiao.entity.response.ResultResponse;
import com.blankj.utilcode.util.ToastUtils;

import rx.Subscriber;

/**
 * Created by Administrator on 2019/5/13.
 */

public abstract class SubscriberCallBack<T> extends Subscriber<ResultResponse<T>> {

    @Override
    public void onNext(ResultResponse<T> response) {
        boolean isSuccess = (!TextUtils.isEmpty(response.message) && response.message.equals("success"))
                || !TextUtils.isEmpty(response.success) && response.success.equals("true");
        if (isSuccess) {
            onSuccess((T) response.data);
        } else {
            ToastUtils.showShort(response.message);
            onFailure(response);
        }
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        LogUtils.e(e.getLocalizedMessage());
        onError();
    }

    protected abstract void onSuccess(T response);

    protected abstract void onError();

    protected void onFailure(ResultResponse response) {
    }
}
