package com.digimax.shop.entities.domain.invoice;

import com.digimax.shop.structural.domain.DomainObject;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * Created by jon on 2014-03-16.
 */
@Inheritance(strategy = InheritanceType.JOINED)
public class AbstractInvoice extends DomainObject implements Invoice {
}
