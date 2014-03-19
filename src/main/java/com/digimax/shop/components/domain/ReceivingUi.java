package com.digimax.shop.components.domain;

import com.digimax.shop.entities.domain.AbstractLocation;
import com.digimax.shop.entities.domain.Receiving;
import com.digimax.shop.entities.domain.Shop;
import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;

/**
 * Created by jon on 2014-03-19.
 */
public class ReceivingUi {

    @Parameter(required = true, allowNull = false, defaultPrefix = BindingConstants.PROP)
    @Property
    private Receiving receiving;

    @Property
    private AbstractLocation location;
}
