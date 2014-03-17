package com.digimax.shop.services.domain;

import com.digimax.shop.services.domain.dao.ShopDao;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;

/**
 * Created by jon on 2014-03-16.
 */
public class ShopServiceImpl implements ShopService {

    @Inject
    private ShopDao dao;

    @Override
    public Session getSession() {
        return dao.getSession();
    }
}
