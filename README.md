# OKHttp的Get请求的封装 #

## Request ##

### 构造方法 ###
    public Request(String url, NetworkListener<T> listener) {
        mUrl = url;
        mNetworkListener = listener;
        mGson = new Gson();
    }
### 执行网络请求 ###
    public void execute() {
        NetworkManager.getInstance().sendRequest(this);
    }
### 解析网络响应 ###
    public T parseNetworkResponse(String result) {
        Class c = this.getClass();
        ParameterizedType parameterizedType = (ParameterizedType) c.getGenericSuperclass();
        Type actualType = parameterizedType.getActualTypeArguments()[0];
        return mGson.fromJson(result, actualType);
    }

## NetworkListener ##
	public interface NetworkListener<T> {
	
		//请求失败的回调
	    void onError(String errorMsg);

		//请求失败的回调	
	    void onSuccess(T result);
	}
## NetworkManager ##
NetworkManager维护一个OkhttpClient的对象来执行网络请求。当收到网络结果后，通过绑定主线程的Handler回调到主线程。

    public void sendRequest(final Request funRequest) {
        final okhttp3.Request request = new okhttp3.Request.Builder().url(funRequest.getUrl()).get().build();
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
                //解析结果在在子线程做
                final Object o = funRequest.parseNetworkResponse(response.body().string());
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
