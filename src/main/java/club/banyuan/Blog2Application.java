package club.banyuan;



import club.banyuan.bean.Blog;
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
        System.out.println(userDao.selectUserByName("aa").toString());


        BlogDao blogDao=context.getBean(BlogDao.class);
        Blog blog = blogDao.getBlogDetail(194);
        System.out.println(blog);

        List<Blog> blogs=blogDao.selectBlogByUserName("aa");
        System.out.println(blogs);
    }
}
