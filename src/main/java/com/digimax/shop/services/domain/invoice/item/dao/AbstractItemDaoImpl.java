package com.digimax.shop.services.domain.invoice.item.dao;

import com.digimax.shop.entities.domain.item.AbstractItem;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.*;

/**
 * Created by jon on 2014-03-20.
 */
@SuppressWarnings("unchecked")
public class AbstractItemDaoImpl implements AbstractItemDao {

    @Inject
    private Session session;

    @Override
    public List<AbstractItem> getAll() {
        List<AbstractItem> items = session.createCriteria(AbstractItem.class).list();
        return items;
    }

    @Override
    public AbstractItem get(Long id) {
        return (AbstractItem) session.get(AbstractItem.class, id);
    }

    @Override
    public boolean exists(Long id) {
        AbstractItem item = (AbstractItem) session.load(AbstractItem.class, id);
        return (item!=null);
    }

    @Override
    public AbstractItem save(AbstractItem item) {
        session.save(item);
        return item;
    }

    @Override
    public void remove(Long id) {
        AbstractItem item = (AbstractItem) session.load(AbstractItem.class, id);
        session.delete(item);
    }

    @Override
    public void remove(AbstractItem item) {
        session.delete(item);
    }

    @Override
    public List<AbstractItem> getAllDistinct() {
        List<AbstractItem> all = getAll();
        Set<AbstractItem> uniqueSet = new HashSet<>(all);
        return new ArrayList<>(uniqueSet);
    }

    @Override
    public List<AbstractItem> findByNamedQuery(String queryName, Map<String, Object> queryParams) {
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
        return (List<AbstractItem>)q.list();
    }
}
