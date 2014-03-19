package com.digimax.shop.services.domain;

import com.digimax.shop.entities.domain.AbstractLocation;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;

/**
 * Created by jon on 2014-03-15.
 */
public interface LocationService {

    @CommitAfter
    AbstractLocation save(AbstractLocation abstractLocation);
}
