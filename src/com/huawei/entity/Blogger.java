package com.huawei.entity;

/**
 * 
 * @ClassName:  Blogger   
 * @Description:TODO(博客类)   
 * @author: XIE.YUXI 
 * @date:   2021年12月28日 下午1:27:41   
 *
 */
public class Blogger {
    //博主的ID
    private String id;
    
    //博主博客文章的数量
    private Integer articleCount; 
    
    //博客文章的总页面数
    private Integer pageCount;
    
    public Blogger(String id, Integer articleCount, Integer pageCount) {
        this.id = id;
        this.articleCount = articleCount;
        this.pageCount = pageCount;
        
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(Integer articleCount) {
        this.articleCount = articleCount;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    @Override
    public String toString() {
        return "Blogger [id=" + id + ", articleCount=" + articleCount + ", pageCount=" + pageCount + "]";
    }
    
    
}
