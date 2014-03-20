package com.digimax.shop.services.user;

import com.digimax.shop.entities.user.User;
import com.digimax.shop.services.user.dao.UserDao;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 * Created by jon on 2014-03-15.
 */
public class UserServiceImpl implements UserService {

    @Inject
    private UserDao userDao;

    @Override
    public User save(User user) {
        return userDao.save(user);
    }
}
