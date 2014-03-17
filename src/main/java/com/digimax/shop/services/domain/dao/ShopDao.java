package com.digimax.shop.services.domain.dao;

import com.digimax.shop.entities.domain.Shop;
import com.digimax.shop.structural.domain.GenericDao;
import org.hibernate.Session;

/**
 * Created by jon on 2014-03-17.
 */
public interface ShopDao extends GenericDao<Shop, Long> {
    Session getSession();
}
