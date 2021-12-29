package com.huawei.entity;

/**
 * 
* @ClassName: Picture
* @Description: 图片类
* @author XIE.YUXI
* @date 2021年12月28日 下午12:36:29
*
 */
public class Picture {
    //图片id
    private Integer id;
    //文章的id
    private String aid;
    //图片的url
    private String url;
    
    public Picture() {
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Picture [id=" + id + ", aid=" + aid + ", url=" + url + "]";
    }
    
    
    
    
}
