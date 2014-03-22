package com.digimax.shop.components.domain.invoice;

import com.digimax.shop.entities.domain.AbstractLocation;
import com.digimax.shop.entities.domain.Receiving;
import com.digimax.shop.entities.domain.invoice.ReceivingInvoice;
import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Zone;

/**
 * Created by jon on 2014-03-21.
 */
public class ReceivingInvoiceUi {

    @InjectComponent
    @Property(read = false)
    private Zone invoiceZone;
    public Zone getInvoiceZone() {
        return invoiceZone;
    }

    @Parameter(required = true, allowNull = false, defaultPrefix = BindingConstants.PROP)
    @Property
    private ReceivingInvoice receivingInvoice;
}
