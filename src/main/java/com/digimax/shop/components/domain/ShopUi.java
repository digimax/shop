package com.digimax.shop.components.domain;

import com.digimax.shop.entities.domain.Shop;
import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;

/**
 * Created by jon on 2014-03-18.
 */
public class ShopUi {

    @Parameter(required = true, allowNull = false, defaultPrefix = BindingConstants.PROP)
    @Property
    private Shop shop;
}
