package com.digimax.shop.services.domain;

import com.digimax.shop.entities.domain.AbstractItem;
import com.digimax.shop.services.domain.invoice.item.dao.AbstractItemDao;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 * Created by jon on 2014-03-15.
 */
public class ItemServiceImpl implements ItemService {

    @Inject
    private AbstractItemDao abstractItemDao;

    @Override
    public AbstractItem save(AbstractItem item) {
        return abstractItemDao.save(item);
    }
}
