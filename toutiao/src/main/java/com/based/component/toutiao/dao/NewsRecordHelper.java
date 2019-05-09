package com.based.component.toutiao.dao;

import com.based.component.toutiao.entity.NewsRecord;

import org.litepal.LitePal;

import java.util.List;

/**
 * Created by Administrator on 2019/5/9.
 * 用于获取新闻记录
 */

public class NewsRecordHelper {

    /**
     * 获取数据库保存的某个频道的最后一条记录
     *
     * @param channelCode 频道
     * @return
     */
    public static NewsRecord getLastNewsRecord(String channelCode) {
        return LitePal.where("channelCode=?", channelCode).findLast(NewsRecord.class);
    }

    /**
     * 获取某个频道上一组新闻记录
     *
     * @param channelCode
     * @param page
     * @return
     */
    public static NewsRecord getPreNewsRecord(String channelCode, int page) {
        List<NewsRecord> newsRecordList = selectNewsRecords(channelCode, page - 1);
        if (newsRecordList == null) {
            return null;
        }
        return newsRecordList.get(0);
    }

    /**
     * 根据频道码和页码查询新闻记录
     *
     * @param channelCode
     * @param page
     * @return
     */
    private static List<NewsRecord> selectNewsRecords(String channelCode, int page) {
        return LitePal.where("channelCode = ? and page = ? ", channelCode, String.valueOf(page)).find(NewsRecord.class);
    }

    public static void save(String channelCode, String json) {
        int page = 1;
        NewsRecord lastNewsRecord = getLastNewsRecord(channelCode);
        if (lastNewsRecord != null) {
            //如果有记录
            page = lastNewsRecord.getPage() + 1;
        }
        //保存新的记录
        NewsRecord newsRecord = new NewsRecord(channelCode, page, json, System.currentTimeMillis());
        newsRecord.saveOrUpdate("channelCode = ? and page = ?", channelCode, String.valueOf(page));
    }
}
