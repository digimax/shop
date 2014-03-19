package com.digimax.shop.services.domain.dao;

import com.digimax.shop.entities.domain.Store;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.*;

/**
 * Created by jon on 2014-03-17.
 */
@SuppressWarnings("unchecked")
public class StorageDaoImpl implements StorageDao {

    @Inject
    private Session session;

    @Override
    public List<Store> getAll() {
        List<Store> stores = session.createCriteria(Store.class).list();
        return stores;
    }

    @Override
    public Store get(Long id) {
        return (Store) session.get(Store.class, id);
    }

    @Override
    public boolean exists(Long id) {
        Store store = (Store) session.load(Store.class, id);
        return (store !=null);
    }

    @Override
    public Store save(Store store) {
        session.save(store);
        return store;
    }

    @Override
    public void remove(Long id) {
        Store store = (Store) session.load(Store.class, id);
        session.delete(store);
    }

    @Override
    public void remove(Store store) {
        session.delete(store);
    }

    @Override
    public List<Store> getAllDistinct() {
        List<Store> all = getAll();
        Set<Store> uniqueSet = new HashSet<>(all);
        return new ArrayList<>(uniqueSet);
    }

    @Override
    public List<Store> findByNamedQuery(String queryName, Map<String, Object> queryParams) {
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
        return (List<Store>)q.list();
    }
}
