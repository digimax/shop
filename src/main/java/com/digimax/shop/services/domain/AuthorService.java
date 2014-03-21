package com.digimax.shop.services.domain;

import com.digimax.shop.entities.user.Author;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;

/**
 * Created by jon on 2014-03-21.
 */
public interface AuthorService {

    @CommitAfter
    Author findOrCreateAuthor(String firstAuthorLastName, String firstAuthorFirstName);
}
