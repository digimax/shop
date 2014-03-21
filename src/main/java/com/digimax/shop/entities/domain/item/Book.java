package com.digimax.shop.entities.domain.item;

import com.digimax.shop.entities.user.Author;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.Validate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jon on 2014-03-16.
 */
@Entity
public class Book extends AbstractItem {

    @Property
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    public Set<Author> authors = new HashSet<>();

    @Property
    public String subTitle;
}
