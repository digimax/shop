package com.digimax.shop.services.domain.dao;

import com.digimax.shop.entities.domain.Book;
import com.digimax.shop.entities.domain.Shop;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.*;

/**
 * Created by jon on 2014-03-17.
 */
@SuppressWarnings("unchecked")
public class BookDaoImpl implements BookDao {

    @Inject
    private Session session;

    @Override
    public List<Book> getAll() {
        List<Book> books = session.createCriteria(Book.class).list();
        return books;
    }

    @Override
    public Book get(Long id) {
        return (Book) session.get(Book.class, id);
    }

    @Override
    public boolean exists(Long id) {
        Book book = (Book) session.load(Book.class, id);
        return (book!=null);
    }

    @Override
    public Book save(Book book) {
        session.save(book);
        return book;
    }

    @Override
    public void remove(Long id) {
        Book book = (Book) session.load(Book.class, id);
        session.delete(book);
    }

    @Override
    public void remove(Book book) {
        session.delete(book);
    }

    @Override
    public List<Book> getAllDistinct() {
        List<Book> all = getAll();
        Set<Book> uniqueSet = new HashSet<>(all);
        return new ArrayList<>(uniqueSet);
    }

    @Override
    public List<Book> findByNamedQuery(String queryName, Map<String, Object> queryParams) {
        Query q = session.getNamedQuery(queryName);

        String []params = new String[queryParams.size()];
        Object []values = new Object[queryParams.size()];
        int index = 0;
        for (String key : queryParams.keySet()) {
            params[index] = key;
            values[index++] = queryParams.get(key);
        }
        if (queryParams!=null && queryParams.size()>0) {
            Iterator<String> i = queryParams.keySet().iterator();
            for (int j=0; j<params.length; j++) {
                q.setParameter(params[j], values[j]);
            }
        }
        return (List<Book>)q.list();
    }    
}
