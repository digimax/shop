package com.digimax.shop.structural.domain;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by jon on 2014-03-15.
 */
public class RandomIdGenerator implements IdentifierGenerator {
    @Override
    public Serializable generate(SessionImplementor session, Object object) throws HibernateException {
        long sessionHash = Arrays.hashCode(session.toString().getBytes());
        Random randoms = new Random(System.currentTimeMillis()+sessionHash);
        return Math.abs(randoms.nextLong());
    }
}
