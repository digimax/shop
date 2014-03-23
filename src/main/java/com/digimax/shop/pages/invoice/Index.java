package com.digimax.shop.pages.invoice;

import com.digimax.shop.entities.domain.AbstractLocation;
import com.digimax.shop.entities.domain.Warehouse;
import com.digimax.shop.entities.domain.invoice.AbstractInvoice;
import com.digimax.shop.entities.domain.invoice.ReceivingInvoice;
import com.digimax.shop.entities.domain.invoice.WarehouseInvoice;
import org.apache.tapestry5.annotations.PageActivationContext;
import org.apache.tapestry5.annotations.Property;

/**
 * Created by jon on 2014-03-20.
 */
public class Index {

    @PageActivationContext
    @Property
    private AbstractInvoice invoice;

    public boolean isReceivingInvoice() {
        return ReceivingInvoice.class.isInstance(invoice);
    }

    public boolean isWarehouseInvoice() {
        return WarehouseInvoice.class.isInstance(invoice);
    }
}
