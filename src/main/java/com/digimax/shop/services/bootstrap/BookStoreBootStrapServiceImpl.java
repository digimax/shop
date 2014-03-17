package com.digimax.shop.services.bootstrap;

import com.digimax.shop.services.domain.ShopService;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 * Created by jon on 2014-03-16.
 */
public class BookStoreBootStrapServiceImpl implements BootStrapService {

    @Inject
    private ShopService shopService;

    @Override
    public boolean bootUp() {
        return false;
    }
}
