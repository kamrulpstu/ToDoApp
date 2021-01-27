package com.task.kamrul.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Md. Kamrul Hasan
 * @param <T>
 * @param <PK>
 */

public class GenericDaoImpl<T, PK extends Serializable> implements GenericDao<T, PK> 
{

    private HibernateTemplate hibernateTemplate;
    private Class<T> type;

    @Required
    public void setSessionFactory(SessionFactory sessionFactory) {
        hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    protected GenericDaoImpl(Class<T> type) {
        this.type = type;
    }

    @SuppressWarnings({"unchecked"})
    @Override
    @Transactional
    public PK create(T o)
    {
        getHibernateTemplate().setCheckWriteOperations(false);
        
        return (PK) getHibernateTemplate().save(o);
    }

    @SuppressWarnings({"unchecked"})
    @Override
    public T read(PK id) {
        return (T) getHibernateTemplate().get(type,id);
    }

    @SuppressWarnings({"unchecked"})
    @Override
    public List<T> loadAll() {
        return getHibernateTemplate().loadAll(type);
    }

    @SuppressWarnings({"unchecked"})
    @Override
    @Transactional
    public void update(T o) {
        getHibernateTemplate().update(o);
    }

    @SuppressWarnings({"unchecked"})
    @Override
    @Transactional
    public void createOrUpdate(T o)
    {
        getHibernateTemplate().setCheckWriteOperations(false);
        
        getHibernateTemplate().saveOrUpdate(o);
    }

    @SuppressWarnings({"unchecked"})
    @Override
    @Transactional
    public void delete(T o)
    {
        getHibernateTemplate().setCheckWriteOperations(false);
        getHibernateTemplate().delete(o);
    }

}
