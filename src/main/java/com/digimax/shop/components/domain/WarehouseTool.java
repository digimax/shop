package com.digimax.shop.components.domain;

import com.digimax.shop.entities.domain.*;
import com.digimax.shop.entities.domain.invoice.StoreInvoice;
import com.digimax.shop.services.domain.StoreService;
import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.Link;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;

/**
 * Created by jon on 2014-03-23.
 */
public class WarehouseTool {

    @Inject
    PageRenderLinkSource pageRenderLinkSource;

    @Inject
    private StoreService storeService;

    @Parameter(required = true, allowNull = false, defaultPrefix = BindingConstants.PROP)
    @Property
    private Warehouse warehouse;

    public Store getStore() {
        return ((Shop)warehouse.parent).getStore();
    }

    private Link onActionFromCreateInvoiceForStore() {
        Shelf garageArea = (Shelf) warehouse.locations.iterator().next();
        Shelf primaryWarehouseShelf = (Shelf) garageArea.locations.iterator().next();
        StoreInvoice storeInvoice = storeService.createInvoice(getStore(), primaryWarehouseShelf);
        return pageRenderLinkSource.createPageRenderLinkWithContext(com.digimax.shop.pages.invoice.Index.class
                , storeInvoice.id);
    }

}
