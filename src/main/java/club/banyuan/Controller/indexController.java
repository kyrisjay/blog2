package club.banyuan.Controller;

import club.banyuan.bean.Blog;
import club.banyuan.service.BlogService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;


@Controller
public class indexController {
    @Autowired
    BlogService blogService;

    @GetMapping({"/","/index"})
    String showHomepage(@RequestParam Optional<Integer> page,
                        @RequestParam Optional<Integer> size,
                        Model model){
        PageInfo<Blog> blogs=blogService.showBlogs(page.orElse(1),size.orElse(10));
        model.addAttribute("blogs",blogs);

        return "index";
    }
}


