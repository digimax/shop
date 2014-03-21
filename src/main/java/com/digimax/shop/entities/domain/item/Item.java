package com.digimax.shop.entities.domain.item;

import com.digimax.shop.entities.app.Image;

import java.util.List;

/**
 * Created by jon on 2014-03-20.
 */
public interface Item {
    String getName();
    String getSku();
    Long getUpc13();
    List<Image> getImages();
}
