package club.banyuan.Controller;

import club.banyuan.service.MailService;
import club.banyuan.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class MailController {
    @Autowired
    private MailService mailService;

    @Autowired
    private RedisService redisService;
    //1. send mail
    @ResponseBody
    @PostMapping("/mail/send-active-mail")
    // 用户已登录
    String sendActiveMail(Principal principal) {
        String username = principal.getName();
        mailService.sendActiveMessage(username);
        return "邮件已发送";
    }
    //2. active

    @ResponseBody
    @GetMapping("/active")
    String activeAccount(@RequestParam(value = "token") String token) {
        // 使用token查找redis上的用户名
        String username = redisService.getUsernameByToken(token);
        if (username == null) {
            return "用户不存在";
        }
        else {
            // 刷新用户的状态 未激活->已激活
            return "激活成功";
        }
    }

}
