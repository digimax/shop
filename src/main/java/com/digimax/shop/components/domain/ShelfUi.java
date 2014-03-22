package com.digimax.shop.components.domain;

import com.digimax.shop.entities.domain.AbstractLineItem;
import com.digimax.shop.entities.domain.AbstractLocation;
import com.digimax.shop.entities.domain.Shelf;
import com.digimax.shop.entities.domain.Shipping;
import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.BeanModelSource;

/**
 * Created by jon on 2014-03-19.
 */
public class ShelfUi {

    @Inject
    private BeanModelSource beanModelSource;
    @Inject
    private Messages messages;

    @Parameter(required = true, allowNull = false, defaultPrefix = BindingConstants.PROP)
    @Property
    private Shelf shelf;

    @Property
    private AbstractLocation location;

    @Property
    private AbstractLineItem thisLineItem;

    public BeanModel getLineItemModel() {
        BeanModel lineItemModel = beanModelSource.createDisplayModel(AbstractLineItem.class, messages);
        lineItemModel.include("quantity", "name");
        return lineItemModel;
    }
}
