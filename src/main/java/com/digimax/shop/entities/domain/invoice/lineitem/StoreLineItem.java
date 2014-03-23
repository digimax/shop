package com.digimax.shop.entities.domain.invoice.lineitem;

import com.digimax.shop.entities.domain.AbstractLineItem;
import org.apache.tapestry5.annotations.Property;

import javax.persistence.Entity;

/**
 * Created by jon on 2014-03-23.
 */
@Entity
public class StoreLineItem extends AbstractLineItem {

    @Property
    public String locationName;

}
