
package com.task.kamrul.dao;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Kamrul Hasan
 * @param <T>
 * @param <PK>
 */
public interface GenericDao<T, PK extends Serializable> {

    PK create(T o);

    T read(PK id);

    List<T> loadAll();

    void update(T o);

    void delete(T o);

    void createOrUpdate(T o);
}
