package com.digimax.shop.entities.domain.item;

import com.digimax.shop.structural.domain.DomainObject;
import org.apache.tapestry5.annotations.Property;

import javax.persistence.Entity;

/**
 * Created by jon on 2014-03-15.
 */
@Entity
public class AbstractItem extends DomainObject implements Item {

    @Property
    public String sku;

    @Property
    public String upc13;

}
