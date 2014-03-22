package com.digimax.shop.entities.domain.lineitem;

import com.digimax.shop.entities.domain.AbstractLineItem;
import com.digimax.shop.entities.domain.Shelf;
import org.apache.tapestry5.annotations.Property;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

/**
 * Created by jon on 2014-03-21.
 */
@Entity
public class ShelfLineItem extends AbstractLineItem {

    @Property
    @ManyToOne(fetch = FetchType.EAGER)
    public Shelf shelf;

}
