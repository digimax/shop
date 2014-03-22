package com.digimax.shop.entities.domain;

import com.digimax.shop.entities.domain.item.AbstractItem;
import com.digimax.shop.entities.domain.lineitem.ShelfLineItem;
import com.digimax.shop.services.domain.ItemService;
import com.digimax.shop.services.domain.ShelfService;
import org.apache.tapestry5.annotations.Property;

import javax.inject.Inject;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jon on 2014-03-15.
 */
@Entity
public class Shelf extends AbstractLocation {

    @Property
    @NotNull
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "shelf")
    public Set<ShelfLineItem> lineItems = new HashSet<>();

    public AbstractLineItem addItem(ShelfService shelfService, AbstractItem item, BigDecimal quantity) {
        return shelfService.addItem(this, item, quantity);
    }
}
