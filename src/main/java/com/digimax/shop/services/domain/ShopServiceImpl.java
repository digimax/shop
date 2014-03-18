package com.digimax.shop.services.domain;

import com.digimax.shop.entities.domain.Shop;
import com.digimax.shop.services.domain.dao.ShopDao;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;

import java.util.Collection;

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

    @Override
    public Shop getCurrentShop() {
        Collection<Shop> allDistinct = dao.getAllDistinct();
        //get the 1st Shop entity
        return allDistinct.isEmpty()? null: allDistinct.iterator().next();
    }

    @Override
    public Shop save(Shop shop) {
        return dao.save(shop);
    }
}
