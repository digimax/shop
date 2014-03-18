package com.digimax.shop.entities.domain;

import org.apache.tapestry5.annotations.Property;

import javax.persistence.Entity;

/**
 * Created by jon on 2014-03-15.
 */
@Entity
public class Shop extends AbstractLocation {

    @Property
    public String copyright;

    @Property
    public String brand;

}
