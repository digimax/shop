package com.digimax.shop.services.domain;

import com.digimax.shop.services.domain.dao.BookDao;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 * Created by jon on 2014-03-16.
 */
public class BookServiceImpl implements BookService {

    @Inject
    private BookDao bookDao;

}
