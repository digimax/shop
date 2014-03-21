package com.digimax.shop.services.domain;

import com.digimax.shop.entities.domain.Warehouse;
import com.digimax.shop.entities.domain.invoice.ReceivingInvoice;

import javax.inject.Inject;

/**
 * Created by jon on 2014-03-21.
 */
public class WarehouseServiceImpl implements WarehouseService {

    @Inject
    private ShopService shopService;

    public void processInvoice(ReceivingInvoice receivingInvoice) {
        Warehouse warehouse = shopService.getCurrentShop().getWarehouse();
    }
}
