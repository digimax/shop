package com.digimax.shop.entities.domain;

import com.digimax.shop.entities.domain.invoice.AbstractInvoice;
import com.digimax.shop.entities.domain.item.AbstractItem;
import com.digimax.shop.structural.domain.DomainObject;
import org.apache.tapestry5.annotations.Property;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Comparator;

/**
 * Created by jon on 2014-03-20.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class AbstractLineItem extends DomainObject implements LineItem {

    @Property
    transient public boolean selected;

    @Property
    public boolean processed;

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

    public static final class Compare implements Comparator<AbstractLineItem> {
        @Override
        public int compare(AbstractLineItem lineItem1, AbstractLineItem lineItem2) {
            return lineItem1.getName().compareTo(lineItem2.getName());
        }
    }    
}
