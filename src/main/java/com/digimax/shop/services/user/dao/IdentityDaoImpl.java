package com.digimax.shop.services.user.dao;

import com.digimax.shop.entities.user.Identity;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.*;

/**
 * Created by jon on 2014-03-17.
 */
@SuppressWarnings("unchecked")
public class IdentityDaoImpl implements IdentityDao {

    @Inject
    private Session session;

    @Override
    public List<Identity> getAll() {
        List<Identity> identitys = session.createCriteria(Identity.class).list();
        return identitys;
    }

    @Override
    public Identity get(Long id) {
        return (Identity) session.get(Identity.class, id);
    }

    @Override
    public boolean exists(Long id) {
        Identity identity = (Identity) session.load(Identity.class, id);
        return (identity!=null);
    }

    @Override
    public Identity save(Identity identity) {
        session.save(identity);
        return identity;
    }

    @Override
    public void remove(Long id) {
        Identity identity = (Identity) session.load(Identity.class, id);
        session.delete(identity);
    }

    @Override
    public void remove(Identity identity) {
        session.delete(identity);
    }

    @Override
    public List<Identity> getAllDistinct() {
        List<Identity> all = getAll();
        Set<Identity> uniqueSet = new HashSet<>(all);
        return new ArrayList<>(uniqueSet);
    }

    @Override
    public List<Identity> findByNamedQuery(String queryName, Map<String, Object> queryParams) {
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
        return (List<Identity>)q.list();
    }    
}
