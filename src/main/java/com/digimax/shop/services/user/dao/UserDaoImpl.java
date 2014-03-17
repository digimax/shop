package com.digimax.shop.services.user.dao;

import com.digimax.shop.entities.domain.Shop;
import com.digimax.shop.entities.user.User;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.*;

/**
 * Created by jon on 2014-03-17.
 */
@SuppressWarnings("unchecked")
public class UserDaoImpl implements UserDao {

    @Inject
    private Session session;

    @Override
    public List<User> getAll() {
        List<User> users = session.createCriteria(User.class).list();
        return users;
    }

    @Override
    public User get(Long id) {
        return (User) session.get(User.class, id);
    }

    @Override
    public boolean exists(Long id) {
        User user = (User) session.load(User.class, id);
        return (user!=null);
    }

    @Override
    public User save(User user) {
        session.save(user);
        return user;
    }

    @Override
    public void remove(Long id) {
        User user = (User) session.load(User.class, id);
        session.delete(user);
    }

    @Override
    public void remove(User user) {
        session.delete(user);
    }

    @Override
    public List<User> getAllDistinct() {
        List<User> all = getAll();
        Set<User> uniqueSet = new HashSet<>(all);
        return new ArrayList<>(uniqueSet);
    }

    @Override
    public List<User> findByNamedQuery(String queryName, Map<String, Object> queryParams) {
        Query q = session.getNamedQuery(queryName);

        String []params = new String[queryParams.size()];
        Object []values = new Object[queryParams.size()];
        int index = 0;
        for (String key : queryParams.keySet()) {
            params[index] = key;
            values[index++] = queryParams.get(key);
        }
        if (queryParams!=null && queryParams.size()>0) {
            Iterator<String> i = queryParams.keySet().iterator();
            for (int j=0; j<params.length; j++) {
                q.setParameter(params[j], values[j]);
            }
        }
        return (List<User>)q.list();
    }    
}

