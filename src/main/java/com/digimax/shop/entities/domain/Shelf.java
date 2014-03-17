package com.digimax.shop.entities.domain;

import javax.persistence.Entity;
import java.util.Set;

/**
 * Created by jon on 2014-03-15.
 */
@Entity
public class Shelf extends AbstractLocation {


    @Override
    public Set<Shelf> getShelves() {
        return null;
    }
}
