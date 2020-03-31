package club.banyuan.Controller;

import club.banyuan.bean.Blog;
import club.banyuan.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BlogController {
    @Autowired
    BlogService blogService;
    @GetMapping("blogs/{username}")
    @ResponseBody
    public String showBlogsByUserName(@PathVariable("username") String username){

        return "You are request"+username+"blogs";
    }

    @GetMapping("/blog/{id}")
    String showBlogById(@PathVariable(value = "id") Integer id, Model model){
        Blog blog=blogService.getDetailById(id);
        model.addAttribute("blog",blog);
        return "item";
    }

}
