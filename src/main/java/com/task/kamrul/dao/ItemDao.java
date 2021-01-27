package com.task.kamrul.dao;

import com.task.kamrul.entity.Item;
import java.util.List;

/**
 *
 * @author Kamrul Hasan Shamim
 */
public interface ItemDao extends GenericDao<Item, Integer> {

    List<Item> searchItemByObject(Item item);

    Integer getItemId();

}
