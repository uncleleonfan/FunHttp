package com.itheima.leon.funhttplib;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * NetworkManager 持有一个OkHttpClient对象来发送网络请求，处理网络结果的线程的切换
 */
public class NetworkManager {

    public static final String TAG = "NetworkManager";

    private static NetworkManager sNetworkManager;

    private Handler mHandler = new Handler(Looper.getMainLooper());

    private NetworkManager() {
        mOkHttpClient = new OkHttpClient();
    }

    private OkHttpClient mOkHttpClient;

    public static NetworkManager getInstance() {
        if (sNetworkManager == null) {
            synchronized (NetworkManager.class) {
                if (sNetworkManager == null) {
                    sNetworkManager = new NetworkManager();
                }
            }
        }
        return sNetworkManager;
    }

    public void sendRequest(final Request funRequest) {
        final okhttp3.Request request = new okhttp3.Request.Builder()
                .url(funRequest.getUrl())
                .addHeader("Connection", "close")
                .get().build();

        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        funRequest.getNetworkListener().onFailed(e.getLocalizedMessage());
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResponseBody body = response.body();
                //解析结果在在子线程做
                final Object o = funRequest.parseNetworkResponse(body.string());
                body.close();
                //回调网络请求成功，传入解析后的结果
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        funRequest.getNetworkListener().onSuccess(o);
                    }
                });
            }
        });
    }
}
