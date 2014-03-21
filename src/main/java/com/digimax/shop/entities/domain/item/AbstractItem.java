package com.digimax.shop.entities.domain.item;

import com.digimax.shop.entities.app.Image;
import com.digimax.shop.structural.domain.DomainObject;
import org.apache.tapestry5.annotations.Property;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by jon on 2014-03-15.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class AbstractItem extends DomainObject implements Item {

    @Property(read = false)
    @OneToMany(mappedBy="item", fetch = FetchType.EAGER,
            cascade = {CascadeType.ALL} )
    @Fetch(FetchMode.SUBSELECT)
    public List<Image> images = new ArrayList<>();
    public List<Image> getImages() {
        Collections.sort(images, new Image.Compare());
        return images;
    }

    @Property(read = false)
    public String sku;
    public String getSku() {
        return sku;
    }

    @Property(read = false)
    public Long upc13;
    public Long getUpc13() {
        return upc13;
    }

}
