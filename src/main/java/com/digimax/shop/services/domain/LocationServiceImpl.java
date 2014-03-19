package com.digimax.shop.services.domain;

import com.digimax.shop.entities.domain.AbstractLocation;
import com.digimax.shop.services.domain.dao.AbstractLocationDao;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 * Created by jon on 2014-03-15.
 */
public class LocationServiceImpl implements LocationService {

    @Inject
    private AbstractLocationDao abstractLocationDao;


    @Override
    public AbstractLocation save(AbstractLocation abstractLocation) {
        return abstractLocationDao.save(abstractLocation);
    }
}
