package com.based.component.toutiao.api;

/**
 * Created by Administrator on 2019/5/13.
 */

public class ApiConstant {
    /**接口根地址*/
    public static final String BASE_SERVER_URL = "https://is.snssdk.com/";
    public static final String HOST_VIDEO = "http://i.snssdk.com";
    public static final String URL_VIDEO="/video/urls/v/1/toutiao/mp4/%s?r=%s";

    //接口api
    public static final String GET_ARTICLE_LIST = "api/news/feed/v62/?refer=1&count=20&loc_mode=4&device_id=34960436458&iid=13136511752";
    public static final String GET_COMMENT_LIST = "article/v2/tab_comments/";
}
