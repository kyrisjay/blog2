package club.banyuan.dao;

import club.banyuan.bean.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    User selectUserByName(String name);
    void updatePasswd(String passwd, Integer id);
    void updateAvatar(Integer id, String avatarPath);
    User getUserById(Integer id);
}

