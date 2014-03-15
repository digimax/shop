package shop.digimax.com.entities.user;

import javax.persistence.Entity;

/**
 * Created by jon on 2014-03-15.
 */
@Entity
public class Person extends User {

    public Person() {
        super();
    }

    public Person(String firstName, String lastName, String userId) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userId;
    }
}
