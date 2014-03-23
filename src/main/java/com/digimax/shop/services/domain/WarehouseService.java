package com.digimax.shop.services.domain;

import com.digimax.shop.entities.domain.AbstractLocation;
import com.digimax.shop.entities.domain.Receiving;
import com.digimax.shop.entities.domain.Shelf;
import com.digimax.shop.entities.domain.Warehouse;
import com.digimax.shop.entities.domain.invoice.ReceivingInvoice;
import com.digimax.shop.entities.domain.invoice.WarehouseInvoice;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;

/**
 * Created by jon on 2014-03-21.
 */
public interface WarehouseService {
    @CommitAfter
    WarehouseInvoice createInvoice(Warehouse warehouse, Shelf shelf);
    @CommitAfter
    void processInvoice(Warehouse warehouse, WarehouseInvoice warehouseInvoice);
    @CommitAfter
    Warehouse save(Warehouse warehouse);
}
