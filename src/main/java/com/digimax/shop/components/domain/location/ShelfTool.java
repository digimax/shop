package com.digimax.shop.components.domain.location;

import com.digimax.shop.entities.domain.AbstractLocation;
import com.digimax.shop.entities.domain.Shelf;
import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;

/**
 * Created by jon on 2014-03-23.
 */
public class ShelfTool {

    @Parameter(required = true, allowNull = false, defaultPrefix = BindingConstants.PROP)
    @Property
    private Shelf shelf;

}
