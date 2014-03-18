package com.digimax.shop.services.bootstrap;

import com.digimax.shop.entities.domain.Shop;
import com.digimax.shop.services.domain.ShopService;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 * Created by jon on 2014-03-16.
 */
public class BookStoreBootStrapServiceImpl implements BootStrapService {

    @Inject
    private ShopService shopService;

    @Override
    public boolean bootUp() {
        //check to see if there is a persisted shop
        Shop shop = shopService.getCurrentShop();
        if (shop==null) {
            shop = new Shop();
            shop.name="Memorial Book Shop";
            addWorkersTo(shop);
            addBooksTo(shop);
            shopService.save(shop);
            return true;
        }
        return false;
    }

    private void addBooksTo(Shop shop) {

    }

    private void addWorkersTo(Shop shop) {

    }
}
