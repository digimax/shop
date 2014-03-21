package com.digimax.shop.components.domain.item;

import com.digimax.shop.entities.domain.AbstractLocation;
import com.digimax.shop.entities.domain.item.Book;
import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;

/**
 * Created by jon on 2014-03-21.
 */
public class BookUi {

    @Parameter(required = true, allowNull = false, defaultPrefix = BindingConstants.PROP)
    @Property
    private Book book;

}
