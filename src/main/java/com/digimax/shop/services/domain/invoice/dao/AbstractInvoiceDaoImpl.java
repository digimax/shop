package com.digimax.shop.services.domain.invoice.dao;

import com.digimax.shop.entities.domain.Shop;
import com.digimax.shop.entities.domain.invoice.AbstractInvoice;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.*;

/**
 * Created by jon on 2014-03-20.
 */
@SuppressWarnings("unchecked")
public class AbstractInvoiceDaoImpl implements AbstractInvoiceDao {

    @Inject
    private Session session;

    @Override
    public List<AbstractInvoice> getAll() {
        List<AbstractInvoice> invoices = session.createCriteria(AbstractInvoice.class).list();
        return invoices;
    }

    @Override
    public AbstractInvoice get(Long id) {
        return (AbstractInvoice) session.get(AbstractInvoice.class, id);
    }

    @Override
    public boolean exists(Long id) {
        AbstractInvoice invoice = (AbstractInvoice) session.load(AbstractInvoice.class, id);
        return (invoice!=null);
    }

    @Override
    public AbstractInvoice save(AbstractInvoice invoice) {
        session.save(invoice);
        return invoice;
    }

    @Override
    public void remove(Long id) {
        AbstractInvoice invoice = (AbstractInvoice) session.load(AbstractInvoice.class, id);
        session.delete(invoice);
    }

    @Override
    public void remove(AbstractInvoice invoice) {
        session.delete(invoice);
    }

    @Override
    public List<AbstractInvoice> getAllDistinct() {
        List<AbstractInvoice> all = getAll();
        Set<AbstractInvoice> uniqueSet = new HashSet<>(all);
        return new ArrayList<>(uniqueSet);
    }

    @Override
    public List<AbstractInvoice> findByNamedQuery(String queryName, Map<String, Object> queryParams) {
        Query q = session.getNamedQuery(queryName);

        String []params = new String[queryParams.size()];
        Object []values = new Object[queryParams.size()];
        int index = 0;
        for (String key : queryParams.keySet()) {
            params[index] = key;
            values[index++] = queryParams.get(key);
        }
        if (queryParams!=null && queryParams.size()>0) {
            Iterator<String> i = queryParams.keySet().iterator();
            for (int j=0; j<params.length; j++) {
                q.setParameter(params[j], values[j]);
            }
        }
        return (List<AbstractInvoice>)q.list();
    }
}
