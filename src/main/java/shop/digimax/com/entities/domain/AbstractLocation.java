package shop.digimax.com.entities.domain;

import shop.digimax.com.structural.domain.DomainObject;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.util.List;
import java.util.Set;

/**
 * Created by jon on 2014-03-15.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class AbstractLocation extends DomainObject implements Location {

    @Override
    public Set<LineItem> getLineItems() {
        return null;
    }

    @Override
    public List<Item> getItems() {
        return null;
    }
}
