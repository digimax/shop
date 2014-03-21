package com.digimax.shop.components;

import com.digimax.shop.entities.domain.AbstractLocation;
import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by jon on 2014-03-19.
 */
public class Breadcrumb {

    @Inject
    private Logger logger;

    @Inject
    PageRenderLinkSource pageRenderLinkSource;

    @Property
    @Parameter(required = true, allowNull = false, defaultPrefix = BindingConstants.PROP)
    private AbstractLocation location;

    @Property
    private AbstractLocation thisLocation;

    public List<AbstractLocation> getParentLocations() {
        List<AbstractLocation> parentAbstractLocations = new ArrayList<>();
        AbstractLocation parentAbstractLocation = location.parent;
        while (parentAbstractLocation!=null) {
            parentAbstractLocations.add(parentAbstractLocation);
            parentAbstractLocation=parentAbstractLocation.parent;
        }
        Collections.reverse(parentAbstractLocations);
        return parentAbstractLocations;
    }

}
