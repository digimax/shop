package com.digimax.shop.services.domain;

import com.digimax.shop.entities.domain.AbstractLineItem;
import com.digimax.shop.entities.domain.AbstractLocation;
import com.digimax.shop.entities.domain.Shelf;
import com.digimax.shop.entities.domain.item.AbstractItem;
import com.digimax.shop.services.domain.dao.ShelfDao;
import org.apache.tapestry5.ioc.annotations.Inject;

import java.math.BigDecimal;

/**
 * Created by jon on 2014-03-21.
 */
public class ShelfServiceImpl implements ShelfService {

    @Inject
    private ShelfDao shelfDao;

    @Override
    public Shelf findOrCreateShelf(AbstractLocation location, String name) {
        return shelfDao.findOrCreateShelf(location, name);
    }

    @Override
    public AbstractLineItem addItem(Shelf shelf, AbstractItem item, BigDecimal quantity) {
        return shelfDao.addItem(shelf, item, quantity);
    }

    @Override
    public Shelf save(Shelf shelf) {
        return shelfDao.save(shelf);
    }
}
