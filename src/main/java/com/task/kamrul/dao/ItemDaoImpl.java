package com.task.kamrul.dao;

import com.task.kamrul.bean.BaseResponse;
import com.task.kamrul.bean.ItemBean;
import com.task.kamrul.entity.Item;
import com.task.kamrul.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Kamrul Hasan Shamim
 */
public class ItemDaoImpl extends GenericDaoImpl<Item, Integer> implements ItemDao {

    static Logger log = Logger.getLogger(ItemDaoImpl.class);

    public ItemDaoImpl() {
        super(Item.class);
    }

    @Override
    public List<Item> searchItemByObject(Item itemObj) {
        log.info("In ItemDaoImpl.searchItemByObject() method...!");

        List<Item> itemList = new ArrayList<>();

        DetachedCriteria dtc = DetachedCriteria.forClass(Item.class);

        if (itemObj.getItemId() != 0) {
            dtc.add(Restrictions.eq("itemId", itemObj.getItemId()));
        }
        if (itemObj.getItemName() != null && !itemObj.getItemName().isEmpty()) {
            dtc.add(Restrictions.ilike("itemName", itemObj.getItemName(), MatchMode.ANYWHERE));
        }
        if (itemObj.getItemDesc() != null && !itemObj.getItemDesc().isEmpty()) {
            dtc.add(Restrictions.ilike("itemDesc", itemObj.getItemDesc(), MatchMode.ANYWHERE));
        }
        if (itemObj.getItemDate() != null) {
            dtc.add(Restrictions.eq("itemDate", itemObj.getItemDate()));
        }

        try {
            itemList = (List) getHibernateTemplate().findByCriteria(dtc);
        } catch (Exception ex) {
            log.error("Exception in getting ItemDaoImpl.searchItemByObject::" + ex.getMessage());
        }

        return itemList;

    }

    public Integer getItemId() {
        log.info("In ItemDaoImpl.getItemId() method ..!");
        Query query = null;
        Integer itemId = 0;

        Session dbSession = HibernateUtil.getSessionFactory().openSession();
        Transaction dbTrx = dbSession.beginTransaction();

        try {

            query = dbSession.createQuery("select max(itemId)+1 from Item");
            itemId = (Integer) query.uniqueResult();
            if (itemId == null) {
                itemId = 1;

            }

        } catch (Exception ex) {
            log.info(ex.getMessage());
        } finally {
            dbSession.close();
        }
        return itemId;
    }

}
