package com.digimax.shop.services.domain;

import com.digimax.shop.entities.domain.Receiving;
import com.digimax.shop.entities.domain.invoice.ReceivingInvoice;

/**
 * Created by jon on 2014-03-21.
 */
public interface ReceivingService {
    void processInvoice(Receiving receiving, ReceivingInvoice receivingInvoice);
}
