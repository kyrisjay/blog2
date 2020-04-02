package club.banyuan.Controller;

import club.banyuan.bean.Blog;
import club.banyuan.bean.User;
import club.banyuan.service.BlogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class AdminController {
    @Autowired
    private BlogService blogService;

    @GetMapping("/admin")
    String showAdminPage(HttpSession session,
                         Model model,
                         Optional<Integer> page,
                         Optional<Integer> size) {
        User user = (User) session.getAttribute("USER");
        PageHelper.startPage(page.orElse(1), size.orElse(10));
        PageInfo<Blog> blogs = new PageInfo<>(blogService.showAuthorBlogs(user.getName()));
        model.addAttribute("blogs",blogs);
        model.addAttribute("user",user);
        return "admin";
    }
}
