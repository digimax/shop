package com.digimax.shop.components.domain.invoice;

import com.digimax.shop.entities.domain.invoice.AbstractInvoice;
import com.digimax.shop.entities.domain.invoice.ReceivingInvoice;
import com.digimax.shop.services.domain.ReceivingService;
import com.digimax.shop.services.domain.ShopService;
import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.slf4j.Logger;

/**
 * Created by jon on 2014-03-21.
 */
public class ReceivingInvoiceTool {

    @Inject
    private Logger logger;

    @Inject
    private ShopService shopService;

    @Inject
    private ReceivingService receivingService;

    @Parameter(required = true, allowNull = false, defaultPrefix = BindingConstants.PROP)
    @Property
    private ReceivingInvoice receivingInvoice;

    private void onActionFromReceiveInvoice() {
        receivingService.processInvoice(shopService.getCurrentShop().getReceiving(), receivingInvoice);
    }

}
