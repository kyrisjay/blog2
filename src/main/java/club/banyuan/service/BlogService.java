package club.banyuan.service;

import club.banyuan.bean.Blog;
import club.banyuan.dao.BlogDao;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {
    private BlogDao blogDao;

    @Autowired
    public BlogService(BlogDao blogDao) {
        this.blogDao = blogDao;
    }

    public Blog getDetailById(Integer id) {
        return blogDao.getBlogDetail(id);
    }

    public List<Blog> showAuthorBlogs(String name) {
        return blogDao.selectBlogByUserName(name);
    }

    public Integer addBlog(Blog blog) {
        blogDao.insertBlog(blog);
        return blog.getId();
    }

    public void deleteBlog(Integer id){
        blogDao.deleteBlogById(id);
    }
    public void updateBlog(Integer id,Blog blog){
        blogDao.update(id,blog);
    }

    public PageInfo<Blog> showBlogs(Integer page,Integer size){
        PageHelper.startPage(page,size);
        return new PageInfo<Blog>(blogDao.getAllBlogs());}

}
