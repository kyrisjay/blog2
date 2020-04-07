package club.banyuan.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.annotation.Resource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Controller
public class FileController {

    String UPLOAD_DIR = "/Users/edz/upload";

    private final ResourceLoader resourceLoader;

    public FileController(@Autowired ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
    //show uploadPage
    @GetMapping("/upload")
    String showUploadPage() {
        return "upload";
    }

    //upload file
    @PostMapping("/upload")
    String upload(@RequestParam(value = "file") MultipartFile file,
                  @RequestParam(value = "name") String fileName) throws IOException {
        //把file存储为本地的一个文件
        Files.copy(file.getInputStream(), Paths.get(UPLOAD_DIR, fileName));
        return "/upload";
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
}
