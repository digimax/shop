package com.digimax.shop.services.domain.invoice;

import com.digimax.shop.entities.domain.invoice.AbstractInvoice;
import com.digimax.shop.services.domain.invoice.dao.AbstractInvoiceDao;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 * Created by jon on 2014-03-20.
 */
public class InvoiceServiceImpl implements InvoiceService {

    @Inject
    private AbstractInvoiceDao abstractInvoiceDao;

    @Override
    public AbstractInvoice save(AbstractInvoice invoice) {
        return abstractInvoiceDao.save(invoice);
    }
}
