package com.digimax.shop.services.domain;

import com.digimax.shop.entities.domain.Shelf;
import com.digimax.shop.entities.domain.Warehouse;
import com.digimax.shop.entities.domain.invoice.ReceivingInvoice;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.slf4j.Logger;

/**
 * Created by jon on 2014-03-21.
 */
public class WarehouseServiceImpl implements WarehouseService {

    @Inject
    private Logger logger;

    @Inject
    private ShopService shopService;

    public void processInvoice(ReceivingInvoice receivingInvoice) {
        Warehouse warehouse = shopService.getCurrentShop().getWarehouse();
    }

    @Override
    public void processShelf(Warehouse warehouse, Shelf shelf) {

    }
}
