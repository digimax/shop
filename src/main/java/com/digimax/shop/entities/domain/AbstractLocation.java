package com.digimax.shop.entities.domain;

import com.digimax.shop.entities.domain.invoice.AbstractInvoice;
import com.digimax.shop.entities.domain.item.AbstractItem;
import com.digimax.shop.entities.user.Worker;
import com.digimax.shop.structural.domain.DomainObject;
import org.apache.tapestry5.annotations.Property;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by jon on 2014-03-15.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class AbstractLocation extends DomainObject implements Location {

    @Property
    @NotNull
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
    public Set<AbstractLocation> locations = new HashSet<>();

    @Property
    @ManyToOne(fetch = FetchType.EAGER)
    public AbstractLocation parent;

    @Property
    @NotNull
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "location")
    public Set<Worker> workers = new HashSet<>();

    @Property
    @NotNull
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "location")
    public Set<AbstractInvoice> invoices = new HashSet<>();

    @Override
    public void addLocation(AbstractLocation location) {
        locations.add(location);
        location.parent = this;
    }

    @Override
    public void addWorker(Worker worker) {
        workers.add(worker);
        worker.location = this;
    }

    @Override
    public void addInvoice(AbstractInvoice invoice) {
        invoices.add(invoice);
        invoice.location = this;
    }    
    
    @Override
    public Set<LineItem> getLineItems() {
        return null;
    }

    @Override
    public List<AbstractItem> getItems() {
        return null;
    }
}
