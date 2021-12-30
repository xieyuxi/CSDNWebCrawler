package com.huawei.main;

import java.util.List;

import com.huawei.entity.Article;
import com.huawei.entity.Blogger;
import com.huawei.entity.Picture;
import com.huawei.util.BlogUtil;
import com.huawei.util.Constants;
import com.huawei.util.JDBCUtil;
import com.huawei.util.StringUtil;

/**
 * 
 * @ClassName:  Main   
 * @Description:TODO(Main)   
 * @author: XIE.YUXI 
 * @date:   2021年12月28日 下午3:41:56   
 *
 */
public class Main {
    
    public static String insertBlogger = "insert into t_blogger(id,article_count,page_count) value (?,?,?)";
    public static String insertArticle = "insert into t_article(b_id,url,create_time,title) value (?,?,?,?)";
    public static String insertPicture = "insert into t_picture(a_id,url) value (?,?)";
    
    public static void main(String[] args) {

        int articleCount = BlogUtil.getArticleCount(Constants.BLOG_HOME);
        int pageCount = BlogUtil.getArticlePageCount(articleCount);
        //博主对象
        Blogger blogger = new Blogger("weixin_44708240",articleCount,pageCount);
        
        JDBCUtil.executeUpdate(insertBlogger, blogger.getId(),blogger.getArticleCount(),blogger.getPageCount());
        //文章列表
        List<Article> articlelist = BlogUtil.getArticleList(1, 3, 
                Constants.BLOG_HOME, StringUtil.subId(Constants.BLOG_HOME));
        for(Article article:articlelist) {
            System.out.println(article);
            int aid = JDBCUtil.executeUpdate(insertArticle, article.getBid(),article.getUrl(),article.getCreateTime(),article.getTitle());
            //图片列表
            List<Picture> pictures = BlogUtil.getArticlePictures(article.getUrl());
            for(Picture picture:pictures) {
                System.out.println(picture);
                JDBCUtil.executeUpdate(insertPicture, aid,picture.getUrl());
            }
        }
        
    }
}
