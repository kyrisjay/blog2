package club.banyuan;



import club.banyuan.bean.Blog;
import club.banyuan.bean.User;
import club.banyuan.dao.BlogDao;
import club.banyuan.dao.UserDao;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;


@SpringBootApplication
@MapperScan("club.banyuan.dao")
public class Blog2Application {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Blog2Application.class, args);

        UserDao userDao=context.getBean(UserDao.class);
        User user1=userDao.selectUserByName("aa");
        System.out.println(user1.toString());


        BlogDao blogDao=context.getBean(BlogDao.class);
        Blog blog = blogDao.getBlogDetail(194);
        System.out.println(blog);

        List<Blog> blogs=blogDao.selectBlogByUserName("aa");
        System.out.println(blogs);

//        Blog blog1=new Blog();
//        blog1.setTitle("4-2");
//        blog1.setContent("4月2日的blog......");
//        blog1.setAuthor(user1);
//        blogDao.insertBlog(blog1);
//        System.out.println("新插入的blogId是:"+blog1.getId());

    }
}
