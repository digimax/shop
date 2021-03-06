package com.digimax.shop.services.domain.dao;

import com.digimax.shop.entities.domain.AbstractLineItem;
import com.digimax.shop.entities.domain.AbstractLocation;
import com.digimax.shop.entities.domain.Shelf;
import com.digimax.shop.entities.domain.item.AbstractItem;
import com.digimax.shop.structural.domain.GenericDao;

import java.math.BigDecimal;

/**
 * Created by jon on 2014-03-17.
 */
public interface ShelfDao extends GenericDao<Shelf, Long> {
    Shelf findOrCreateShelf(AbstractLocation location, String name);
    AbstractLineItem addItem(Shelf shelf, AbstractItem item, BigDecimal quantity);
}
