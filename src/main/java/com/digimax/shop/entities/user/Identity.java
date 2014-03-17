package com.digimax.shop.entities.user;

import com.digimax.shop.structural.domain.DomainObject;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.Validate;

import javax.persistence.Entity;

/**
 * Created by jon on 2014-03-16.
 */
@Entity
public class Identity extends DomainObject {

    @Property
    @Validate("required, regex")
    public String emailAddress;

    @Property
    public String ipAddress;

    @Property
    @Validate("required, minlength=6, regexp")
    public String password;
 }
