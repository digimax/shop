package com.digimax.shop.services.bootstrap;

import com.digimax.shop.entities.domain.*;
import com.digimax.shop.services.domain.LocationService;
import com.digimax.shop.services.domain.ShopService;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 * Created by jon on 2014-03-16.
 */
public class BookStoreBootStrapServiceImpl implements BootStrapService {

    @Inject
    private LocationService locationService;

    @Inject
    private ShopService shopService;

    @Override
    public boolean bootUp() {
        //check to see if there is a persisted shop
        Shop shop = shopService.getCurrentShop();
        if (shop==null) {
            shop = new Shop();
            shop.name="Memories Book Shop";
            shop.brand="MBS";
            shop.copyright="&#x00A9; 2014 &nbsp; Memories Book Shop.&nbsp; All rights reserved.";
            addLocationsTo(shop);
            addWorkersTo(shop);
            addBooksTo(shop);
            shopService.save(shop);
            return true;
        }
        return false;
    }

    private void addLocationsTo(Shop shop) {
        //Warehouse
        Warehouse warehouse = new Warehouse();
        warehouse.name = "Warehouse";

        Shelf garageShelf = new Shelf();
        garageShelf.name="Garage";
        locationService.save(garageShelf);

        warehouse.addLocation(garageShelf);
        locationService.save(warehouse);


        //Store
        AbstractLocation store = new Store();
        store.name = "Store";
        locationService.save(store);

        //Receiving
        AbstractLocation receiving = new Receiving();
        receiving.name = "Receiving";
        locationService.save(receiving);

        //Shipping
        AbstractLocation shipping = new Shipping();
        shipping.name = "Shipping";
        locationService.save(shipping);


        shop.addLocation(receiving);
        shop.addLocation(store);
        shop.addLocation(shipping);
        shop.addLocation(warehouse);
    }

    private void addBooksTo(Shop shop) {
        Receiving receiving = shop.getReceiving();
    }

    private void addWorkersTo(Shop shop) {

    }
}
