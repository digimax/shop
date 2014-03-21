package com.digimax.shop.components.domain;

import com.digimax.shop.entities.domain.AbstractLocation;
import com.digimax.shop.entities.domain.Shelf;
import com.digimax.shop.entities.domain.invoice.AbstractInvoice;
import com.digimax.shop.entities.user.Worker;
import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;

import java.util.Set;

/**
 * Created by jon on 2014-03-19.
 */
public class LocationUi {

    @Parameter(required = true, allowNull = false, defaultPrefix = BindingConstants.PROP)
    @Property
    private AbstractLocation location;

    @Property
    private AbstractLocation thisLocation;

    @Property
    private AbstractInvoice thisInvoice;    
    
    @Property
    private Worker thisWorker;

    public boolean hasInvoices() {
        Set<AbstractInvoice> invoices = location.invoices;
        return invoices!=null && !invoices.isEmpty();
    }    
    
    public boolean hasLocations() {
        Set<AbstractLocation> locations = location.locations;
        return locations!=null && !locations.isEmpty();
    }

    public boolean hasWorkers() {
        Set<Worker> workers = location.workers;
        return workers!=null && !workers.isEmpty();
    }
}
