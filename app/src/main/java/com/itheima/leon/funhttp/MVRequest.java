package com.itheima.leon.funhttp;

import com.itheima.leon.funhttplib.NetworkListener;
import com.itheima.leon.funhttplib.Request;

import java.util.List;

/**
 * Created by Leon on 2017/1/15.
 */

public class MVRequest extends Request<List<HomeListItemBean>>{

    public MVRequest(String url, NetworkListener listener) {
        super(url, listener);
    }

    public static MVRequest getRequest(NetworkListener listener) {
        return new MVRequest(URLProviderUtils.getHomeUrl(0, 10), listener);
    }
}
