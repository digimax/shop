package com.digimax.shop.services;

import java.io.IOException;

import com.digimax.shop.services.bootstrap.BootStrapService;
import com.digimax.shop.services.domain.BookService;
import com.digimax.shop.services.domain.ItemService;
import com.digimax.shop.services.domain.LocationService;
import com.digimax.shop.services.domain.ShopService;
import com.digimax.shop.services.domain.dao.BookDao;
import com.digimax.shop.services.domain.dao.ShelfDao;
import com.digimax.shop.services.domain.dao.ShopDao;
import com.digimax.shop.services.domain.dao.StorageDao;
import com.digimax.shop.services.user.IdentityService;
import com.digimax.shop.services.user.UserService;
import com.digimax.shop.services.user.dao.IdentityDao;
import com.digimax.shop.services.user.dao.UserDao;
import org.apache.tapestry5.*;
import org.apache.tapestry5.hibernate.HibernateTransactionAdvisor;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.MethodAdviceReceiver;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.ioc.annotations.Local;
import org.apache.tapestry5.ioc.annotations.Match;
import org.apache.tapestry5.ioc.services.ApplicationDefaults;
import org.apache.tapestry5.ioc.services.SymbolProvider;
import org.apache.tapestry5.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This module is automatically included as part of the Tapestry IoC Registry, it's a good place to
 * configure and extend Tapestry, or to place your own service definitions.
 */
public class AppModule extends DependencyModule
{
    private static final Logger LOGGER = LoggerFactory.getLogger(AppModule.class);
}
