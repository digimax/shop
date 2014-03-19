package com.digimax.shop.services.domain.dao;

import com.digimax.shop.entities.domain.AbstractLocation;
import com.digimax.shop.entities.domain.Shop;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.*;

/**
 * Created by jon on 2014-03-18.
 */
public class AbstractLocationDaoImpl implements AbstractLocationDao {

    @Inject
    private Session session;

    @Override
    public List<AbstractLocation> getAll() {
        List<AbstractLocation> abstractLocations = session.createCriteria(AbstractLocation.class).list();
        return abstractLocations;
    }

    @Override
    public AbstractLocation get(Long id) {
        return (AbstractLocation) session.get(AbstractLocation.class, id);
    }

    @Override
    public boolean exists(Long id) {
        AbstractLocation abstractLocation = (AbstractLocation) session.load(AbstractLocation.class, id);
        return (abstractLocation!=null);
    }

    @Override
    public AbstractLocation save(AbstractLocation abstractLocation) {
        session.save(abstractLocation);
        return abstractLocation;
    }

    @Override
    public void remove(Long id) {
        AbstractLocation abstractLocation = (AbstractLocation) session.load(AbstractLocation.class, id);
        session.delete(abstractLocation);
    }

    @Override
    public void remove(AbstractLocation abstractLocation) {
        session.delete(abstractLocation);
    }

    @Override
    public List<AbstractLocation> getAllDistinct() {
        List<AbstractLocation> all = getAll();
        Set<AbstractLocation> uniqueSet = new HashSet<>(all);
        return new ArrayList<>(uniqueSet);
    }

    @Override
    public List<AbstractLocation> findByNamedQuery(String queryName, Map<String, Object> queryParams) {
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
        return (List<AbstractLocation>)q.list();
    }

}
