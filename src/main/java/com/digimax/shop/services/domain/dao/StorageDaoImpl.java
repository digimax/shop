package com.digimax.shop.services.domain.dao;

import com.digimax.shop.entities.domain.Shop;
import com.digimax.shop.entities.domain.Storage;
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
    public List<Storage> getAll() {
        List<Storage> storages = session.createCriteria(Storage.class).list();
        return storages;
    }

    @Override
    public Storage get(Long id) {
        return (Storage) session.get(Storage.class, id);
    }

    @Override
    public boolean exists(Long id) {
        Storage storage = (Storage) session.load(Storage.class, id);
        return (storage!=null);
    }

    @Override
    public Storage save(Storage storage) {
        session.save(storage);
        return storage;
    }

    @Override
    public void remove(Long id) {
        Storage storage = (Storage) session.load(Storage.class, id);
        session.delete(storage);
    }

    @Override
    public void remove(Storage storage) {
        session.delete(storage);
    }

    @Override
    public List<Storage> getAllDistinct() {
        List<Storage> all = getAll();
        Set<Storage> uniqueSet = new HashSet<>(all);
        return new ArrayList<>(uniqueSet);
    }

    @Override
    public List<Storage> findByNamedQuery(String queryName, Map<String, Object> queryParams) {
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
        return (List<Storage>)q.list();
    }
}
