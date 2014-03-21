package com.digimax.shop.components.domain.invoice;

import com.digimax.shop.entities.domain.AbstractLineItem;
import com.digimax.shop.entities.domain.invoice.AbstractInvoice;
import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.BeanModelSource;

/**
 * Created by jon on 2014-03-17.
 */
public class InvoiceUi {

    @Inject
    private BeanModelSource beanModelSource;
    @Inject
    private Messages messages;

    @Parameter(required = true, allowNull = false, defaultPrefix = BindingConstants.PROP)
    @Property
    private AbstractInvoice invoice;

    @Property
    private AbstractLineItem thisLineItem;

    public BeanModel getLineItemModel() {
        BeanModel lineItemModel = beanModelSource.createDisplayModel(AbstractLineItem.class, messages);
        lineItemModel.include("quantity", "name");
        return lineItemModel;
    }

}
