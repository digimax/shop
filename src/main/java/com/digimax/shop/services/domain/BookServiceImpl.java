package com.digimax.shop.services.domain;

import com.digimax.shop.entities.domain.item.Book;
import com.digimax.shop.entities.user.Author;
import com.digimax.shop.services.domain.dao.BookDao;
import com.digimax.shop.services.domain.invoice.item.dao.AbstractItemDao;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by jon on 2014-03-16.
 */
public class BookServiceImpl implements BookService {

    @Inject
    private BookDao bookDao;

    @Inject
    private AuthorService authorService;

    public Book findBook(Book searchBook) {
        return bookDao.findBook(searchBook);
    }

    public Book newBook(String title, String subTitle, String firstAuthorLastName, String firstAuthorFirstName, String secondAuthorLastName, String secondAuthorFirstName) {
        Book book = new Book();
        Author author = authorService.findOrCreateAuthor(firstAuthorLastName, firstAuthorFirstName);
        book.authors.add(author);
        if (secondAuthorLastName!=null && secondAuthorLastName.length()>0) {
            Author secondAuthor = authorService.findOrCreateAuthor(secondAuthorLastName, secondAuthorFirstName);
            book.authors.add(secondAuthor);
        }
        book.name = title;
        book.subTitle = subTitle;
        save(book);
        return book;
    }

    public Book findOrCreateBook(Book searchBook) {
        Book book = findBook(searchBook);
        if (book==null) {
            Author author = (Author) searchBook.authors.toArray()[0];
            Author secondAuthor = null;
            if (searchBook.authors.size()>1) {
                secondAuthor = (Author) searchBook.authors.toArray()[1];
            }
            book = newBook(searchBook.name, searchBook.subTitle, author.lastName, author.firstName,
                    (secondAuthor!=null)?secondAuthor.lastName: null,
                    (secondAuthor!=null)?secondAuthor.firstName: null);
        }
        return book;
    }

    @Override
    public Book save(Book book) {
        return bookDao.save(book);
    }

    @Override
    public List<Book> getAllDistinct() {
        return bookDao.getAllDistinct();
    }
}
