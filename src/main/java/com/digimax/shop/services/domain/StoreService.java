package com.digimax.shop.services.domain;

import com.digimax.shop.entities.domain.Shelf;
import com.digimax.shop.entities.domain.Store;
import com.digimax.shop.entities.domain.invoice.StoreInvoice;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;

/**
 * Created by jon on 2014-03-23.
 */
public interface StoreService {
    @CommitAfter
    StoreInvoice createInvoice(Store store, Shelf fromShelf);
    @CommitAfter
    Store save(Store store);
}
