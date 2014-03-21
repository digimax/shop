package com.digimax.shop.services.domain;

import com.digimax.shop.entities.user.Author;
import com.digimax.shop.services.domain.dao.AuthorDao;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 * Created by jon on 2014-03-21.
 */
public class AuthorServiceImpl implements AuthorService {

    @Inject
    private AuthorDao authorDao;
    @Override
    public Author findOrCreateAuthor(String lastName, String firstName) {
        return authorDao.findOrCreateAuthor(lastName, firstName);
    }
}
