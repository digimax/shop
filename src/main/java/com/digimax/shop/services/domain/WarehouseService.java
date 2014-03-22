package com.digimax.shop.services.domain;

import com.digimax.shop.entities.domain.Shelf;
import com.digimax.shop.entities.domain.Warehouse;
import com.digimax.shop.entities.domain.invoice.ReceivingInvoice;

/**
 * Created by jon on 2014-03-21.
 */
public interface WarehouseService {
    void processInvoice(ReceivingInvoice receivingInvoice);
    void processShelf(Warehouse warehouse, Shelf shelf);
}
