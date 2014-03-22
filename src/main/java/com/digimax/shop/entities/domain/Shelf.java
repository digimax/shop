package com.digimax.shop.entities.domain;

import com.digimax.shop.entities.domain.item.AbstractItem;
import com.digimax.shop.entities.domain.lineitem.ShelfLineItem;
import com.digimax.shop.services.domain.ShelfService;
import org.apache.tapestry5.annotations.Property;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by jon on 2014-03-15.
 */
@Entity
public class Shelf extends AbstractLocation {

    @Property
    @NotNull
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "shelf")
    public Set<ShelfLineItem> lineItems = new HashSet<>();
    public List<ShelfLineItem> getOrderedLineItems() {
        List<ShelfLineItem> orderedLineItems = new ArrayList<>(lineItems);
        Collections.sort(orderedLineItems, new AbstractLineItem.Compare());
        return orderedLineItems;
    }

    public AbstractLineItem addItem(ShelfService shelfService, AbstractItem item, BigDecimal quantity) {
        return shelfService.addItem(this, item, quantity);
    }

    public static final class Compare implements Comparator<Shelf> {
        @Override
        public int compare(Shelf domainObject1, Shelf domainObject2) {
            return domainObject1.name.compareTo(domainObject2.name);
        }
    }    
}
