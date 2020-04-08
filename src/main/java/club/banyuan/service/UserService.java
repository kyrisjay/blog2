package club.banyuan.service;

import club.banyuan.bean.User;
import club.banyuan.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public User findUserByName(String name) {
        return userDao.selectUserByName(name);
    }

    public void updatePasswd(String newPasswd, Integer id) {
        userDao.updatePasswd(newPasswd, id);
    }
    public void updateAvatarById(Integer id, String avatarPath) {
        userDao.updateAvatar(id, avatarPath);
    }

    public User getUserById(Integer id) {
        return userDao.getUserById(id);
    }
}
