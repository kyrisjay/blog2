package club.banyuan.Controller;

import club.banyuan.bean.User;
import club.banyuan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class FileController {

    String UPLOAD_DIR = "/Users/edz/upload";

    private final ResourceLoader resourceLoader;
    private UserService userService;

    public FileController(@Autowired ResourceLoader resourceLoader,
                          @Autowired UserService userService) {
        this.resourceLoader = resourceLoader;
        this.userService=userService;
    }
    //show uploadPage
    @GetMapping("/upload")
    String showUploadPage() {
        return "upload";
    }

    //upload file
    @PostMapping("/upload")
    String upload(@RequestParam(value = "file") MultipartFile file,
                  @RequestParam(value = "name") String fileName,
                  Model model,
                  HttpSession session
    ) throws IOException {
        // 把file存放为本地的一个文件
        Path avatarPath = Paths.get(UPLOAD_DIR, fileName);
        Files.copy(file.getInputStream(), avatarPath);
        // 将文件的路径保存到db里面
        User user = (User)session.getAttribute("USER");
        user.setAvatar(avatarPath.toString());
        userService.updateAvatarById(user.getId(), avatarPath.toString());
        model.addAttribute("user", user);
        return "/admin";
    }

    //get img
    @ResponseBody
    @GetMapping("/dyImg/{filename}")
    ResponseEntity<?> getImgData(@PathVariable(value = "filename") String name) {
        //读取uploa目录下的文件内容，并返回
        return ResponseEntity.ok(resourceLoader.getResource("file:" + Paths.get(UPLOAD_DIR, name)));
    }

    @GetMapping("/showImg")
    String showImg(@RequestParam(value = "name") String name, Model model) {
        model.addAttribute("filename", name);
        return "picView";
    }

    @GetMapping("/avatar/{username}")
    @ResponseBody
    ResponseEntity<?> getAvatar(@PathVariable(value = "username") String name){
        //获取头像路径
        User user=userService.findUserByName(name);
        if (user.getAvatar()==null){
            return null;
        }
        //返回头像图片内容
        return ResponseEntity.ok(
                resourceLoader.getResource("file:"+ user.getAvatar() ));
    }

}
