package com.digimax.shop.services.bootstrap;

import com.digimax.shop.services.QaRegistryTest;
import com.digimax.shop.services.domain.ShopService;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by jon on 2014-03-17.
 */
@Test
public class BootstrapApplicationTest extends QaRegistryTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(BootstrapApplicationTest.class);

    @BeforeMethod
    public void bootup() {
        BootStrapService bootStrapService = registry.getService(BootStrapService.class);
        boolean success = bootStrapService.bootUp();
        LOGGER.debug("FINISHED BOOTUP NODES");
    }

    @Test
    public void testBootup() {
        ShopService shopService = registry.getService(ShopService.class);
        Session session = shopService.getSession();
        session.clear();
    }
}
