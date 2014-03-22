package com.digimax.shop.services.domain.dao;

import com.digimax.shop.entities.user.Author;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.Restrictions;

import java.util.*;

/**
 * Created by jon on 2014-03-21.
 */
@SuppressWarnings("unchecked")
public class AuthorDaoImpl implements AuthorDao {

    @Inject
    private Session session;

    @Override
    public List<Author> getAll() {
        List<Author> authors = session.createCriteria(Author.class).list();
        return authors;
    }

    @Override
    public Author get(Long id) {
        return (Author) session.get(Author.class, id);
    }

    @Override
    public boolean exists(Long id) {
        Author author = (Author) session.load(Author.class, id);
        return (author!=null);
    }

    @Override
    public Author save(Author author) {
        session.save(author);
        return author;
    }

    @Override
    public void remove(Long id) {
        Author author = (Author) session.load(Author.class, id);
        session.delete(author);
    }

    @Override
    public void remove(Author author) {
        session.delete(author);
    }

    @Override
    public List<Author> getAllDistinct() {
        List<Author> all = getAll();
        Set<Author> uniqueSet = new HashSet<>(all);
        return new ArrayList<>(uniqueSet);
    }

    @Override
    public List<Author> findByNamedQuery(String queryName, Map<String, Object> queryParams) {
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
        return (List<Author>)q.list();
    }

    public Author findAuthor(String lastName, String firstName) {
        Junction junction = Restrictions.conjunction().add(Restrictions.eq("lastName", lastName));
        if (firstName!=null && firstName.trim().length()>0) {
            junction.add(Restrictions.eq("firstName", firstName));
        } else {
            junction.add(Restrictions.or(Restrictions.and(Restrictions.isNotNull("firstName"),
                    Restrictions.eq("firstName", firstName)), Restrictions.isNull("firstName")));
        }
        Author author  = (Author) session.createCriteria(Author.class).add(junction).uniqueResult();
        return author;
    }

    public Author newAuthor(String userName, String password, String lastName, String firstName) {
//        //check this user doesn't already exist
//        if (!isUserNameUnique(userName))
//            return null;

        Author author = new Author();
        author.userName = userName;
        author.firstName = firstName;
        author.lastName = lastName;
//        author.identityMeta.setNewPassword(password);
        save(author);
        return author;
    }

    public Author findOrCreateAuthor(String authorLastName, String authorFirstName) {
        Author author  = findAuthor(authorLastName, authorFirstName);
        if (author==null) {
            author = newAuthor(null, null, authorLastName, authorFirstName);
        }
        return author;
    }
}
