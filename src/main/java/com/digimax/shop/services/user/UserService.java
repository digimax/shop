package com.digimax.shop.services.user;

import com.digimax.shop.entities.user.User;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;

/**
 * Created by jon on 2014-03-15.
 */
public interface UserService {

    @CommitAfter
    User save(User user);

}
