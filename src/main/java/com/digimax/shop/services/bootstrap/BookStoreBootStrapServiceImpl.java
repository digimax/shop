package com.digimax.shop.services.bootstrap;

import com.digimax.shop.entities.domain.*;
import com.digimax.shop.entities.domain.invoice.ReceivingInvoice;
import com.digimax.shop.entities.domain.invoice.lineitem.ReceivingLineItem;
import com.digimax.shop.entities.domain.item.Book;
import com.digimax.shop.entities.user.Worker;
import com.digimax.shop.services.domain.ItemService;
import com.digimax.shop.services.domain.LocationService;
import com.digimax.shop.services.domain.ShopService;
import com.digimax.shop.services.domain.invoice.InvoiceService;
import com.digimax.shop.services.user.UserService;
import org.apache.tapestry5.ioc.annotations.Inject;

import java.math.BigDecimal;

/**
 * Created by jon on 2014-03-16.
 */
public class BookStoreBootStrapServiceImpl implements BootStrapService {

    @Inject
    private InvoiceService invoiceService;

    @Inject
    private ItemService itemService;

    @Inject
    private LocationService locationService;

    @Inject
    private ShopService shopService;

    @Inject
    private UserService userService;

    @Override
    public boolean bootUp() {
        //check to see if there is a persisted shop
        Shop shop = shopService.getCurrentShop();
        if (shop==null) {
            shop = new Shop();
            shop.name="Memories Book Shop";
            shop.brand="Memories Book Shop";
            shop.copyright="&#x00A9; 2014 Memories Book Shop.&nbsp; All rights reserved.";
            shopService.save(shop);
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
        Store store = new Store();
        store.name = "Store";

        Shelf storeShelveArea = new Shelf();
        storeShelveArea.name="Shopping Shelves Area";
        locationService.save(storeShelveArea);
        store.addLocation(storeShelveArea);
        locationService.save(store);

        //Receiving
        Receiving receiving = new Receiving();
        receiving.name = "Receiving";
        Shelf receivingArea = new Shelf();
        receivingArea.name="Receiving Area";
        locationService.save(receivingArea);
        receiving.addLocation(receivingArea);
        locationService.save(receiving);

        //Shipping
        Shipping shipping = new Shipping();
        shipping.name = "Shipping";
        Shelf loadingdock = new Shelf();
        loadingdock.name = "Loading Dock, Shipping Side";
        locationService.save(loadingdock);
        shipping.addLocation(loadingdock);
        locationService.save(shipping);


        shop.addLocation(receiving);
        shop.addLocation(store);
        shop.addLocation(shipping);
        shop.addLocation(warehouse);
    }

    private void addBooksTo(Shop shop) {
        Receiving receiving = shop.getReceiving();

        ReceivingInvoice receivingInvoice = new ReceivingInvoice();
        receivingInvoice.name = "Receiving Invoice #-000001";

        Book romeBook = new Book();
        romeBook.name = "Rome";
        itemService.save(romeBook);

        Book veniceBook = new Book();
        veniceBook.name = "Venice";
        itemService.save(veniceBook);

        ReceivingLineItem receivingLineItem1 = new ReceivingLineItem();
        receivingLineItem1.quantity = BigDecimal.ONE;
        receivingLineItem1.item = romeBook;
        ReceivingLineItem receivingLineItem2 = new ReceivingLineItem();
        receivingLineItem2.quantity = BigDecimal.TEN;
        receivingLineItem2.item = veniceBook;

        receivingInvoice.addLineItem(receivingLineItem1);
        receivingInvoice.addLineItem(receivingLineItem2);
        invoiceService.save(receivingInvoice);

        receiving.addInvoice(receivingInvoice);
    }

    private void addWorkersTo(Shop shop) {
        //Owner
        Worker owner = new Worker("Williams", "Jonathan", "jon@digimax.com");
        userService.save(owner);
        shop.getReceiving().addWorker(owner);
    }
}
