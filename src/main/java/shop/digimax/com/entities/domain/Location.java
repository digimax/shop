package shop.digimax.com.entities.domain;

import java.util.List;
import java.util.Set;

/**
 * Created by jon on 2014-03-15.
 */
public interface Location {

    Set<LineItem> getLineItems();
    List<Item> getItems();
}
