# 简介 #
FunHttp是对Okttp的Get请求的简单封装，处理了网络回调的线程切换
# 使用姿势 #
## 添加依赖 ##
	//项目build.gradle
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

	//app模块下的build.gradle
	dependencies {
	        compile 'com.github.uncleleonfan:funhttp:1.2.1'
	}

## 创建请求 ##
所有的请求必须继承自Request<T>, 泛型T传入网络结果想要解析成的数据类型

	public class MVRequest extends Request<List<HomeListItemBean>>{
	
	    public MVRequest(String url, NetworkListener listener) {
	        super(url, listener);
	    }
	
	    public static MVRequest getRequest(NetworkListener listener) {
	        return new MVRequest(URLProviderUtils.getHomeUrl(0, 10), listener);
	    }
	}

## 执行请求 ##
   	MVRequest.getRequest(mListNetworkListener).execute();

## 监听网络结果 ##
网络结果在主线程被回调

    private NetworkListener<List<HomeListItemBean>> mListNetworkListener = new NetworkListener<List<HomeListItemBean>>() {
        @Override
        public void onFailed(String s) {

        }

        @Override
        public void onSuccess(List<HomeListItemBean> result) {
            Toast.makeText(MainActivity.this, "onSuccess " + result.size(), Toast.LENGTH_SHORT).show();
        }
    };

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
