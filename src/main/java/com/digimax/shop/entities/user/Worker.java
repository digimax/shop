package com.digimax.shop.entities.user;

import com.digimax.shop.entities.domain.AbstractLocation;
import org.apache.tapestry5.annotations.Property;

import javax.annotation.Nullable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Null;

/**
 * Created by jon on 2014-03-17.
 */
@Entity
public class Worker extends Person {

    @Property
    @ManyToOne(fetch = FetchType.EAGER)
    public AbstractLocation location;

    public Worker() {
        super();
    }
    public Worker(String firstName, String lastName, String userId) {
        super(firstName, lastName, userId);
    }
}
