package com.huawei.main;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.huawei.entity.Article;
import com.huawei.entity.Blogger;
import com.huawei.entity.Picture;
import com.huawei.util.BlogUtil;
import com.huawei.util.Constants;
import com.huawei.util.JDBCUtil;
import com.huawei.util.StringUtil;

/**
 * 
 * @ClassName:  Main2   
 * @Description:TODO(Main2单线程和多线程比较)   
 * @author: XIE.YUXI 
 * @date:   2021年12月28日 下午3:41:56   
 *
 */
public class Main2 {
    
    public static String insertBlogger = "insert into t_blogger(id,article_count,page_count) value (?,?,?)";
    public static String insertArticle = "insert into t_article(b_id,url,create_time,title) value (?,?,?,?)";
    public static String insertPicture = "insert into t_picture(a_id,url) value (?,?)";
    
    public static String delBlogger = "delete from t_blogger";
    public static String delArticle = "delete from t_article";
    public static String delPicture = "delete from t_picture"; 
    
    public static void main(String[] args) {
        //1.删除数据库表数据
        delAll();
        //2.博主信息只用插入一次
        insertBloger();
        //3.单线程插入文章和图片
//        singleTest(1,10,Constants.BLOG_HOME);  //单线程执行时间为：34295毫秒
        //4.多线程插入文章和图片
        threadTest(1,10,Constants.BLOG_HOME);    //多线程用时：7037毫秒
    }

    private static void threadTest(int beginPage, int endPage, String blogHome) {
        ExecutorService executorService = Executors.newFixedThreadPool(endPage-beginPage+1);
        long begin = System.currentTimeMillis();
        for(int i = beginPage; i <= endPage; i++)
        {
            final int a = i;
            executorService.execute(new Runnable() {
                
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "正在被执行");
                    // 插入音频
                    insertArticleAndPicture(a,a,blogHome);
                }
            });
        }
        // 线程池不再接收新任务
        executorService.shutdown();
        
        while(true) {
            if(executorService.isTerminated()) {
                long end = System.currentTimeMillis();
                System.out.println("多线程用时："+(end - begin)+"毫秒");
                break;
            }
        }
        
    }

    private static void singleTest(int beginPage, int endPage, String blogHome) {
        
        long begin = System.currentTimeMillis();
        insertArticleAndPicture(beginPage,endPage,blogHome);
        long end = System.currentTimeMillis();
        
        System.out.println("单线程执行时间为："+(end - begin)+"毫秒");
        
    }

    private static void insertArticleAndPicture(int beginPage, int endPage, String blogHome) {
      //文章列表
        List<Article> articlelist = BlogUtil.getArticleList(1, 3, 
                blogHome, StringUtil.subId(blogHome));
        for(Article article:articlelist) {
            int aid = JDBCUtil.executeUpdate(insertArticle, article.getBid(),article.getUrl(),article.getCreateTime(),article.getTitle());
            //图片列表
            List<Picture> pictures = BlogUtil.getArticlePictures(article.getUrl());
            for(Picture picture:pictures) {
                JDBCUtil.executeUpdate(insertPicture, aid,picture.getUrl());
            }
        }
        
    }

    private static void insertBloger() {
        int articleCount = BlogUtil.getArticleCount(Constants.BLOG_HOME);
        int pageCount = BlogUtil.getArticlePageCount(articleCount);
        //博主对象
        Blogger blogger = new Blogger("weixin_44708240",articleCount,pageCount);
        
        JDBCUtil.executeUpdate(insertBlogger, blogger.getId(),blogger.getArticleCount(),blogger.getPageCount());
        
    }

    private static void delAll() {
        JDBCUtil.executeUpdate(delBlogger);
        JDBCUtil.executeUpdate(delArticle);
        JDBCUtil.executeUpdate(delPicture);
        
    }
}
