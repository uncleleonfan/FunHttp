package com.itheima.leon.funhttp;

public class HomeListItemBean {
    public static final String TAG = "HomeListItemBean";


    /**
     * type : ACTIVITY
     * id : 0
     * title : EXO合集
     * description :
     * posterPic : http://img0.c.yinyuetai.com/others/mobile_front_page/161219/0/-M-f416bcf2e5d7246b7e4a8a149b80d821_0x0.jpg
     * url : http://activity.shop.yinyuetai.com/index.html#/Activity/25?_k=8xy9rr
     * videoSize : 0
     * hdVideoSize : 0
     * uhdVideoSize : 0
     * status : 0
     * traceUrl : http://www.yinyuetai.com/v?a=102437&un=53a621a9362eb7ed4e46425ac834f4b545fe1eff443acb1e2ba5fdc547da9314f66a78b03b640904a24e6f25376102b0c1dc16842b2b37e0d446aaffccd10a8cf69d2ebc6c2e79bfe31b925f005aee7e12ef159d573c37c97845c34d5e9dc329d8763c9a0e375997
     * clickUrl : http://mapi.yinyuetai.com/statistics/click.json?id=5008
     */

    private String type;
    private int id;
    private String title;
    private String description;
    private String posterPic;
    private String url;
    private int videoSize;
    private int hdVideoSize;
    private int uhdVideoSize;
    private int status;
    private String traceUrl;
    private String clickUrl;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPosterPic() {
        return posterPic;
    }

    public void setPosterPic(String posterPic) {
        this.posterPic = posterPic;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getVideoSize() {
        return videoSize;
    }

    public void setVideoSize(int videoSize) {
        this.videoSize = videoSize;
    }

    public int getHdVideoSize() {
        return hdVideoSize;
    }

    public void setHdVideoSize(int hdVideoSize) {
        this.hdVideoSize = hdVideoSize;
    }

    public int getUhdVideoSize() {
        return uhdVideoSize;
    }

    public void setUhdVideoSize(int uhdVideoSize) {
        this.uhdVideoSize = uhdVideoSize;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTraceUrl() {
        return traceUrl;
    }

    public void setTraceUrl(String traceUrl) {
        this.traceUrl = traceUrl;
    }

    public String getClickUrl() {
        return clickUrl;
    }

    public void setClickUrl(String clickUrl) {
        this.clickUrl = clickUrl;
    }
}
