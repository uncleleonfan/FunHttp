package com.itheima.leon.funhttplib;

public interface NetworkListener<T> {

    void onFailed(String s);

    /**
     * 回调解析后的结果
     * @param result
     */
    void onSuccess(T result);
}
