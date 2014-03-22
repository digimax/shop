package com.digimax.shop.services.domain;

import com.digimax.shop.entities.domain.AbstractLineItem;
import com.digimax.shop.entities.domain.AbstractLocation;
import com.digimax.shop.entities.domain.Shelf;
import com.digimax.shop.entities.domain.item.AbstractItem;
import com.digimax.shop.entities.user.Author;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;

import java.math.BigDecimal;

/**
 * Created by jon on 2014-03-21.
 */
public interface ShelfService {

    @CommitAfter
    Shelf findOrCreateShelf(AbstractLocation location, String name);

    @CommitAfter
    AbstractLineItem addItem(Shelf shelf, AbstractItem item, BigDecimal quantity);

    @CommitAfter
    Shelf save(Shelf shelf);
}
