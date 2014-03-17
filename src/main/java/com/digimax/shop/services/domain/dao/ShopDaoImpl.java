package com.digimax.shop.services.domain.dao;

import com.digimax.shop.entities.domain.Shop;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.*;

/**
 * Created by jon on 2014-03-17.
 */
@SuppressWarnings("unchecked")
public class ShopDaoImpl implements ShopDao {

    @Inject
    private Session session;

    @Override
    public List<Shop> getAll() {
        List<Shop> shops = session.createCriteria(Shop.class).list();
        return shops;
    }

    @Override
    public Shop get(Long id) {
        return (Shop) session.get(Shop.class, id);
    }

    @Override
    public boolean exists(Long id) {
        Shop shop = (Shop) session.load(Shop.class, id);
        return (shop!=null);
    }

    @Override
    public Shop save(Shop shop) {
        session.save(shop);
        return shop;
    }

    @Override
    public void remove(Long id) {
        Shop shop = (Shop) session.load(Shop.class, id);
        session.delete(shop);
    }

    @Override
    public void remove(Shop shop) {
        session.delete(shop);
    }

    @Override
    public List<Shop> getAllDistinct() {
        List<Shop> all = getAll();
        Set<Shop> uniqueSet = new HashSet<>(all);
        return new ArrayList<>(uniqueSet);
    }

    @Override
    public List<Shop> findByNamedQuery(String queryName, Map<String, Object> queryParams) {
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
        return (List<Shop>)q.list();
    }

    @Override
    public Session getSession() {
        return session;
    }
}
