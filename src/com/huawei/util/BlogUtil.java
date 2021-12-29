package com.huawei.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.huawei.entity.Article;
import com.huawei.entity.Picture;

/**
 * 
 * @ClassName:  BlogUtil   
 * @Description:TODO(博客数据爬取工具类)   
 * @author: XIE.YUXI 
 * @date:   2021年12月28日 下午1:27:59   
 *
 */
public class BlogUtil {
    /**
     * 
     * @Title: getArticleCount   
     * @Description: TODO(获取博客文章数量)
     * @Author: XIE.YUXI   
     * @param: @param blogHome
     * @param: @return      
     * @return: int      
     * @throws
     */
    public static int getArticleCount(String blogHome) {
        //1.获取文档对象
        Document doc = null;
        try {
            doc = Jsoup.connect(blogHome).get();
        } catch (IOException e) {
            System.out.println("文档获取失败！");
            e.printStackTrace();
        }
        //2.查找包含博客数量的元素
        Element countElement = doc.select("a.div").first();
        System.out.println(countElement.text());
        //3.取出元素包含的文本，这里为博客数量
        String articleCount = countElement.text();
        
        return Integer.parseInt(articleCount);
    }
    
    /**
     * 
     * @Title: getArticlePageCount   
     * @Description: TODO(获得博客文章页数)
     * @Author: XIE.YUXI   
     * @param: @param articleCount
     * @param: @return      
     * @return: int      
     * @throws
     */
    public static int getArticlePageCount(int articleCount) {
        //向上取整，获得页面数量
        int pageCount = (int)Math.ceil(articleCount/Constants.PAGE_SIZE);
        return pageCount;
    }
    
    /**
     * 
     * @Title: getArticleList   
     * @Description: TODO(获取博客文章列表)
     * @Author: XIE.YUXI   
     * @param: @param pageBegin 开始页
     * @param: @param pageEnd   结束页
     * @param: @return      
     * @return: List<Article>      
     * @throws
     */
    public static List<Article> getArticleList(int pageBegin,int pageEnd,String blogHome,String bid){
        //1.创建博客文章列表
        ArrayList<Article> articles = new ArrayList<>(
                (pageEnd - pageBegin +1)*(int)Constants.PAGE_SIZE);
        
        //2.定义变量：博客文章列表的网址
        String articleListUrl = null;
        for(int i = pageBegin;i<=pageEnd;i++) {
            //拼接url
            articleListUrl = Constants.BLOG_HOME + Constants.ARTICLE_LIST_URL + i;

            //1.获取document对象
            Document doc = null;
            try {
                doc = Jsoup.connect(articleListUrl).get();
            } catch (IOException e) {
                System.out.println("获取博客文章列表失败");
                e.printStackTrace();
            }
            
            //2.查找包含博客文章列表的元素
            Element articleList =  doc.select("div.article-list").first();
            
            //3.查找每篇博客的元素
            Elements articleElements = articleList.select("div.article-item-box."
                    + "csdn-tracking-statistics");
            
            for(Element element:articleElements) {
                //获取文章的url
                String href = element.select("h4>a").first().attr("href");
                //获取文章的创建时间
                String date = element.select("span.date").first().text();
                //获取文章的标题
                String title = element.select("h4>a").first().text().substring(3);
                
                Article article = new Article();
                article.setUrl(href);
                article.setCreateTime(date);
                article.setTitle(title);
                article.setBid(bid);
                
                articles.add(article);
            }
        }
        
        return articles;     
    }    
    
    public static List<Picture> getArticlePictures(String articleUrl){
        ArrayList<Picture> pictures = new ArrayList<>();
        
        //1.获取文档对象
        Document doc = null;
        try {
            doc = Jsoup.connect(articleUrl).get();
        } catch (IOException e) {
            System.out.println("图片获取失败");
            e.printStackTrace();
        }
        
        //2.获取文章元素下面的所有图片元素，并进行遍历
        Element article = doc.select("article.baidu_pl").first();
        Elements images = article.select("img");
        for(Element image: images) {
            String picurl = image.attr("src");
            Picture picture = new Picture();
            picture.setUrl(picurl);
            
            pictures.add(picture);
        }
        
        return pictures;   
    }
    
    
    

}
