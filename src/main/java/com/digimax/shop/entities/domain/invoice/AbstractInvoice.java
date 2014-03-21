package com.digimax.shop.entities.domain.invoice;

import com.digimax.shop.entities.domain.AbstractLineItem;
import com.digimax.shop.entities.domain.AbstractLocation;
import com.digimax.shop.structural.domain.DomainObject;
import org.apache.tapestry5.annotations.Property;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by jon on 2014-03-16.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class AbstractInvoice extends DomainObject implements Invoice {

    @Property
    public boolean closed;

    @Property
    @ManyToOne(fetch = FetchType.EAGER)
    public AbstractLocation location;

    @Property
    @NotNull
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "invoice")
    public Set<AbstractLineItem> lineItems = new HashSet<>();
    public List<AbstractLineItem> getOrderedLineItems() {
        List<AbstractLineItem> orderedLineItems = new ArrayList<>(lineItems);
        return orderedLineItems;
    }

    public void addLineItem(AbstractLineItem lineItem) {
        lineItems.add(lineItem);
        lineItem.invoice = this;
    }
}
