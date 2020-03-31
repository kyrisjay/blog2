package club.banyuan.service;

import club.banyuan.bean.Blog;
import club.banyuan.dao.BlogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogService {
   private BlogDao blogDao;
    @Autowired
    public BlogService(BlogDao blogDao) {
        this.blogDao = blogDao;
    }
   public Blog getDetailById(Integer id){
   return blogDao.selectBlogById(id);
  }

}
