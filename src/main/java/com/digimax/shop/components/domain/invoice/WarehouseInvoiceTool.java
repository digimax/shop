package com.digimax.shop.components.domain.invoice;

import com.digimax.shop.entities.domain.invoice.WarehouseInvoice;
import com.digimax.shop.services.domain.ShopService;
import com.digimax.shop.services.domain.WarehouseService;
import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.Link;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;
import org.slf4j.Logger;

/**
 * Created by jon on 2014-03-23.
 */
public class WarehouseInvoiceTool {


    @Inject
    private PageRenderLinkSource pageRenderLinkSource;

    @Inject
    private Logger logger;

    @Inject
    private ShopService shopService;

    @Inject
    private WarehouseService warehouseService;

    @Parameter(allowNull = false, required = true, defaultPrefix = BindingConstants.PROP)
    @Property(read = false)
    private Zone refreshZone;
    public Zone getRefreshZone() {
        return refreshZone;
    }

    @Parameter(required = true, allowNull = false, defaultPrefix = BindingConstants.PROP)
    @Property
    private WarehouseInvoice warehouseInvoice;

    private Link onActionFromReceiveInvoice() {
        warehouseService.processInvoice(shopService.getCurrentShop().getWarehouse(), warehouseInvoice);
        return pageRenderLinkSource.createPageRenderLinkWithContext(com.digimax.shop.pages.location.Index.class
                , shopService.getCurrentShop().getWarehouse().id);
    }

}
