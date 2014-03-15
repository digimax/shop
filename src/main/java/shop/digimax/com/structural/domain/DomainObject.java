package shop.digimax.com.structural.domain;

/**
 * Created by jon on 2014-03-15.
 */

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.NonVisual;
import org.apache.tapestry5.beaneditor.Validate;
import org.hibernate.annotations.GenericGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xbill.DNS.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

/**
 * A product of Digimax Technology Inc. (digimax.com)
 * User: jonwilliams
 * Date: 6/22/13
 * Time: 5:32 PM
 */
@MappedSuperclass
public class DomainObject implements Serializable {

    private final static Logger LOGGER = LoggerFactory.getLogger(DomainObject.class);

    @Property
    @NonVisual
    public Date createDate;

    @Property
    @NonVisual
    public String createUserName;

    @Property
    @NonVisual
    public String createUserIpAddress;


    @Property
    @NonVisual
    public Date modifiedDate;
    @Property
    @NonVisual
    public String modifiedUserName;

    @Id
    @Column(unique = true)
    @GenericGenerator(name="random_id", strategy="com.digimax.shop.structural.domain.RandomIdGenerator")
    @GeneratedValue(generator = "random_id")
    @NonVisual
    public Long id;

    @Property
    @Validate("required, minlength=1")
    public String name;

    public DomainObject() {
        super();
//        LOGGER.debug("Instantiated Domain Object ::{} .{}", new Object[] {this, id});
    }


    @Override
    public boolean equals(Object o) {
        DomainObject domainObject = (DomainObject) o;
        boolean isEqual = (domainObject!=null && getClass() == domainObject.getClass() &&
                (domainObject==this || (domainObject.id!=null && (domainObject.id.longValue()==this.id.longValue()))));
        return isEqual;
    }

    @Override
    public int hashCode() {
        return id == null ? super.hashCode() : id.hashCode();
    }

    public Date getCreatedDate() {
        return createDate;
    }

    @NonVisual
    public String getCreatedBy() {
        return createUserName;
    }

    public String getCreatorId() {
        return createUserIpAddress!=null? Long.toString(Arrays.hashCode(createUserIpAddress.getBytes())): "unassigned";
    }

    public String getCreatorLocation() throws IOException {
        String location = reverseDnsLookup(createUserIpAddress);
        location = (location!=null && location.contains("."))
                ? location.substring(location.indexOf('.'))
                : "";
        return location;
    }

    private String reverseDnsLookup(String hostIpAddress) throws IOException {
        if (hostIpAddress==null || hostIpAddress.trim().length()<1) {
            return null;
        }
        LOGGER.debug("Starting Reverse DNS Lookup for: {}", hostIpAddress);
        Resolver res = new ExtendedResolver();

        Name name = ReverseMap.fromAddress(hostIpAddress);
        int type = Type.PTR;
        int dclass = DClass.IN;
        Record rec = Record.newRecord(name, type, dclass);
        Message query = Message.newQuery(rec);
        Message response = res.send(query);

        Record[] answers = response.getSectionArray(Section.ANSWER);
        if (answers.length == 0)
            return null;
        else
            return answers[0].rdataToString();
    }


    public static final class Compare implements Comparator<DomainObject> {
        @Override
        public int compare(DomainObject domainObject1, DomainObject domainObject2) {
            return Long.valueOf(domainObject1.createDate.getTime()).compareTo(domainObject2.createDate.getTime());
        }
    }
}
