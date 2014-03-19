package com.digimax.shop.entities.domain;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import org.apache.tapestry5.annotations.Property;

import javax.persistence.Entity;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jon on 2014-03-15.
 */
@Entity
public class Shop extends AbstractLocation {

    @Property
    public String copyright;

    @Property
    public String brand;

    public Receiving getReceiving() {
        Iterator<AbstractLocation> receivings = getLocations(Receiving.class).iterator();
        return !receivings.hasNext()?null: (Receiving) receivings.next();
    }
    public Shipping getShipping() {
        Iterator<AbstractLocation> shippings = getLocations(Shipping.class).iterator();
        return !shippings.hasNext()?null: (Shipping) shippings.next();
    }
    public Store getStore() {
        Iterator<AbstractLocation> stores = getLocations(Store.class).iterator();
        return !stores.hasNext()?null: (Store) stores.next();
    }
    public Warehouse getWarehouse() {
        Iterator<AbstractLocation> warehouses = getLocations(Warehouse.class).iterator();
        return !warehouses.hasNext()?null: (Warehouse) warehouses.next();
    }    
    public Iterable<AbstractLocation> getLocations(final Class locationType) {
        Iterable<AbstractLocation> filtered = Iterables.filter(locations, new Predicate<AbstractLocation>() {

            @Override
            public boolean apply(AbstractLocation abstractLocation) {
                return locationType.isInstance(abstractLocation);
            }
        });
        return filtered;
    }



}

