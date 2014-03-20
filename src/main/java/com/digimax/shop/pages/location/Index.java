package com.digimax.shop.pages.location;

import com.digimax.shop.entities.domain.*;
import org.apache.tapestry5.annotations.PageActivationContext;
import org.apache.tapestry5.annotations.Property;

/**
 * Created by jon on 2014-03-19.
 */
public class Index {

    @PageActivationContext
    @Property
    private AbstractLocation location;

    public boolean isReceiving() {
        return Receiving.class.isInstance(location);
    }

    public boolean isShipping() {
        return Shipping.class.isInstance(location);
    }

    public boolean isShelf() {
        return Shelf.class.isInstance(location);
    }

    public boolean isStore() {
        return Store.class.isInstance(location);
    }

    public boolean isWarehouse() {
        return Warehouse.class.isInstance(location);
    }

}
