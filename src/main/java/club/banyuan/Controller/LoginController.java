package club.banyuan.Controller;

import club.banyuan.bean.User;
import club.banyuan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;


@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String showLogin(@RequestParam(required = false) String next,
                            HttpSession session) {
        if (next!=null){
            session.setAttribute("NEXT",next);
        }
        return "login";
    }

    @PostMapping("/login")
    String login(@RequestParam String username,
                 @RequestParam String password,
                 HttpSession session) {
        //check user valid
        User user = userService.findUserByName(username);
        if (user != null && user.getPassword().equals(password)) {
            session.setAttribute("USER", user);
            String next = (String) session.getAttribute("NEXT");

            if (next == null) {
                return "redirect:/admin";
            } else {
                session.removeAttribute("NEXT");
                return "redirect:".concat(next);
            }
        } else {
            return "redirect:/login";
        }
    }
}