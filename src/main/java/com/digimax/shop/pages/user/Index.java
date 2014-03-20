package com.digimax.shop.pages.user;

import com.digimax.shop.entities.domain.AbstractLocation;
import com.digimax.shop.entities.user.User;
import org.apache.tapestry5.annotations.PageActivationContext;
import org.apache.tapestry5.annotations.Property;

/**
 * Created by jon on 2014-03-19.
 */
public class Index {

    @PageActivationContext
    @Property
    private User user;
}
