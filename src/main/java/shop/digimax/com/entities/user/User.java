package shop.digimax.com.entities.user;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.Validate;
import shop.digimax.com.structural.domain.DomainObject;

import javax.persistence.*;

/**
 * Created by jon on 2014-03-15.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends DomainObject {

    @Property
    @Validate("required, minlength=2")
    public String firstName;

    @Property
    @Validate("required, minlength=2")
    public String lastName;

    @Property
    @Validate("required, minlength=6, regexp")
    public String userName;

    public User() {
        super();
    }

    public String getFullName() {
        String fullName = null;
        if (firstName!=null) {
            fullName = String.format("%s %s", firstName, lastName);
        } else if (lastName!=null) {
            fullName = lastName;
        }
        return fullName;
    }

    public String toString() {
        return String.format("(User %s)\n\r %s %s", userName, firstName, lastName);
    }
}
