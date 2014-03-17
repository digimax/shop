package com.digimax.shop.services;

/**
 * Created by jon on 2014-03-17.
 */
import org.apache.tapestry5.hibernate.modules.HibernateCoreModule;
import org.apache.tapestry5.hibernate.modules.HibernateModule;
import org.apache.tapestry5.ioc.Registry;
import org.apache.tapestry5.ioc.RegistryBuilder;
import org.apache.tapestry5.modules.TapestryModule;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

/**
 * A product of Digimax Technology Inc. (digimax.com)
 * User: jonwilliams
 * Date: 6/22/13
 * Time: 6:45 PM
 */
public abstract class QaRegistryTest {

    protected final static Logger logger = LoggerFactory.getLogger(QaRegistryTest.class);
    protected Registry registry;

    @BeforeClass
    public void oneTimeSetUp() {
        registry = new RegistryBuilder().add(TapestryModule.class,
                HibernateCoreModule.class,
                HibernateModule.class,
                DependencyModule.class,
                QaModule.class).build();
        registry.performRegistryStartup();
        Session session = registry.getService(Session.class);
        session.flush();

        logger.info("QaRegistryTest@BeforeClass - setUp");
    }

    @AfterClass
    public void shutdown() {
        registry.shutdown();
    }

}
