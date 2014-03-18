package com.digimax.shop.pages.shopping;

import com.digimax.shop.entities.domain.Shop;
import com.digimax.shop.services.domain.ShopService;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 * Created by jon on 2014-03-16.
 */
public class Index {

    private Shop shop;

    @Inject
    private ShopService shopService;

    public Shop getShop() {
        if (shop==null) {
            shop = shopService.getCurrentShop();
        }
        return shop;
    }

    @AfterRender
    private void afterRender() {
        //Clear instance variables
        shop = null;
    }
}
