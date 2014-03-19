package com.digimax.shop.entities.domain;

import com.digimax.shop.structural.domain.DomainObject;
import org.apache.tapestry5.annotations.Property;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by jon on 2014-03-15.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class AbstractLocation extends DomainObject implements Location {

    @Property
    @NotNull
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
    public Set<AbstractLocation> locations = new HashSet<>();

    @Property
    @ManyToOne(fetch = FetchType.EAGER)
    public AbstractLocation parent;



    public void addLocation(AbstractLocation location) {
        locations.add(location);
        location.parent = this;
    }

    @Override
    public Set<LineItem> getLineItems() {
        return null;
    }

    @Override
    public List<Item> getItems() {
        return null;
    }

    @Override
    public Set<Shelf> getShelves() {
        return null;
    }
}
