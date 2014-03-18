package com.digimax.shop.services.domain;

import com.digimax.shop.entities.domain.Shop;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.hibernate.Session;

/**
 * Created by jon on 2014-03-16.
 */
public interface ShopService {
    Session getSession();
    Shop getCurrentShop();
    @CommitAfter
    Shop save(Shop shop);
}
