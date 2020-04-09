package club.banyuan.Controller;

import club.banyuan.bean.Blog;
import club.banyuan.bean.User;
import club.banyuan.service.BlogService;
import club.banyuan.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private BlogService blogService;
    @Autowired
    UserService userService;

    @GetMapping("")
    String showAdminPage(Principal principal, Model model) {
        String userName = principal.getName();
        User user = userService.findUserByName(userName);
        model.addAttribute("user", user);
        return "admin";
    }


    @GetMapping("/blogs")
    String showAdminBlogPage(@RequestParam Optional<Integer> page,
                         @RequestParam Optional<Integer> size,
                         Principal principal,
                         Model model) {
        String username = principal.getName();
        model.addAttribute("username", username);
        PageHelper.startPage(page.orElse(1), size.orElse(10));
        model.addAttribute("blogs",
                new PageInfo<Blog>(blogService.showAuthorBlogs(username)));
        return "admin-blogs";
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
