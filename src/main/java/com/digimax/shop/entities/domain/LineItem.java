package com.digimax.shop.entities.domain;

import com.digimax.shop.entities.domain.item.AbstractItem;

import java.math.BigDecimal;

/**
 * Created by jon on 2014-03-15.
 */
public interface LineItem {

    BigDecimal getQuantity();
    AbstractItem getItem();
}
