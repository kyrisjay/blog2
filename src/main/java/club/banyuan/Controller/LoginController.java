package club.banyuan.Controller;

import club.banyuan.bean.User;
import club.banyuan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

//    @PostMapping("/login")
//    String login(@RequestParam String username,
//                 @RequestParam String password,
//                 HttpSession session) {
//        //check user valid
//        User user = userService.findUserByName(username);
//        if (user != null && user.getPassword().equals(password)) {
//            session.setAttribute("USER", user);
//            String next = (String) session.getAttribute("NEXT");
//
//            if (next == null) {
//                return "redirect:/admin";
//            } else {
//                session.removeAttribute("NEXT");
//                return "redirect:".concat(next);
//            }
//        } else {
//            return "redirect:/login";
//        }
//    }

    @PostMapping("/login/change-password")
    String changePassword(HttpSession session,
                          @RequestParam(value = "oldPasswd") String oldPassword,
                          @RequestParam(value = "newPasswd") String newPassword,
                          Model model){
        String username=((User)session.getAttribute("USER")).getName();
        User user=userService.findUserByName(username);
        String oldInDB=user.getPassword();
        if (oldInDB.equals(oldPassword)){
            //oldpassword 正确
            user.setPassword(newPassword);
            userService.updatePasswd(newPassword,user.getId());
            model.addAttribute("message", "修改密码成功");
        }else {
            model.addAttribute("message","原始密码不正确");
        }
        model.addAttribute("user",user);
        return "/admin";
    }

    @PostMapping("/logout")
    String logout(HttpSession session){
        session.removeAttribute("USER");
        return "redirect:/";
    }
}