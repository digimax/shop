package com.digimax.shop.pages.item;

import com.digimax.shop.entities.domain.AbstractLocation;
import com.digimax.shop.entities.domain.Receiving;
import com.digimax.shop.entities.domain.item.AbstractItem;
import com.digimax.shop.entities.domain.item.Book;
import org.apache.tapestry5.annotations.PageActivationContext;
import org.apache.tapestry5.annotations.Property;

/**
 * Created by jon on 2014-03-21.
 */
public class Index {

    @PageActivationContext
    @Property
    private AbstractItem item;

    public boolean isBook() {
        return Book.class.isInstance(item);
    }

}
