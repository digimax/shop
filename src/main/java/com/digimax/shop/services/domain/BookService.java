package com.digimax.shop.services.domain;

import com.digimax.shop.entities.domain.item.Book;
import com.digimax.shop.services.domain.dao.BookDao;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;

import java.util.List;

/**
 * Created by jon on 2014-03-16.
 */
public interface BookService {

    @CommitAfter
    Book findOrCreateBook(Book searchBook);
    @CommitAfter
    Book save(Book book);
    List<Book> getAllDistinct();
}
