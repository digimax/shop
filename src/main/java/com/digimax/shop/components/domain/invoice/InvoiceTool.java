package com.digimax.shop.components.domain.invoice;

import com.digimax.shop.entities.domain.invoice.AbstractInvoice;
import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;

/**
 * Created by jon on 2014-03-21.
 */
public class InvoiceTool {

    @Parameter(required = true, allowNull = false, defaultPrefix = BindingConstants.PROP)
    @Property
    private AbstractInvoice invoice;

}
