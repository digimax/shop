package com.digimax.shop.services.domain;

import com.digimax.shop.entities.domain.AbstractLineItem;
import com.digimax.shop.entities.domain.AbstractLocation;
import com.digimax.shop.entities.domain.Shelf;
import com.digimax.shop.entities.domain.Warehouse;
import com.digimax.shop.entities.domain.invoice.WarehouseInvoice;
import com.digimax.shop.entities.domain.invoice.lineitem.ReceivingLineItem;
import com.digimax.shop.entities.domain.invoice.lineitem.WarehouseLineItem;
import com.digimax.shop.entities.domain.item.AbstractItem;
import com.digimax.shop.entities.domain.lineitem.ShelfLineItem;
import com.digimax.shop.services.domain.dao.AbstractLocationDao;
import com.digimax.shop.services.domain.invoice.dao.AbstractInvoiceDao;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.slf4j.Logger;

/**
 * Created by jon on 2014-03-21.
 */
public class WarehouseServiceImpl implements WarehouseService {

    @Inject
    private Logger logger;

    @Inject
    private AbstractLocationDao abstractLocationDao;

    @Inject
    private AbstractInvoiceDao abstractInvoiceDao;


    @Inject
    private ShelfService shelfService;

    @Override
    public WarehouseInvoice createInvoice(Warehouse warehouse, Shelf fromShelf) {
        WarehouseInvoice warehouseInvoice = new WarehouseInvoice();
        warehouseInvoice.name = "Test Warehouse Invoice #-000001";
        for (ShelfLineItem lineItem: fromShelf.lineItems) {
            WarehouseLineItem warehouseLineItem = new WarehouseLineItem();
            warehouseLineItem.item = lineItem.item;
            warehouseLineItem.quantity = lineItem.quantity;
            warehouseLineItem.locationName = fromShelf.name;
            warehouseInvoice.addLineItem(warehouseLineItem);
        }
        warehouse.addInvoice(warehouseInvoice);
        save(warehouse);
        return (WarehouseInvoice) abstractInvoiceDao.save(warehouseInvoice);
    }

    @Override
    public void processInvoice(Warehouse warehouse, WarehouseInvoice warehouseInvoice) {
        if (!warehouseInvoice.closed) {
            for (AbstractLineItem lineItem: warehouseInvoice.getOrderedLineItems()) {
                WarehouseLineItem warehouseLineItem = ((WarehouseLineItem)lineItem);
                String locationName = ((WarehouseLineItem)lineItem).locationName;
                Shelf shelf = shelfService.findOrCreateShelf(warehouse.locations.iterator().next(), locationName);
                shelf.addItem(shelfService, warehouseLineItem.item, warehouseLineItem.quantity);
                lineItem.processed = true;
            }
            warehouseInvoice.closed = true;
        }
    }

    @Override
    public Warehouse save(Warehouse warehouse) {
        return (Warehouse) abstractLocationDao.save(warehouse);
    }
}
