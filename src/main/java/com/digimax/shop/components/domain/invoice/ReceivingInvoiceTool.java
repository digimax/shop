package com.digimax.shop.components.domain.invoice;

import com.digimax.shop.entities.domain.invoice.AbstractInvoice;
import com.digimax.shop.entities.domain.invoice.ReceivingInvoice;
import com.digimax.shop.services.domain.ReceivingService;
import com.digimax.shop.services.domain.ShopService;
import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.Link;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;
import org.slf4j.Logger;

/**
 * Created by jon on 2014-03-21.
 */
public class ReceivingInvoiceTool {

    @Inject
    private PageRenderLinkSource pageRenderLinkSource;

    @Inject
    private Logger logger;

    @Inject
    private ShopService shopService;

    @Inject
    private ReceivingService receivingService;

    @Parameter(allowNull = false, required = true, defaultPrefix = BindingConstants.PROP)
    @Property(read = false)
    private Zone refreshZone;
    public Zone getRefreshZone() {
        return refreshZone;
    }

    @Parameter(required = true, allowNull = false, defaultPrefix = BindingConstants.PROP)
    @Property
    private ReceivingInvoice receivingInvoice;

    private Link onActionFromReceiveInvoice() {
        receivingService.processInvoice(shopService.getCurrentShop().getReceiving(), receivingInvoice);
        return pageRenderLinkSource.createPageRenderLinkWithContext(com.digimax.shop.pages.location.Index.class
                , shopService.getCurrentShop().getReceiving().id);
    }

}
