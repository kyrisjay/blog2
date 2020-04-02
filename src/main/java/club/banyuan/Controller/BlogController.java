package club.banyuan.Controller;

import club.banyuan.bean.Blog;
import club.banyuan.bean.Comment;
import club.banyuan.bean.User;
import club.banyuan.service.BlogService;
import club.banyuan.service.CommentService;
import club.banyuan.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.basic.BasicLookAndFeel;
import java.util.List;
import java.util.Optional;

@Controller
public class BlogController {
    @Autowired
    BlogService blogService;
    @Autowired
    CommentService commentService;
    @Autowired
    UserService userService;

    @GetMapping("blogs/{username}")
    public String showBlogsByUserName(@PathVariable("username") String username,
                                      @RequestParam Optional<Integer> page,
                                      @RequestParam Optional<Integer> size,
                                      Model model) {
        PageHelper.startPage(page.orElse(1), size.orElse(10), "id asc");
        List<Blog> blogs = blogService.showAuthorBlogs(username);
        PageInfo<Blog> pageInfo = new PageInfo<Blog>(blogs);
        User user = blogs.get(0).getAuthor();
        model.addAttribute("blogs", pageInfo);
        model.addAttribute("user", user);
        return "list";
    }

    @GetMapping("/blog/{id}")
    String showBlogById(@PathVariable(value = "id") Integer id, Model model) {
        Blog blog = blogService.getDetailById(id);
//        List<Comment> commentList=commentService.findBlogComments(id);
        model.addAttribute("blog", blog);
//        model.addAttribute("comments",commentList);
        return "item";
    }

    @PutMapping("/blog/{id}/edit")
    String updateBlog(@PathVariable Integer id,
                      Blog blog
                     ) {
        blogService.updateBlog(id, blog);
        return "redirect:/blog/" + id;
    }

    @DeleteMapping("/blog/{id}")
    String deleteBlog(@PathVariable(value = "id") Integer id, Model model,
                      HttpSession session) {
        //delete
        //author == user
        User user = (User) session.getAttribute("USER");
        if (user == null) {
            return "redirect:/";
        }
        Blog blog = blogService.getDetailById(id);
        System.out.println(user.toString());
        System.out.println(blog.getAuthor().getName());
        if (blog.getAuthor().getName().equals(user.getName())) {
            blogService.deleteBlog(id);
        }
        return "redirect:/admin";
    }

    //展示创建博客的页面
    @GetMapping("/blog/create")
    String showCreatePage(HttpServletRequest request, HttpSession session) {

        return "create";
    }


    @PostMapping("/blog/create")
    String createBlog(@RequestParam(value = "title") String title,
                      @RequestParam(value = "content") String content,
                      HttpSession session) {
        User user = (User) session.getAttribute("USER");
        Blog blog = new Blog();
        blog.setAuthor(user);
        blog.setTitle(title);
        blog.setContent(content);
        Integer blogId = blogService.addBlog(blog);
        return "redirect:/blog/" + blogId;
    }

    @GetMapping("/blog/{id}/edit")
    String showEditPage(@PathVariable Integer id, Model model) {
        Blog blog = blogService.getDetailById(id);
        model.addAttribute("blog", blog);
        return "edit";
    }

}
