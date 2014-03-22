package com.digimax.shop.services.domain.dao;

import com.digimax.shop.entities.domain.AbstractLineItem;
import com.digimax.shop.entities.domain.AbstractLocation;
import com.digimax.shop.entities.domain.Shelf;
import com.digimax.shop.entities.domain.item.AbstractItem;
import com.digimax.shop.entities.domain.lineitem.ShelfLineItem;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.Restrictions;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by jon on 2014-03-17.
 */
@SuppressWarnings("unchecked")
public class ShelfDaoImpl implements ShelfDao {

    @Inject
    private Session session;

    @Override
    public List<Shelf> getAll() {
        List<Shelf> shelfs = session.createCriteria(Shelf.class).list();
        return shelfs;
    }

    @Override
    public Shelf get(Long id) {
        return (Shelf) session.get(Shelf.class, id);
    }

    @Override
    public boolean exists(Long id) {
        Shelf shelf = (Shelf) session.load(Shelf.class, id);
        return (shelf!=null);
    }

    @Override
    public Shelf save(Shelf shelf) {
        session.save(shelf);
        return shelf;
    }

    @Override
    public void remove(Long id) {
        Shelf shelf = (Shelf) session.load(Shelf.class, id);
        session.delete(shelf);
    }

    @Override
    public void remove(Shelf shelf) {
        session.delete(shelf);
    }

    @Override
    public List<Shelf> getAllDistinct() {
        List<Shelf> all = getAll();
        Set<Shelf> uniqueSet = new HashSet<>(all);
        return new ArrayList<>(uniqueSet);
    }

    @Override
    public List<Shelf> findByNamedQuery(String queryName, Map<String, Object> queryParams) {
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
        return (List<Shelf>)q.list();
    }

    public Shelf findShelf(AbstractLocation location, String name) {
        Junction junction = Restrictions.conjunction().add(Restrictions.eq("name", name));
        if (location!=null) {
            junction.add(Restrictions.eq("parent", location));
//            junction.add(Restrictions.sqlRestriction("{alias}.parent_id = (?)", location.id, LongType.INSTANCE));

        }
        Shelf shelf  = (Shelf) session.createCriteria(Shelf.class).add(junction).uniqueResult();
        return shelf;
    }

    public Shelf newShelf(AbstractLocation location, String name) {
        Shelf shelf = new Shelf();
        shelf.name = name;
        location.addLocation(shelf);
        return shelf;
    }

    public Shelf findOrCreateShelf(AbstractLocation location, String name) {
        Shelf shelf = findShelf(location, name);
        if (shelf==null) {
            shelf = newShelf(location, name);
        }
        return shelf;
    }

    @Override
    public AbstractLineItem addItem(Shelf shelf, AbstractItem item, BigDecimal quantity) {
        for (ShelfLineItem shelfLineItem: shelf.lineItems) {
            if (item.equals(shelfLineItem.item)) {
                shelfLineItem.quantity.add(quantity);
                return shelfLineItem;
            }
        }
        ShelfLineItem shelfLineItem = new ShelfLineItem();
        shelfLineItem.item = item;
        shelfLineItem.quantity = quantity;
        shelf.lineItems.add(shelfLineItem);
        shelfLineItem.shelf = shelf;
        save(shelf);
        return shelfLineItem;
    }

}

