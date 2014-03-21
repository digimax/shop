package com.digimax.shop.services.domain.dao;

import com.digimax.shop.entities.domain.item.Book;
import com.digimax.shop.entities.user.Author;
import com.digimax.shop.structural.domain.GenericDao;

/**
 * Created by jon on 2014-03-21.
 */
public interface AuthorDao extends GenericDao<Author, Long> {
    Author findOrCreateAuthor(String lastName, String firstName);
}
