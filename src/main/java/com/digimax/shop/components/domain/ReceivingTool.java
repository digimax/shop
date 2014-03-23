package com.digimax.shop.components.domain;

import com.digimax.shop.entities.domain.*;
import com.digimax.shop.entities.domain.invoice.WarehouseInvoice;
import com.digimax.shop.services.domain.WarehouseService;
import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.Link;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;

/**
 * Created by jon on 2014-03-23.
 */
public class ReceivingTool {

    @Inject
    PageRenderLinkSource pageRenderLinkSource;

    @Inject
    private WarehouseService warehouseService;

    @Parameter(required = true, allowNull = false, defaultPrefix = BindingConstants.PROP)
    @Property
    private Receiving receiving;

    public Warehouse getWarehouse() {
        return ((Shop)receiving.parent).getWarehouse();
    }

    private Link onActionFromCreateInvoiceForWarehouse() {
        Shelf mainReceivingArea = (Shelf) receiving.locations.iterator().next();
        Shelf primaryReceivingShelf = (Shelf) mainReceivingArea.locations.iterator().next();
        WarehouseInvoice warehouseInvoice = warehouseService.createInvoice(getWarehouse(), primaryReceivingShelf);
        return pageRenderLinkSource.createPageRenderLinkWithContext(com.digimax.shop.pages.invoice.Index.class
                , warehouseInvoice.id);
    }
}
