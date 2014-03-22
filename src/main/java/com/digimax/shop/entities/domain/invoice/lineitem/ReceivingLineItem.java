package com.digimax.shop.entities.domain.invoice.lineitem;

import com.digimax.shop.entities.domain.AbstractLineItem;
import org.apache.tapestry5.annotations.Property;

import javax.persistence.Entity;

/**
 * Created by jon on 2014-03-20.
 */
@Entity
public class ReceivingLineItem extends AbstractLineItem {

    @Property
    public String locationName;

}
