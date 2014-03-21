package com.digimax.shop.entities.domain;

import com.digimax.shop.entities.domain.invoice.AbstractInvoice;
import com.digimax.shop.entities.user.Worker;

import java.util.List;
import java.util.Set;

/**
 * Created by jon on 2014-03-15.
 */
public interface Location {

    void addInvoice(AbstractInvoice invoice);
    void addLocation(AbstractLocation location);
    void addWorker(Worker worker);
    Set<LineItem> getLineItems();
    List<AbstractItem> getItems();
}
