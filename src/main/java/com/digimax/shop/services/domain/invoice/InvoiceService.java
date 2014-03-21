package com.digimax.shop.services.domain.invoice;

import com.digimax.shop.entities.domain.invoice.AbstractInvoice;
import com.digimax.shop.structural.domain.GenericDao;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;

/**
 * Created by jon on 2014-03-20.
 */
public interface InvoiceService {

    @CommitAfter
    AbstractInvoice close(AbstractInvoice invoice);

    @CommitAfter
    AbstractInvoice save(AbstractInvoice invoice);
}
