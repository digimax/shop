package com.digimax.shop.services.domain;

import com.digimax.shop.entities.domain.Shelf;
import com.digimax.shop.entities.domain.Store;
import com.digimax.shop.entities.domain.invoice.StoreInvoice;
import com.digimax.shop.entities.domain.invoice.lineitem.StoreLineItem;
import com.digimax.shop.entities.domain.lineitem.ShelfLineItem;
import com.digimax.shop.services.domain.dao.AbstractLocationDao;
import com.digimax.shop.services.domain.invoice.dao.AbstractInvoiceDao;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.slf4j.Logger;

/**
 * Created by jon on 2014-03-23.
 */
public class StoreServiceImpl implements StoreService {

    @Inject
    private Logger logger;

    @Inject
    private AbstractLocationDao abstractLocationDao;

    @Inject
    private AbstractInvoiceDao abstractInvoiceDao;

    @Inject
    private ShelfService shelfService;

    @Override
    public StoreInvoice createInvoice(Store store, Shelf fromShelf) {
        StoreInvoice storeInvoice = new StoreInvoice();
        storeInvoice.name = "Test Store Invoice #-000001";
        for (ShelfLineItem lineItem: fromShelf.lineItems) {
            StoreLineItem storeLineItem = new StoreLineItem();
            storeLineItem.item = lineItem.item;
            storeLineItem.quantity = lineItem.quantity;
            storeLineItem.locationName = fromShelf.name;
            storeInvoice.addLineItem(storeLineItem);
        }
        store.addInvoice(storeInvoice);
        save(store);
        return (StoreInvoice) abstractInvoiceDao.save(storeInvoice);
    }

    @Override
    public Store save(Store store) {
        return (Store) abstractLocationDao.save(store);
    }
}
