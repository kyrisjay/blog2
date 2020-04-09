package club.banyuan;


import club.banyuan.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@SpringBootTest
public class Test {

    @Autowired
    JavaMailSenderImpl javaMailSender;

    @Autowired
    MailService mailService;

    @org.junit.jupiter.api.Test
    void sendEmail(){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("jay958718389@qq.com");
        simpleMailMessage.setTo("jay958718389@qq.com");//接收邮件的邮箱
        simpleMailMessage.setSubject("啦啦啦德玛西亚");
        simpleMailMessage.setText("12341234像首歌");

        javaMailSender.send(simpleMailMessage);
    }
}
