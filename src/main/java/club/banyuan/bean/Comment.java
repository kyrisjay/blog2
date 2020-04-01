package club.banyuan.bean;

import lombok.Data;

import java.util.Date;
@Data
public class Comment {
    private Integer id;
    private String content;
    //private Integer userId;
    private User commenter;
    private Date createdTime;
    private Integer blogId;


}
