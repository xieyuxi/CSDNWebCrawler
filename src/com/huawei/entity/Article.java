package com.huawei.entity;

import java.security.KeyStore.PrivateKeyEntry;

/**
 * 
 * @ClassName:  Article   
 * @Description:TODO(文章类)   
 * @author: XIE.YUXI 
 * @date:   2021年12月28日 下午1:26:51   
 *
 */
public class Article {
    //文章id
    private Integer id;
    //博主id
    private String bid;
    //文章的url
    private String url;
    //创建时间
    private String createTime;
    //文章标题
    private String title;
    
    public Article() {
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Article [id=" + id + ", bid=" + bid + ", url=" + url + ", "
                + "createTime=" + createTime + ", title=" + title
                + "]";
    }

    
    
}
