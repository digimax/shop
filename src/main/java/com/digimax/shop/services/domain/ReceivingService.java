package com.digimax.shop.services.domain;

import com.digimax.shop.entities.domain.Receiving;
import com.digimax.shop.entities.domain.invoice.ReceivingInvoice;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;

/**
 * Created by jon on 2014-03-21.
 */
public interface ReceivingService {

    @CommitAfter
    void processInvoice(Receiving receiving, ReceivingInvoice receivingInvoice);
}
