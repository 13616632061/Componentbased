package com.based.component.toutiao.api;


/**
 * Created by Administrator on 2019/5/9.
 * 网络请求的service
 */

public interface ApiService {

    String GET_ARTICLE_LIST = "api/news/feed/v62/?refer=1&count=20&loc_mode=4&device_id=34960436458&iid=13136511752";
    String GET_COMMENT_LIST = "article/v2/tab_comments/";

//    Observable<NewsResponse> get
}
