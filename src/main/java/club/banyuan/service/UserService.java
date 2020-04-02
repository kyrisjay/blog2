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
}
