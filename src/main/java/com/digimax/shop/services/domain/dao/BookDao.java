package com.digimax.shop.services.domain.dao;

import com.digimax.shop.entities.domain.item.Book;
import com.digimax.shop.structural.domain.GenericDao;

/**
 * Created by jon on 2014-03-17.
 */
public interface BookDao extends GenericDao<Book, Long> {
    Book findBook(Book searchBook);
}
