package com.digimax.shop.entities.domain;

import com.digimax.shop.entities.domain.invoice.AbstractInvoice;
import com.digimax.shop.entities.domain.item.AbstractItem;
import com.digimax.shop.structural.domain.DomainObject;
import org.apache.tapestry5.annotations.Property;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by jon on 2014-03-20.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class AbstractLineItem extends DomainObject implements LineItem {

    @Property
    @ManyToOne(fetch = FetchType.EAGER)
    public AbstractInvoice invoice;

    @Property(read = false)
    public BigDecimal quantity;
    @Override
    public BigDecimal getQuantity() {
        return quantity;
    }

    @Property(read = false)
    @OneToOne(cascade = CascadeType.ALL)
    public AbstractItem item;
    @Override
    public AbstractItem getItem() {
        return item;
    }

    @Override
    public String getName() {
        return item==null? null: item.name;
    }
}
