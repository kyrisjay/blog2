package club.banyuan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MailService {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    public void testSendMail() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("张三");
        simpleMailMessage.setTo("jay958718389@qq.com");//接收邮件的邮箱
        simpleMailMessage.setSubject("啦啦啦德玛西亚");
        simpleMailMessage.setText("12341234像首歌");

        mailSender.send(simpleMailMessage);
    }

    public void sendActiveMessage(String username){
        // 发送邮件验证信息到用户的邮箱
        // 1. 验证信息是啥
        String message = "你已经注册半圆网络的blog，请点击如下链接进行激活";
        String url = "http://localhost:8080/active?token=";
        // token哪里来？
        // a) 生产token
        long token = (new Date()).getTime();
        // b) 将token和用户名保存在redis上
        redisService.storeToken("" + token, username, 15 * 60);
        url += token;
        message += url;

        // 2. 用户的邮箱在哪里
        // userService->email
        String email = userService.findUserByName(username).getEmail();

        //构造mail
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("jay958718389@qq.com");
        simpleMailMessage.setTo(email);//接收邮件的邮箱
        simpleMailMessage.setSubject("半圆网络激活邮件");
        simpleMailMessage.setText(message);

        mailSender.send(simpleMailMessage);
    }
}
