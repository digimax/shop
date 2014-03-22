package com.digimax.shop.services.domain;

import com.digimax.shop.entities.domain.AbstractLineItem;
import com.digimax.shop.entities.domain.Receiving;
import com.digimax.shop.entities.domain.Shelf;
import com.digimax.shop.entities.domain.invoice.ReceivingInvoice;
import com.digimax.shop.entities.domain.invoice.lineitem.ReceivingLineItem;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 * Created by jon on 2014-03-21.
 */
public class ReceivingServiceImpl implements ReceivingService {

    @Inject
    private ShelfService shelfService;

    @Override
    public void processInvoice(Receiving receiving, ReceivingInvoice receivingInvoice) {
        if (!receivingInvoice.closed) {
            for (AbstractLineItem lineItem: receivingInvoice.getOrderedLineItems()) {
                ReceivingLineItem receivingLineItem = ((ReceivingLineItem)lineItem);
                String locationName = ((ReceivingLineItem)lineItem).locationName;
                Shelf shelf = shelfService.findOrCreateShelf(receiving.locations.iterator().next(), locationName);
                shelf.addItem(shelfService, receivingLineItem.item, receivingLineItem.quantity);
                lineItem.processed = true;
            }
            receivingInvoice.closed = true;
        }
    }
}
