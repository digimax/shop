package com.digimax.shop.entities.app;

import com.digimax.shop.entities.domain.item.AbstractItem;
import com.digimax.shop.structural.domain.DomainObject;
import org.apache.tapestry5.annotations.Property;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

/**
 * Created by jon on 2014-03-21.
 */
@Entity
public class Image extends DomainObject {

    @Property
    public String fileName;

    @Property
    public int height;

    @Property
    public int width;

    @Property
    public String alt;

    @Property
    public String description;

    @Property
    public String title;


    @Property
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REFRESH} )
    public AbstractItem item;

    public Image() {
        super();
    }

    public Image(String title, String fileName, int width, int height) {
        this();
        this.title = title;
        this.fileName = fileName;
        this.width = width;
        this.height = height;
    }

}
